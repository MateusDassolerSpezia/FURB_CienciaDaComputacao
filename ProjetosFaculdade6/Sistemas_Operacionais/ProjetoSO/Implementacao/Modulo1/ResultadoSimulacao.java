package miniso.modulo1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Resultado imutável que será enviado para a interface web. */
public final class ResultadoSimulacao {
    private final String titulo;
    private final String descricao;
    private final List<String> eventos;
    private final Map<String, String> resumo;

    public ResultadoSimulacao(
            String titulo,
            String descricao,
            List<String> eventos,
            Map<String, String> resumo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.eventos = new ArrayList<>(eventos);
        this.resumo = new LinkedHashMap<>(resumo);
    }

    public String paraJson() {
        StringBuilder json = new StringBuilder();
        json.append('{');
        campo(json, "titulo", titulo).append(',');
        campo(json, "descricao", descricao).append(',');

        json.append("\"eventos\":[");
        for (int i = 0; i < eventos.size(); i++) {
            if (i > 0) {
                json.append(',');
            }
            texto(json, eventos.get(i));
        }
        json.append("],\"resumo\":{");

        int indice = 0;
        for (Map.Entry<String, String> item : resumo.entrySet()) {
            if (indice++ > 0) {
                json.append(',');
            }
            texto(json, item.getKey()).append(':');
            texto(json, item.getValue());
        }
        return json.append("}}").toString();
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
                    if (caractere < 0x20) {
                        json.append(String.format("\\u%04x", (int) caractere));
                    } else {
                        json.append(caractere);
                    }
            }
        }
        return json.append('"');
    }
}
