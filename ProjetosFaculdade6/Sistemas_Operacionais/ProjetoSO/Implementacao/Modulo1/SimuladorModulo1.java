package miniso.modulo1;

/** Ponto de entrada único das simulações do Módulo 1. */
public final class SimuladorModulo1 {
    private SimuladorModulo1() {
    }

    public static ResultadoSimulacao executar(String simulacao) throws InterruptedException {
        switch (simulacao) {
            case "produtor-consumidor":
                return new ProdutorConsumidor().executar();
            case "filosofos":
                return new JantarFilosofos().executar();
            case "leitores-escritores":
                return new LeitoresEscritores().executar();
            default:
                throw new IllegalArgumentException("Simulação desconhecida: " + simulacao);
        }
    }
}
