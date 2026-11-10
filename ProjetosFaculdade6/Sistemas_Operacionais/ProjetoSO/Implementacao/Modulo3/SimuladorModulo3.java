package miniso.modulo3;

/** Fachada dos algoritmos de substituição de páginas do Módulo 3. */
public final class SimuladorModulo3 {
    private SimuladorModulo3() {
    }

    public static ResultadoMemoria executar(String algoritmo, int quadros) {
        if (quadros < 2 || quadros > 6) {
            throw new IllegalArgumentException("A quantidade de quadros deve estar entre 2 e 6.");
        }

        MMUSimulada.Algoritmo selecionado;
        if ("fifo".equals(algoritmo)) selecionado = MMUSimulada.Algoritmo.FIFO;
        else if ("lru".equals(algoritmo)) selecionado = MMUSimulada.Algoritmo.LRU;
        else throw new IllegalArgumentException("Algoritmo desconhecido: " + algoritmo);

        return new MMUSimulada(selecionado, quadros).executar();
    }
}
