package miniso.modulo2;

/** Fachada das estratégias de escalonamento disponíveis no Módulo 2. */
public final class SimuladorModulo2 {
    private SimuladorModulo2() {
    }

    public static ResultadoEscalonamento executar(String algoritmo, int quantum) {
        if ("round-robin".equals(algoritmo)) {
            if (quantum < 1 || quantum > 10) {
                throw new IllegalArgumentException("O quantum deve estar entre 1 e 10.");
            }
            return new Escalonador(Escalonador.Algoritmo.ROUND_ROBIN, quantum).executar();
        }
        if ("prioridade-aging".equals(algoritmo)) {
            return new Escalonador(Escalonador.Algoritmo.PRIORIDADE_AGING, quantum).executar();
        }
        throw new IllegalArgumentException("Algoritmo desconhecido: " + algoritmo);
    }
}
