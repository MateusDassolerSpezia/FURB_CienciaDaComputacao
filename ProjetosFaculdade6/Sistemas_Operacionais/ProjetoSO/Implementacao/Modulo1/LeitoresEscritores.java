package miniso.modulo1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** Leitores concorrentes e escritores exclusivos, usando uma fila justa. */
public final class LeitoresEscritores {
    private static final int LEITORES = 3;
    private static final int ESCRITORES = 2;
    private static final int RODADAS = 3;

    private final ReentrantReadWriteLock acesso = new ReentrantReadWriteLock(true);
    private final RegistroEventos registro = new RegistroEventos();
    private final Queue<Throwable> erros = new ConcurrentLinkedQueue<>();
    private final CountDownLatch largada = new CountDownLatch(1);
    private final AtomicInteger leituras = new AtomicInteger();
    private final AtomicInteger escritas = new AtomicInteger();
    private int dadoCompartilhado;

    public ResultadoSimulacao executar() throws InterruptedException {
        registro.adicionar("Recurso compartilhado iniciado com valor 0 e política de acesso justa.");
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= LEITORES; i++) {
            final int id = i;
            threads.add(novaThread("Leitor " + id, () -> ler(id)));
        }
        for (int i = 1; i <= ESCRITORES; i++) {
            final int id = i;
            threads.add(novaThread("Escritor " + id, () -> escrever(id)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        largada.countDown();
        ControleThreads.aguardar(threads, erros);

        boolean completo = leituras.get() == LEITORES * RODADAS
                && escritas.get() == ESCRITORES * RODADAS
                && dadoCompartilhado == ESCRITORES * RODADAS;
        registro.adicionar("Execução encerrada; leitores e escritores obtiveram acesso ao recurso.");

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Leituras", String.valueOf(leituras.get()));
        resumo.put("Escritas", String.valueOf(escritas.get()));
        resumo.put("Valor final", String.valueOf(dadoCompartilhado));
        resumo.put("Ausência de starvation", completo ? "Confirmada nesta execução" : "Não confirmada");
        resumo.put("Política", "ReadWriteLock justo (FIFO aproximado)");

        return new ResultadoSimulacao(
                "Leitores e Escritores",
                "Vários leitores podem acessar juntos; escritores recebem acesso exclusivo e não são ultrapassados indefinidamente.",
                registro.copiar(),
                resumo);
    }

    private void ler(int id) throws InterruptedException {
        largada.await();
        for (int rodada = 0; rodada < RODADAS; rodada++) {
            acesso.readLock().lockInterruptibly();
            try {
                leituras.incrementAndGet();
                registro.adicionar("Leitor " + id + " consultou o valor " + dadoCompartilhado + ".");
                ControleThreads.pausa(7L);
            } finally {
                acesso.readLock().unlock();
            }
            ControleThreads.pausa(5L + id);
        }
    }

    private void escrever(int id) throws InterruptedException {
        largada.await();
        for (int rodada = 0; rodada < RODADAS; rodada++) {
            acesso.writeLock().lockInterruptibly();
            try {
                dadoCompartilhado++;
                escritas.incrementAndGet();
                registro.adicionar("Escritor " + id + " atualizou o valor para " + dadoCompartilhado + ".");
                ControleThreads.pausa(9L);
            } finally {
                acesso.writeLock().unlock();
            }
            ControleThreads.pausa(6L + id);
        }
    }

    private Thread novaThread(String nome, Tarefa tarefa) {
        return new Thread(() -> {
            try {
                tarefa.executar();
            } catch (Throwable erro) {
                erros.add(erro);
                Thread.currentThread().interrupt();
            }
        }, nome);
    }

    @FunctionalInterface
    private interface Tarefa {
        void executar() throws InterruptedException;
    }
}
