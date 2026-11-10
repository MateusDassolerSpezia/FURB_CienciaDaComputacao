package miniso.modulo5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Sistema de arquivos em memória com alocação encadeada por FAT e bitmap. */
final class SistemaArquivosFAT {
    static final int TOTAL_BLOCOS = 32;
    static final int TAMANHO_BLOCO = 64;
    static final int BLOCOS_RESERVADOS = 2;
    private static final int LIVRE = -1;
    private static final int EOF = -2;
    private static final int RESERVADO = -3;

    private static final class EntradaInterna {
        final String caminho;
        final boolean diretorio;
        final int tamanho;
        final int primeiroBloco;

        EntradaInterna(String caminho, boolean diretorio, int tamanho, int primeiroBloco) {
            this.caminho = caminho;
            this.diretorio = diretorio;
            this.tamanho = tamanho;
            this.primeiroBloco = primeiroBloco;
        }
    }

    private final int[] fat = new int[TOTAL_BLOCOS];
    private final boolean[] bitmap = new boolean[TOTAL_BLOCOS];
    private final String[] proprietarios = new String[TOTAL_BLOCOS];
    private final Map<String, EntradaInterna> diretorio = new LinkedHashMap<>();
    private final List<String> eventos = new ArrayList<>();
    private int arquivosCriados;
    private int arquivosExcluidos;
    private int falhas;

    SistemaArquivosFAT() {
        Arrays.fill(fat, LIVRE);
        for (int bloco = 0; bloco < BLOCOS_RESERVADOS; bloco++) {
            fat[bloco] = RESERVADO;
            bitmap[bloco] = true;
            proprietarios[bloco] = "SISTEMA";
        }
        diretorio.put("/", new EntradaInterna("/", true, 0, -1));
        eventos.add("Disco formatado: 32 blocos de 64 bytes; blocos 0 e 1 reservados para metadados.");
    }

    void criarDiretorio(String caminho) {
        validarNovoCaminho(caminho);
        validarDiretorioPai(caminho);
        diretorio.put(caminho, new EntradaInterna(caminho, true, 0, -1));
        eventos.add("MKDIR " + caminho + " — entrada de diretório criada.");
    }

    void criarArquivo(String caminho, int tamanho) {
        validarNovoCaminho(caminho);
        validarDiretorioPai(caminho);
        if (tamanho < 0) throw new IllegalArgumentException("O tamanho do arquivo não pode ser negativo.");

        int necessarios = (tamanho + TAMANHO_BLOCO - 1) / TAMANHO_BLOCO;
        List<Integer> livres = localizarLivres(necessarios);
        if (livres.size() < necessarios) {
            falhas++;
            eventos.add("ERRO CREATE " + caminho + " (" + tamanho + " bytes) — necessários "
                    + necessarios + " blocos, disponíveis " + livres.size() + "; nenhuma alteração realizada.");
            throw new IllegalStateException("Espaço insuficiente para criar " + caminho + ".");
        }

        for (int i = 0; i < livres.size(); i++) {
            int bloco = livres.get(i);
            fat[bloco] = i == livres.size() - 1 ? EOF : livres.get(i + 1);
            bitmap[bloco] = true;
            proprietarios[bloco] = caminho;
        }
        int primeiro = livres.isEmpty() ? -1 : livres.get(0);
        diretorio.put(caminho, new EntradaInterna(caminho, false, tamanho, primeiro));
        arquivosCriados++;
        eventos.add("CREATE " + caminho + " (" + tamanho + " bytes) — FAT: " + cadeiaTexto(livres) + ".");
    }

    void excluirArquivo(String caminho) {
        EntradaInterna entrada = diretorio.get(caminho);
        if (entrada == null || entrada.diretorio) throw new IllegalArgumentException("Arquivo inexistente: " + caminho);
        List<Integer> cadeia = percorrerCadeia(entrada);
        for (int bloco : cadeia) {
            fat[bloco] = LIVRE;
            bitmap[bloco] = false;
            proprietarios[bloco] = null;
        }
        diretorio.remove(caminho);
        arquivosExcluidos++;
        eventos.add("DELETE " + caminho + " — blocos liberados: " + cadeiaTexto(cadeia) + ".");
    }

    void lerArquivo(String caminho) {
        EntradaInterna entrada = diretorio.get(caminho);
        if (entrada == null || entrada.diretorio) throw new IllegalArgumentException("Arquivo inexistente: " + caminho);
        List<Integer> cadeia = percorrerCadeia(entrada);
        eventos.add("READ " + caminho + " — seguindo a FAT: " + cadeiaTexto(cadeia)
                + "; " + entrada.tamanho + " bytes lidos.");
    }

    void registrarFalhaEsperada(String mensagem) {
        eventos.add("ROLLBACK confirmado — " + mensagem);
    }

