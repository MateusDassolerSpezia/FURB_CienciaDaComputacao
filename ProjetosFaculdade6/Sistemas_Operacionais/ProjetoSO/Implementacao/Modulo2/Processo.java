package miniso.modulo2;

/** Estado mutável de um processo durante uma simulação de escalonamento. */
final class Processo {
    enum Estado {
        NOVO, PRONTO, EXECUTANDO, BLOQUEADO, FINALIZADO
    }

    final String id;
    final int chegada;
    final int prioridadeInicial;
    final int[] rajadasCpu;
    final int[] duracoesIo;

    Estado estado = Estado.NOVO;
    int indiceRajada;
    int restanteRajada;
    int desbloqueioEm = -1;
    int tempoEspera;
    int tempoCpu;
    int termino = -1;
    int primeiraExecucao = -1;
    int ordemPronto;

    Processo(String id, int chegada, int prioridadeInicial, int[] rajadasCpu, int[] duracoesIo) {
        this.id = id;
        this.chegada = chegada;
        this.prioridadeInicial = prioridadeInicial;
        this.rajadasCpu = rajadasCpu.clone();
        this.duracoesIo = duracoesIo.clone();
        this.restanteRajada = rajadasCpu[0];
    }

    int prioridadeEfetiva(int intervaloAging) {
        return Math.max(0, prioridadeInicial - tempoEspera / intervaloAging);
    }

    int turnaround() {
        return termino - chegada;
    }

    int resposta() {
        return primeiraExecucao - chegada;
    }
}
