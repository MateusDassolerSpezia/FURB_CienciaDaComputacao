package miniso.modulo4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Resultado serializável dos experimentos de deadlock. */
public final class ResultadoDeadlock {
    static final class DadosBanqueiro {
        final String[] processos;
        final String[] recursos;
        final int[][] alocacao;
        final int[][] maximo;
        final int[][] necessidade;
        final int[] disponivel;
        final String solicitante;
        final int[] solicitacao;
        final boolean aprovada;
        final boolean estadoSeguro;
        final List<String> sequenciaSegura;
        final List<String> impedidos;

        DadosBanqueiro(String[] processos, String[] recursos, int[][] alocacao, int[][] maximo,
                       int[][] necessidade, int[] disponivel, String solicitante, int[] solicitacao,
                       boolean aprovada, boolean estadoSeguro, List<String> sequenciaSegura,
                       List<String> impedidos) {
            this.processos = processos.clone();
            this.recursos = recursos.clone();
            this.alocacao = copiar(alocacao);
            this.maximo = copiar(maximo);
            this.necessidade = copiar(necessidade);
            this.disponivel = disponivel.clone();
            this.solicitante = solicitante;
            this.solicitacao = solicitacao.clone();
            this.aprovada = aprovada;
            this.estadoSeguro = estadoSeguro;
            this.sequenciaSegura = new ArrayList<>(sequenciaSegura);
            this.impedidos = new ArrayList<>(impedidos);
        }
    }

    static final class Aresta {
        final String origem;
        final String destino;
        final String tipo;

        Aresta(String origem, String destino, String tipo) {
            this.origem = origem;
            this.destino = destino;
            this.tipo = tipo;
        }
    }

    static final class DadosGrafo {
        final List<String> processos;
        final List<String> recursos;
        final List<Aresta> arestas;
        final List<String> ciclo;
        final boolean deadlock;

        DadosGrafo(List<String> processos, List<String> recursos, List<Aresta> arestas,
                   List<String> ciclo, boolean deadlock) {
            this.processos = new ArrayList<>(processos);
            this.recursos = new ArrayList<>(recursos);
            this.arestas = new ArrayList<>(arestas);
            this.ciclo = new ArrayList<>(ciclo);
            this.deadlock = deadlock;
        }
    }

    private final String tipo;
    private final String titulo;
    private final String descricao;
    private final List<String> eventos;
    private final Map<String, String> resumo;
    private final DadosBanqueiro banqueiro;
    private final DadosGrafo grafo;

    ResultadoDeadlock(String tipo, String titulo, String descricao, List<String> eventos,
                      Map<String, String> resumo, DadosBanqueiro banqueiro, DadosGrafo grafo) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.eventos = new ArrayList<>(eventos);
        this.resumo = new LinkedHashMap<>(resumo);
        this.banqueiro = banqueiro;
        this.grafo = grafo;
    }

    public String paraJson() {
        StringBuilder json = new StringBuilder("{");
        campo(json, "tipo", tipo).append(',');
        campo(json, "titulo", titulo).append(',');
        campo(json, "descricao", descricao).append(',');
        json.append("\"eventos\":");
        listaTextos(json, eventos).append(',');
        json.append("\"resumo\":{");
        int indice = 0;
        for (Map.Entry<String, String> item : resumo.entrySet()) {
            if (indice++ > 0) json.append(',');
            texto(json, item.getKey()).append(':');
            texto(json, item.getValue());
        }
        json.append('}');
        if (banqueiro != null) json.append(",\"banqueiro\":").append(banqueiroJson());
        if (grafo != null) json.append(",\"grafo\":").append(grafoJson());
        return json.append('}').toString();
    }

    private String banqueiroJson() {
        StringBuilder json = new StringBuilder("{");
        json.append("\"processos\":");
        listaTextos(json, java.util.Arrays.asList(banqueiro.processos)).append(',');
        json.append("\"recursos\":");
        listaTextos(json, java.util.Arrays.asList(banqueiro.recursos)).append(',');
        json.append("\"alocacao\":"); matriz(json, banqueiro.alocacao).append(',');
        json.append("\"maximo\":"); matriz(json, banqueiro.maximo).append(',');
        json.append("\"necessidade\":"); matriz(json, banqueiro.necessidade).append(',');
        json.append("\"disponivel\":"); vetor(json, banqueiro.disponivel).append(',');
        campo(json, "solicitante", banqueiro.solicitante).append(',');
        json.append("\"solicitacao\":"); vetor(json, banqueiro.solicitacao).append(',');
        json.append("\"aprovada\":").append(banqueiro.aprovada).append(',');
        json.append("\"estadoSeguro\":").append(banqueiro.estadoSeguro).append(',');
        json.append("\"sequenciaSegura\":"); listaTextos(json, banqueiro.sequenciaSegura).append(',');
        json.append("\"impedidos\":"); listaTextos(json, banqueiro.impedidos);
        return json.append('}').toString();
    }

    private String grafoJson() {
        StringBuilder json = new StringBuilder("{");
        json.append("\"processos\":"); listaTextos(json, grafo.processos).append(',');
        json.append("\"recursos\":"); listaTextos(json, grafo.recursos).append(',');
        json.append("\"arestas\":[");
        for (int i = 0; i < grafo.arestas.size(); i++) {
            if (i > 0) json.append(',');
            Aresta aresta = grafo.arestas.get(i);
            json.append('{');
            campo(json, "origem", aresta.origem).append(',');
            campo(json, "destino", aresta.destino).append(',');
            campo(json, "tipo", aresta.tipo).append('}');
        }
        json.append("],\"ciclo\":"); listaTextos(json, grafo.ciclo).append(',');
        json.append("\"deadlock\":").append(grafo.deadlock);
        return json.append('}').toString();
    }

    private static StringBuilder matriz(StringBuilder json, int[][] matriz) {
        json.append('[');
        for (int i = 0; i < matriz.length; i++) {
            if (i > 0) json.append(',');
            vetor(json, matriz[i]);
        }
        return json.append(']');
    }

    private static StringBuilder vetor(StringBuilder json, int[] vetor) {
        json.append('[');
        for (int i = 0; i < vetor.length; i++) {
            if (i > 0) json.append(',');
            json.append(vetor[i]);
        }
        return json.append(']');
    }

    private static StringBuilder listaTextos(StringBuilder json, List<String> valores) {
        json.append('[');
        for (int i = 0; i < valores.size(); i++) {
            if (i > 0) json.append(',');
            texto(json, valores.get(i));
        }
        return json.append(']');
    }

    private static StringBuilder campo(StringBuilder json, String nome, String valor) {
        texto(json, nome).append(':');
        return texto(json, valor);
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

    private static int[][] copiar(int[][] origem) {
        int[][] copia = new int[origem.length][];
        for (int i = 0; i < origem.length; i++) copia[i] = origem[i].clone();
        return copia;
    }
}
