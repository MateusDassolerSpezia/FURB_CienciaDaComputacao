package miniso.modulo2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Resultado serializável de uma execução do escalonador. */
public final class ResultadoEscalonamento {
    public static final class Faixa {
        final int inicio;
        int fim;
        final String processo;

        Faixa(int inicio, int fim, String processo) {
            this.inicio = inicio;
            this.fim = fim;
            this.processo = processo;
        }
    }

    public static final class Metrica {
        final String processo;
        final int chegada;
        final int cpu;
        final int prioridade;
        final int espera;
        final int turnaround;
        final int resposta;
        final int termino;

        Metrica(Processo processo) {
            this.processo = processo.id;
            this.chegada = processo.chegada;
            this.cpu = processo.tempoCpu;
            this.prioridade = processo.prioridadeInicial;
            this.espera = processo.tempoEspera;
            this.turnaround = processo.turnaround();
            this.resposta = processo.resposta();
            this.termino = processo.termino;
        }
    }

    private final String titulo;
    private final String descricao;
    private final List<String> eventos;
    private final Map<String, String> resumo;
    private final List<Faixa> linhaTempo;
    private final List<Metrica> metricas;

    ResultadoEscalonamento(
            String titulo,
            String descricao,
            List<String> eventos,
            Map<String, String> resumo,
            List<Faixa> linhaTempo,
            List<Metrica> metricas) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.eventos = new ArrayList<>(eventos);
        this.resumo = new LinkedHashMap<>(resumo);
        this.linhaTempo = new ArrayList<>(linhaTempo);
        this.metricas = new ArrayList<>(metricas);
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
        int indice = 0;
        for (Map.Entry<String, String> item : resumo.entrySet()) {
            if (indice++ > 0) json.append(',');
            texto(json, item.getKey()).append(':');
            texto(json, item.getValue());
        }

        json.append("},\"linhaTempo\":[");
        for (int i = 0; i < linhaTempo.size(); i++) {
            if (i > 0) json.append(',');
            Faixa faixa = linhaTempo.get(i);
            json.append('{');
            numero(json, "inicio", faixa.inicio).append(',');
            numero(json, "fim", faixa.fim).append(',');
            campo(json, "processo", faixa.processo).append('}');
        }

        json.append("],\"processos\":[");
        for (int i = 0; i < metricas.size(); i++) {
            if (i > 0) json.append(',');
            Metrica metrica = metricas.get(i);
            json.append('{');
            campo(json, "id", metrica.processo).append(',');
            numero(json, "chegada", metrica.chegada).append(',');
            numero(json, "cpu", metrica.cpu).append(',');
            numero(json, "prioridade", metrica.prioridade).append(',');
            numero(json, "espera", metrica.espera).append(',');
            numero(json, "turnaround", metrica.turnaround).append(',');
            numero(json, "resposta", metrica.resposta).append(',');
            numero(json, "termino", metrica.termino).append('}');
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
