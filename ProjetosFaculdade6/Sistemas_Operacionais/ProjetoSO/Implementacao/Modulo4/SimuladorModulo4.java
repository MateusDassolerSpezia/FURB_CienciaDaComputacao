package miniso.modulo4;

/** Fachada dos experimentos de prevenção e detecção de deadlocks. */
public final class SimuladorModulo4 {
    private SimuladorModulo4() {
    }

    public static ResultadoDeadlock executar(String experimento, String cenario) {
        if ("banqueiro".equals(experimento)) {
            if ("seguro".equals(cenario)) return new AlgoritmoBanqueiro().executar(true);
            if ("inseguro".equals(cenario)) return new AlgoritmoBanqueiro().executar(false);
            throw new IllegalArgumentException("Cenário do Banqueiro desconhecido: " + cenario);
        }
        if ("grafo".equals(experimento)) {
            if ("com-ciclo".equals(cenario)) return new GrafoAlocacao().executar(true);
            if ("sem-ciclo".equals(cenario)) return new GrafoAlocacao().executar(false);
            throw new IllegalArgumentException("Cenário do grafo desconhecido: " + cenario);
        }
        throw new IllegalArgumentException("Experimento desconhecido: " + experimento);
    }
}
