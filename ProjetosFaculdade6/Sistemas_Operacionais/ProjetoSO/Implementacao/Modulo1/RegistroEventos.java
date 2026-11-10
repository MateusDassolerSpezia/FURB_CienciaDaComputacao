package miniso.modulo1;

import java.util.ArrayList;
import java.util.List;

/** Mantém o log legível mesmo quando várias threads escrevem ao mesmo tempo. */
final class RegistroEventos {
    private final List<String> eventos = new ArrayList<>();
    private int sequencia;

    synchronized void adicionar(String mensagem) {
        eventos.add(String.format("%03d | %s", ++sequencia, mensagem));
    }

    synchronized List<String> copiar() {
        return new ArrayList<>(eventos);
    }
}
