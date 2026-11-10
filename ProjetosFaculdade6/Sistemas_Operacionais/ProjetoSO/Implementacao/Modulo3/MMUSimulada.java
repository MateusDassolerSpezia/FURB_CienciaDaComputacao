package miniso.modulo3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** MMU com paginação simples, swap e substituição FIFO ou LRU. */
final class MMUSimulada {
    enum Algoritmo {
        FIFO, LRU
    }

    private static final int TAMANHO_PAGINA = 256;
    private static final int TOTAL_PAGINAS = 8;
    private static final int[] ENDERECOS = {
            1900, 42, 301, 650, 77, 900, 128, 1100,
            700, 850, 200, 1000, 620, 1402, 1605, 1850
    };

    private static final class Quadro {
        final int numero;
        int pagina = -1;
        int carregadoEm = -1;
        int ultimoUso = -1;

        Quadro(int numero) {
            this.numero = numero;
        }
    }

    private final Algoritmo algoritmo;
    private final Quadro[] quadros;
    private final int[] tabelaPaginas = new int[TOTAL_PAGINAS];
    private final List<String> eventos = new ArrayList<>();
    private final List<ResultadoMemoria.Acesso> acessos = new ArrayList<>();
    private int hits;
    private int pageFaults;
    private int substituicoes;

    MMUSimulada(Algoritmo algoritmo, int quantidadeQuadros) {
        this.algoritmo = algoritmo;
        this.quadros = new Quadro[quantidadeQuadros];
        for (int i = 0; i < quantidadeQuadros; i++) quadros[i] = new Quadro(i);
        Arrays.fill(tabelaPaginas, -1);
    }

    ResultadoMemoria executar() {
        registrar("MMU iniciada: " + TOTAL_PAGINAS + " páginas virtuais, " + quadros.length
                + " quadros físicos e páginas de " + TAMANHO_PAGINA + " bytes.");

        for (int indice = 0; indice < ENDERECOS.length; indice++) {
            int enderecoVirtual = ENDERECOS[indice];
            int pagina = enderecoVirtual / TAMANHO_PAGINA;
            int deslocamento = enderecoVirtual % TAMANHO_PAGINA;
            int quadro = tabelaPaginas[pagina];
            Integer paginaSubstituida = null;
            String resultado;

            if (quadro >= 0) {
                hits++;
                resultado = "HIT";
                quadros[quadro].ultimoUso = indice;
                registrar(formatoAcesso(indice, enderecoVirtual, pagina, deslocamento)
                        + " HIT no quadro " + quadro + ".");
            } else {
                pageFaults++;
                resultado = "PAGE FAULT";
                Quadro destino = localizarQuadroLivre();
                if (destino == null) {
                    destino = escolherVitima();
                    paginaSubstituida = destino.pagina;
                    tabelaPaginas[destino.pagina] = -1;
                    substituicoes++;
                    registrar("      swap-out: página " + paginaSubstituida + " removida do quadro "
                            + destino.numero + " pelo algoritmo " + algoritmo + ".");
                }

                quadro = destino.numero;
                destino.pagina = pagina;
                destino.carregadoEm = indice;
                destino.ultimoUso = indice;
                tabelaPaginas[pagina] = quadro;
                registrar(formatoAcesso(indice, enderecoVirtual, pagina, deslocamento)
                        + " PAGE FAULT; swap-in da página " + pagina + " no quadro " + quadro + ".");
            }

            int enderecoFisico = quadro * TAMANHO_PAGINA + deslocamento;
            registrar("      tradução: quadro " + quadro + " × " + TAMANHO_PAGINA + " + "
                    + deslocamento + " = endereço físico " + enderecoFisico + ".");
            acessos.add(new ResultadoMemoria.Acesso(indice + 1, enderecoVirtual, pagina, deslocamento,
                    resultado, quadro, enderecoFisico, paginaSubstituida, fotografarQuadros()));
        }

        registrar("Simulação concluída: " + hits + " hits e " + pageFaults + " page faults.");
        return montarResultado();
    }

    private Quadro localizarQuadroLivre() {
        for (Quadro quadro : quadros) {
            if (quadro.pagina < 0) return quadro;
        }
        return null;
    }

    private Quadro escolherVitima() {
        Comparator<Quadro> comparador = algoritmo == Algoritmo.FIFO
                ? Comparator.comparingInt((Quadro q) -> q.carregadoEm).thenComparingInt(q -> q.numero)
                : Comparator.comparingInt((Quadro q) -> q.ultimoUso).thenComparingInt(q -> q.numero);
        return Arrays.stream(quadros).min(comparador)
                .orElseThrow(() -> new IllegalStateException("Nenhum quadro disponível para substituição."));
    }

    private int[] fotografarQuadros() {
        int[] estado = new int[quadros.length];
        for (int i = 0; i < quadros.length; i++) estado[i] = quadros[i].pagina;
        return estado;
    }

    private ResultadoMemoria montarResultado() {
        List<ResultadoMemoria.Pagina> paginas = new ArrayList<>();
        for (int pagina = 0; pagina < TOTAL_PAGINAS; pagina++) {
            paginas.add(new ResultadoMemoria.Pagina(pagina, tabelaPaginas[pagina]));
        }

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Algoritmo", algoritmo.toString());
        resumo.put("Quadros físicos", String.valueOf(quadros.length));
        resumo.put("Tamanho da página", TAMANHO_PAGINA + " bytes");
        resumo.put("Acessos", String.valueOf(ENDERECOS.length));
        resumo.put("Hits", String.valueOf(hits));
        resumo.put("Page faults", String.valueOf(pageFaults));
        resumo.put("Substituições", String.valueOf(substituicoes));
        resumo.put("Taxa de page fault", percentual(pageFaults, ENDERECOS.length));

        String descricao = algoritmo == Algoritmo.FIFO
                ? "FIFO substitui a página que está há mais tempo na memória física."
                : "LRU substitui a página que passou mais tempo sem ser acessada.";
        return new ResultadoMemoria("Substituição " + algoritmo, descricao, eventos, resumo, acessos, paginas);
    }

    private String formatoAcesso(int indice, int endereco, int pagina, int deslocamento) {
        return String.format("%02d | virtual %4d → página %d, deslocamento %3d:",
                indice + 1, endereco, pagina, deslocamento);
    }

    private String percentual(int parte, int total) {
        return String.format(Locale.US, "%.2f%%", parte * 100.0 / total).replace('.', ',');
    }

    private void registrar(String mensagem) {
        eventos.add(mensagem);
    }
}
