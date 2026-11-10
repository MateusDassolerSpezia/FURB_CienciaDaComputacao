package miniso.modulo5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Estado final serializável do sistema de arquivos FAT. */
public final class ResultadoSistemaArquivos {
    static final class Bloco {
        final int numero;
        final String estado;
        final String proximo;
        final String proprietario;

        Bloco(int numero, String estado, String proximo, String proprietario) {
            this.numero = numero;
            this.estado = estado;
            this.proximo = proximo;
            this.proprietario = proprietario;
        }
    }

    static final class Entrada {
        final String caminho;
        final String tipo;
        final int tamanho;
        final int primeiroBloco;
        final List<Integer> cadeia;

        Entrada(String caminho, String tipo, int tamanho, int primeiroBloco, List<Integer> cadeia) {
            this.caminho = caminho;
            this.tipo = tipo;
            this.tamanho = tamanho;
            this.primeiroBloco = primeiroBloco;
            this.cadeia = new ArrayList<>(cadeia);
        }
    }

    private final String titulo;
    private final String descricao;
    private final List<String> eventos;
    private final Map<String, String> resumo;
    private final List<Bloco> blocos;
    private final List<Entrada> entradas;
    private final boolean[] bitmap;

    ResultadoSistemaArquivos(String titulo, String descricao, List<String> eventos,
                             Map<String, String> resumo, List<Bloco> blocos,
                             List<Entrada> entradas, boolean[] bitmap) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.eventos = new ArrayList<>(eventos);
        this.resumo = new LinkedHashMap<>(resumo);
        this.blocos = new ArrayList<>(blocos);
        this.entradas = new ArrayList<>(entradas);
        this.bitmap = bitmap.clone();
    }

    public String paraJson() {
        StringBuilder json = new StringBuilder("{");
        campo(json, "titulo", titulo).append(',');
        campo(json, "descricao", descricao).append(',');
        json.append("\"eventos\":"); listaTextos(json, eventos).append(',');
        json.append("\"resumo\":{");
        int indice = 0;
        for (Map.Entry<String, String> item : resumo.entrySet()) {
            if (indice++ > 0) json.append(',');
            texto(json, item.getKey()).append(':'); texto(json, item.getValue());
        }

        json.append("},\"bitmap\":[");
        for (int i = 0; i < bitmap.length; i++) {
            if (i > 0) json.append(',');
            json.append(bitmap[i]);
        }

        json.append("],\"blocos\":[");
        for (int i = 0; i < blocos.size(); i++) {
            if (i > 0) json.append(',');
            Bloco bloco = blocos.get(i);
            json.append('{');
            numero(json, "numero", bloco.numero).append(',');
            campo(json, "estado", bloco.estado).append(',');
            campo(json, "proximo", bloco.proximo).append(',');
            campo(json, "proprietario", bloco.proprietario).append('}');
        }

        json.append("],\"entradas\":[");
        for (int i = 0; i < entradas.size(); i++) {
            if (i > 0) json.append(',');
            Entrada entrada = entradas.get(i);
            json.append('{');
            campo(json, "caminho", entrada.caminho).append(',');
            campo(json, "tipo", entrada.tipo).append(',');
            numero(json, "tamanho", entrada.tamanho).append(',');
            numero(json, "primeiroBloco", entrada.primeiroBloco).append(',');
            json.append("\"cadeia\":[");
            for (int bloco = 0; bloco < entrada.cadeia.size(); bloco++) {
                if (bloco > 0) json.append(',');
                json.append(entrada.cadeia.get(bloco));
            }
            json.append("]}");
        }
        return json.append("]}").toString();
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