    ResultadoSistemaArquivos resultado(String titulo, String descricao) {
        validarIntegridade();
        List<ResultadoSistemaArquivos.Bloco> blocos = new ArrayList<>();
        for (int bloco = 0; bloco < TOTAL_BLOCOS; bloco++) {
            String estado = fat[bloco] == LIVRE ? "Livre" : fat[bloco] == RESERVADO ? "Reservado" : "Ocupado";
            String proximo = fat[bloco] == LIVRE ? "—" : fat[bloco] == RESERVADO ? "SYS"
                    : fat[bloco] == EOF ? "EOF" : String.valueOf(fat[bloco]);
            blocos.add(new ResultadoSistemaArquivos.Bloco(bloco, estado, proximo,
                    proprietarios[bloco] == null ? "—" : proprietarios[bloco]));
        }

        List<ResultadoSistemaArquivos.Entrada> entradas = new ArrayList<>();
        int fragmentados = 0;
        for (EntradaInterna entrada : diretorio.values()) {
            List<Integer> cadeia = entrada.diretorio ? new ArrayList<>() : percorrerCadeia(entrada);
            if (!entrada.diretorio && fragmentada(cadeia)) fragmentados++;
            entradas.add(new ResultadoSistemaArquivos.Entrada(entrada.caminho,
                    entrada.diretorio ? "Diretório" : "Arquivo", entrada.tamanho,
                    entrada.primeiroBloco, cadeia));
        }

        int usados = contarUsados();
        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Capacidade", TOTAL_BLOCOS * TAMANHO_BLOCO + " bytes");
        resumo.put("Blocos de dados", String.valueOf(TOTAL_BLOCOS - BLOCOS_RESERVADOS));
        resumo.put("Blocos ocupados", String.valueOf(usados));
        resumo.put("Blocos livres", String.valueOf(TOTAL_BLOCOS - usados));
        resumo.put("Arquivos criados", String.valueOf(arquivosCriados));
        resumo.put("Arquivos excluídos", String.valueOf(arquivosExcluidos));
        resumo.put("Arquivos fragmentados", String.valueOf(fragmentados));
        resumo.put("Falhas sem alteração", String.valueOf(falhas));
        resumo.put("Integridade FAT/bitmap", "Confirmada");
        return new ResultadoSistemaArquivos(titulo, descricao, eventos, resumo, blocos, entradas, bitmap);
    }

    private List<Integer> localizarLivres(int quantidade) {
        List<Integer> encontrados = new ArrayList<>();
        for (int bloco = BLOCOS_RESERVADOS; bloco < TOTAL_BLOCOS && encontrados.size() < quantidade; bloco++) {
            if (!bitmap[bloco]) encontrados.add(bloco);
        }
        return encontrados;
    }

    private List<Integer> percorrerCadeia(EntradaInterna entrada) {
        List<Integer> cadeia = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        int bloco = entrada.primeiroBloco;
        while (bloco >= 0) {
            if (bloco >= TOTAL_BLOCOS || !visitados.add(bloco)) {
                throw new IllegalStateException("Cadeia FAT corrompida em " + entrada.caminho + ".");
            }
            cadeia.add(bloco);
            bloco = fat[bloco] == EOF ? -1 : fat[bloco];
        }
        return cadeia;
    }

    private void validarIntegridade() {
        Set<Integer> referenciados = new HashSet<>();
        for (EntradaInterna entrada : diretorio.values()) {
            if (entrada.diretorio) continue;
            List<Integer> cadeia = percorrerCadeia(entrada);
            int esperados = (entrada.tamanho + TAMANHO_BLOCO - 1) / TAMANHO_BLOCO;
            if (cadeia.size() != esperados) throw new IllegalStateException("Tamanho da cadeia incompatível.");
            for (int bloco : cadeia) {
                if (!referenciados.add(bloco) || !bitmap[bloco]
                        || !entrada.caminho.equals(proprietarios[bloco])) {
                    throw new IllegalStateException("Bloco duplicado ou inconsistente: " + bloco);
                }
            }
        }
        for (int bloco = BLOCOS_RESERVADOS; bloco < TOTAL_BLOCOS; bloco++) {
            boolean alocado = fat[bloco] != LIVRE;
            if (bitmap[bloco] != alocado || alocado != referenciados.contains(bloco)) {
                throw new IllegalStateException("FAT e bitmap divergem no bloco " + bloco + ".");
            }
        }
        eventos.add("FSCK — FAT, bitmap, diretório e cadeias verificados sem inconsistências.");
    }

    private void validarNovoCaminho(String caminho) {
        if (caminho == null || !caminho.startsWith("/") || "/".equals(caminho) || caminho.endsWith("/")) {
            throw new IllegalArgumentException("Caminho inválido: " + caminho);
        }
        if (diretorio.containsKey(caminho)) throw new IllegalArgumentException("Caminho já existe: " + caminho);
    }

    private void validarDiretorioPai(String caminho) {
        int separador = caminho.lastIndexOf('/');
        String pai = separador == 0 ? "/" : caminho.substring(0, separador);
        EntradaInterna entradaPai = diretorio.get(pai);
        if (entradaPai == null || !entradaPai.diretorio) {
            throw new IllegalArgumentException("Diretório pai inexistente: " + pai);
        }
    }

    private int contarUsados() {
        int usados = 0;
        for (boolean ocupado : bitmap) if (ocupado) usados++;
        return usados;
    }

    private boolean fragmentada(List<Integer> cadeia) {
        for (int i = 1; i < cadeia.size(); i++) if (cadeia.get(i) != cadeia.get(i - 1) + 1) return true;
        return false;
    }

    private String cadeiaTexto(List<Integer> cadeia) {
        if (cadeia.isEmpty()) return "arquivo vazio → EOF";
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < cadeia.size(); i++) {
            if (i > 0) texto.append(" → ");
            texto.append(cadeia.get(i));
        }
        return texto.append(" → EOF").toString();
    }
}
