package miniso.modulo3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Resultado completo de uma execução da MMU simulada. */
public final class ResultadoMemoria {
    static final class Acesso {
        final int indice;
        final int enderecoVirtual;
        final int pagina;
        final int deslocamento;
        final String resultado;
        final int quadro;
        final int enderecoFisico;
        final Integer paginaSubstituida;
        final int[] quadros;

        Acesso(int indice, int enderecoVirtual, int pagina, int deslocamento, String resultado,
               int quadro, int enderecoFisico, Integer paginaSubstituida, int[] quadros) {
            this.indice = indice;
            this.enderecoVirtual = enderecoVirtual;
            this.pagina = pagina;
            this.deslocamento = deslocamento;
            this.resultado = resultado;
            this.quadro = quadro;
            this.enderecoFisico = enderecoFisico;
            this.paginaSubstituida = paginaSubstituida;
            this.quadros = quadros.clone();
        }
    }

    static final class Pagina {
        final int numero;
        final boolean presente;
        final int quadro;

        Pagina(int numero, int quadro) {
            this.numero = numero;
            this.presente = quadro >= 0;
            this.quadro = quadro;
        }
    }

    private final String titulo;
    private final String descricao;
    private final List<String> eventos;
    private final Map<String, String> resumo;
    private final List<Acesso> acessos;
    private final List<Pagina> tabelaPaginas;

    ResultadoMemoria(String titulo, String descricao, List<String> eventos,
                     Map<String, String> resumo, List<Acesso> acessos, List<Pagina> tabelaPaginas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.eventos = new ArrayList<>(eventos);
        this.resumo = new LinkedHashMap<>(resumo);
        this.acessos = new ArrayList<>(acessos);
        this.tabelaPaginas = new ArrayList<>(tabelaPaginas);
    }

    public String paraJson() {
        StringBuilder json = new StringBuilder("{");
        campo(json, "titulo", titulo).append(',');
        campo(json, "descricao", descricao).append(',');

        json.append("\"eventos\":[");
        for (int i = 0; i < eventos.size(); i++) {
            if (i > 0) json.append(',');
            texto(json, eventos.get(i));
        }

        json.append("],\"resumo\":{");
        int indiceResumo = 0;
        for (Map.Entry<String, String> item : resumo.entrySet()) {
            if (indiceResumo++ > 0) json.append(',');
            texto(json, item.getKey()).append(':');
            texto(json, item.getValue());
        }

        json.append("},\"acessos\":[");
        for (int i = 0; i < acessos.size(); i++) {
            if (i > 0) json.append(',');
            Acesso acesso = acessos.get(i);
            json.append('{');
            numero(json, "indice", acesso.indice).append(',');
            numero(json, "enderecoVirtual", acesso.enderecoVirtual).append(',');
            numero(json, "pagina", acesso.pagina).append(',');
            numero(json, "deslocamento", acesso.deslocamento).append(',');
            campo(json, "resultado", acesso.resultado).append(',');
            numero(json, "quadro", acesso.quadro).append(',');
            numero(json, "enderecoFisico", acesso.enderecoFisico).append(',');
            texto(json, "paginaSubstituida").append(':');
            if (acesso.paginaSubstituida == null) json.append("null");
            else json.append(acesso.paginaSubstituida);
            json.append(",\"quadros\":[");
            for (int quadro = 0; quadro < acesso.quadros.length; quadro++) {
                if (quadro > 0) json.append(',');
                json.append(acesso.quadros[quadro]);
            }
            json.append("]}");
        }

        json.append("],\"tabelaPaginas\":[");
        for (int i = 0; i < tabelaPaginas.size(); i++) {
            if (i > 0) json.append(',');
            Pagina pagina = tabelaPaginas.get(i);
            json.append('{');
            numero(json, "pagina", pagina.numero).append(',');
            texto(json, "presente").append(':').append(pagina.presente).append(',');
            numero(json, "quadro", pagina.quadro).append(',');
            campo(json, "local", pagina.presente ? "Memória física" : "Disco de swap").append('}');
        }
        return json.append("]}").toString();
    }

    private static StringBuilder campo(StringBuilder json, String nome, String valor) {
        texto(json, nome).append(':');
        return texto(json, valor);
    }

    private static StringBuilder numero(StringBuilder json, String nome, int valor) {
        texto(json, nome).append(':').append(valor);
        return json;
    }

    private static StringBuilder texto(StringBuilder json, String valor) {
        json.append('"');
        for (int i = 0; i < valor.length(); i++) {
            char caractere = valor.charAt(i);
            switch (caractere) {
                case '"': json.append("\\\""); break;
                case '\\': json.append("\\\\"); break;
                case '\n': json.append("\\n"); break;
                case '\r': json.append("\\r"); break;
                case '\t': json.append("\\t"); break;
                default:
                    if (caractere < 0x20) json.append(String.format("\\u%04x", (int) caractere));
                    else json.append(caractere);
            }
        }
        return json.append('"');
    }
}
