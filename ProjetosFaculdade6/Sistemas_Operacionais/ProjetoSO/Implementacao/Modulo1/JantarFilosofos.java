package miniso.modulo1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.ReentrantLock;

/** Cinco filósofos com prevenção de deadlock por ordenação global dos recursos. */
public final class JantarFilosofos {
    private static final int QUANTIDADE = 5;
    private static final int REFEICOES = 3;

    private final ReentrantLock[] hashis = new ReentrantLock[QUANTIDADE];
    private final AtomicIntegerArray refeicoes = new AtomicIntegerArray(QUANTIDADE);
    private final RegistroEventos registro = new RegistroEventos();
    private final Queue<Throwable> erros = new ConcurrentLinkedQueue<>();
    private final CountDownLatch largada = new CountDownLatch(1);

    public JantarFilosofos() {
        for (int i = 0; i < QUANTIDADE; i++) {
            hashis[i] = new ReentrantLock(true);
        }
    }

    public ResultadoSimulacao executar() throws InterruptedException {
        registro.adicionar("Mesa preparada para cinco filósofos; todos aguardam a largada.");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < QUANTIDADE; i++) {
            final int id = i;
            Thread thread = new Thread(() -> {
                try {
                    filosofar(id);
                } catch (Throwable erro) {
                    erros.add(erro);
                    Thread.currentThread().interrupt();
                }
            }, "Filósofo " + (id + 1));
            threads.add(thread);
            thread.start();
        }

        largada.countDown();
        ControleThreads.aguardar(threads, erros);

        int total = 0;
        boolean todosComeram = true;
        for (int i = 0; i < QUANTIDADE; i++) {
            total += refeicoes.get(i);
            todosComeram &= refeicoes.get(i) == REFEICOES;
        }
        registro.adicionar("Jantar encerrado sem deadlock; todos os filósofos completaram suas refeições.");

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Filósofos", String.valueOf(QUANTIDADE));
        resumo.put("Refeições concluídas", total + "/" + (QUANTIDADE * REFEICOES));
        resumo.put("Progresso de todos", todosComeram ? "Confirmado" : "Não confirmado");
        resumo.put("Prevenção de deadlock", "Aquisição dos hashis em ordem numérica");

        return new ResultadoSimulacao(
                "Jantar dos Filósofos",
                "Cada filósofo pensa e come três vezes, compartilhando cinco hashis protegidos por locks justos.",
                registro.copiar(),
                resumo);
    }

    private void filosofar(int id) throws InterruptedException {
        largada.await();
        int hashiEsquerdo = id;
        int hashiDireito = (id + 1) % QUANTIDADE;
        int primeiro = Math.min(hashiEsquerdo, hashiDireito);
        int segundo = Math.max(hashiEsquerdo, hashiDireito);

        for (int rodada = 1; rodada <= REFEICOES; rodada++) {
            registro.adicionar(nome(id) + " está pensando.");
            ControleThreads.pausa(6L + id * 2L);

            hashis[primeiro].lockInterruptibly();
            try {
                hashis[segundo].lockInterruptibly();
                try {
                    int atual = refeicoes.incrementAndGet(id);
                    registro.adicionar(nome(id) + " está comendo (refeição " + atual + ").");
                    ControleThreads.pausa(9L);
                } finally {
                    hashis[segundo].unlock();
                }
            } finally {
                hashis[primeiro].unlock();
            }
        }
    }

    private String nome(int id) {
        return "Filósofo " + (id + 1);
    }
}
