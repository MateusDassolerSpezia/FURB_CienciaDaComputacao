package miniso.modulo1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/** Buffer circular limitado coordenado por dois semáforos e um mutex. */
public final class ProdutorConsumidor {
    private static final int CAPACIDADE = 5;
    private static final int TOTAL_ITENS = 12;

    private final int[] buffer = new int[CAPACIDADE];
    private final Semaphore vazios = new Semaphore(CAPACIDADE, true);
    private final Semaphore cheios = new Semaphore(0, true);
    private final ReentrantLock mutex = new ReentrantLock(true);
    private final RegistroEventos registro = new RegistroEventos();
    private final AtomicInteger proximoItem = new AtomicInteger(1);
    private final AtomicInteger maiorOcupacao = new AtomicInteger();
    private final Queue<Throwable> erros = new ConcurrentLinkedQueue<>();

    private int entrada;
    private int saida;
    private int quantidade;
    private int produzidos;
    private int consumidos;

    public ResultadoSimulacao executar() throws InterruptedException {
        registro.adicionar("Buffer circular criado com capacidade para " + CAPACIDADE + " itens.");

        List<Thread> threads = new ArrayList<>();
        threads.add(novaThread("Produtor 1", () -> produzir("Produtor 1", TOTAL_ITENS / 2)));
        threads.add(novaThread("Produtor 2", () -> produzir("Produtor 2", TOTAL_ITENS / 2)));
        threads.add(novaThread("Consumidor 1", () -> consumir("Consumidor 1", TOTAL_ITENS / 2)));
        threads.add(novaThread("Consumidor 2", () -> consumir("Consumidor 2", TOTAL_ITENS / 2)));

        for (Thread thread : threads) {
            thread.start();
        }
        ControleThreads.aguardar(threads, erros);

        boolean integro = produzidos == TOTAL_ITENS && consumidos == TOTAL_ITENS && quantidade == 0;
        registro.adicionar(integro
                ? "Validação concluída: todos os itens foram consumidos e o buffer terminou vazio."
                : "Validação falhou: o estado final do buffer é inconsistente.");

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Itens produzidos", String.valueOf(produzidos));
        resumo.put("Itens consumidos", String.valueOf(consumidos));
        resumo.put("Maior ocupação", maiorOcupacao.get() + "/" + CAPACIDADE);
        resumo.put("Estado final", integro ? "Íntegro — buffer vazio" : "Inconsistente");
        resumo.put("Sincronização", "Semáforos vazio/cheio + mutex justo");

        return new ResultadoSimulacao(
                "Produtor e Consumidor",
                "Dois produtores e dois consumidores compartilham um buffer circular sem espera ocupada.",
                registro.copiar(),
                resumo);
    }

    private void produzir(String nome, int repeticoes) throws InterruptedException {
        for (int i = 0; i < repeticoes; i++) {
            int item = proximoItem.getAndIncrement();
            vazios.acquire();
            boolean inserido = false;
            try {
                mutex.lockInterruptibly();
                try {
                    buffer[entrada] = item;
                    entrada = (entrada + 1) % CAPACIDADE;
                    quantidade++;
                    produzidos++;
                    maiorOcupacao.accumulateAndGet(quantidade, Math::max);
                    registro.adicionar(nome + " produziu o item " + item + " (ocupação: " + quantidade + ").");
                    inserido = true;
                } finally {
                    mutex.unlock();
                }
            } finally {
                if (inserido) {
                    cheios.release();
                } else {
                    vazios.release();
                }
            }
            ControleThreads.pausa(8L + (item % 3) * 3L);
        }
    }

    private void consumir(String nome, int repeticoes) throws InterruptedException {
        for (int i = 0; i < repeticoes; i++) {
            cheios.acquire();
            boolean removido = false;
            int item = -1;
            try {
                mutex.lockInterruptibly();
                try {
                    item = buffer[saida];
                    saida = (saida + 1) % CAPACIDADE;
                    quantidade--;
                    consumidos++;
                    registro.adicionar(nome + " consumiu o item " + item + " (ocupação: " + quantidade + ").");
                    removido = true;
                } finally {
                    mutex.unlock();
                }
            } finally {
                if (removido) {
                    vazios.release();
                } else {
                    cheios.release();
                }
            }
            ControleThreads.pausa(11L + (item % 2) * 4L);
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
