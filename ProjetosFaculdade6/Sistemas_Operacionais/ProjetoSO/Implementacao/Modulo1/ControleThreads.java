package miniso.modulo1;

import java.util.List;
import java.util.Queue;

final class ControleThreads {
    private ControleThreads() {
    }

    static void aguardar(List<Thread> threads, Queue<Throwable> erros) throws InterruptedException {
        long limite = System.currentTimeMillis() + 10_000L;
        for (Thread thread : threads) {
            long restante = limite - System.currentTimeMillis();
            if (restante > 0) {
                thread.join(restante);
            }
        }

        for (Thread thread : threads) {
            if (thread.isAlive()) {
                thread.interrupt();
                throw new IllegalStateException("A simulação excedeu o tempo limite: " + thread.getName());
            }
        }

        if (!erros.isEmpty()) {
            Throwable erro = erros.peek();
            throw new IllegalStateException("Falha em uma thread da simulação: " + erro.getMessage(), erro);
        }
    }

    static void pausa(long milissegundos) throws InterruptedException {
        Thread.sleep(milissegundos);
    }
}
