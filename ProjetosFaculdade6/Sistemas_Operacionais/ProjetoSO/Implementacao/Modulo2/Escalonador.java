package miniso.modulo2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** Simulador discreto de CPU com estados de processo e operações de E/S. */
final class Escalonador {
    enum Algoritmo {
        ROUND_ROBIN, PRIORIDADE_AGING
    }

    private static final int INTERVALO_AGING = 3;
    private static final int LIMITE_TEMPO = 1_000;

    private final Algoritmo algoritmo;
    private final int quantum;
    private final List<Processo> processos = criarCarga();
    private final Deque<Processo> filaRoundRobin = new ArrayDeque<>();
    private final List<Processo> filaPrioridade = new ArrayList<>();
    private final List<String> eventos = new ArrayList<>();
    private final List<ResultadoEscalonamento.Faixa> linhaTempo = new ArrayList<>();
    private int proximaOrdem;
    private int preempcoes;
    private int bloqueios;

    Escalonador(Algoritmo algoritmo, int quantum) {
        this.algoritmo = algoritmo;
        this.quantum = quantum;
    }

    ResultadoEscalonamento executar() {
        int tempo = 0;
        int concluidos = 0;
        int quantumUsado = 0;
        Processo executando = null;

        registrar(tempo, "Simulação iniciada com quatro processos no estado NOVO.");
        while (concluidos < processos.size()) {
            if (tempo > LIMITE_TEMPO) {
                throw new IllegalStateException("O escalonador excedeu o limite de tempo simulado.");
            }

            admitirChegadas(tempo);
            concluirIo(tempo);

            if (executando == null) {
                executando = proximoProcesso();
                quantumUsado = 0;
                if (executando != null) {
                    executando.estado = Processo.Estado.EXECUTANDO;
                    if (executando.primeiraExecucao < 0) executando.primeiraExecucao = tempo;
                    if (algoritmo == Algoritmo.ROUND_ROBIN) {
                        registrar(tempo, executando.id + " passou de PRONTO para EXECUTANDO (quantum " + quantum + ").");
                    } else {
                        registrar(tempo, executando.id + " passou de PRONTO para EXECUTANDO (prioridade efetiva "
                                + executando.prioridadeEfetiva(INTERVALO_AGING) + ").");
                    }
                }
            }

            incrementarEspera();
            if (executando == null) {
                adicionarFaixa(tempo, tempo + 1, "OCIOSA");
                tempo++;
                continue;
            }

            adicionarFaixa(tempo, tempo + 1, executando.id);
            executando.restanteRajada--;
            executando.tempoCpu++;
            quantumUsado++;
            tempo++;

            if (executando.restanteRajada == 0) {
                if (executando.indiceRajada < executando.rajadasCpu.length - 1) {
                    int duracaoIo = executando.duracoesIo[executando.indiceRajada];
                    executando.indiceRajada++;
                    executando.restanteRajada = executando.rajadasCpu[executando.indiceRajada];
                    executando.desbloqueioEm = tempo + duracaoIo;
                    executando.estado = Processo.Estado.BLOQUEADO;
                    bloqueios++;
                    registrar(tempo, executando.id + " passou de EXECUTANDO para BLOQUEADO por E/S até t="
                            + executando.desbloqueioEm + ".");
                } else {
                    executando.estado = Processo.Estado.FINALIZADO;
                    executando.termino = tempo;
                    concluidos++;
                    registrar(tempo, executando.id + " passou de EXECUTANDO para FINALIZADO.");
                }
                executando = null;
                quantumUsado = 0;
            } else if (algoritmo == Algoritmo.ROUND_ROBIN && quantumUsado == quantum) {
                executando.estado = Processo.Estado.PRONTO;
                adicionarPronto(executando);
                preempcoes++;
                registrar(tempo, executando.id + " foi preemptado e voltou para PRONTO ao fim do quantum.");
                executando = null;
                quantumUsado = 0;
            }
        }

        registrar(tempo, "Todos os processos estão FINALIZADOS; CPU liberada.");
        return montarResultado(tempo);
    }

    private void admitirChegadas(int tempo) {
        for (Processo processo : processos) {
            if (processo.estado == Processo.Estado.NOVO && processo.chegada == tempo) {
                processo.estado = Processo.Estado.PRONTO;
                adicionarPronto(processo);
                registrar(tempo, processo.id + " chegou e passou de NOVO para PRONTO.");
            }
        }
    }

    private void concluirIo(int tempo) {
        for (Processo processo : processos) {
            if (processo.estado == Processo.Estado.BLOQUEADO && processo.desbloqueioEm == tempo) {
                processo.estado = Processo.Estado.PRONTO;
                adicionarPronto(processo);
                registrar(tempo, processo.id + " concluiu E/S e passou de BLOQUEADO para PRONTO.");
            }
        }
    }

    private void adicionarPronto(Processo processo) {
        processo.ordemPronto = proximaOrdem++;
        if (algoritmo == Algoritmo.ROUND_ROBIN) filaRoundRobin.addLast(processo);
        else filaPrioridade.add(processo);
    }

    private Processo proximoProcesso() {
        if (algoritmo == Algoritmo.ROUND_ROBIN) return filaRoundRobin.pollFirst();
        Processo escolhido = filaPrioridade.stream()
                .min(Comparator.comparingInt((Processo p) -> p.prioridadeEfetiva(INTERVALO_AGING))
                        .thenComparingInt(p -> p.ordemPronto)
                        .thenComparing(p -> p.id))
                .orElse(null);
        filaPrioridade.remove(escolhido);
        return escolhido;
    }

    private void incrementarEspera() {
        if (algoritmo == Algoritmo.ROUND_ROBIN) {
            for (Processo processo : filaRoundRobin) processo.tempoEspera++;
        } else {
            for (Processo processo : filaPrioridade) processo.tempoEspera++;
        }
    }

    private void adicionarFaixa(int inicio, int fim, String processo) {
        if (!linhaTempo.isEmpty()) {
            ResultadoEscalonamento.Faixa ultima = linhaTempo.get(linhaTempo.size() - 1);
            if (ultima.processo.equals(processo) && ultima.fim == inicio) {
                ultima.fim = fim;
                return;
            }
        }
        linhaTempo.add(new ResultadoEscalonamento.Faixa(inicio, fim, processo));
    }

    private ResultadoEscalonamento montarResultado(int tempoTotal) {
        double somaEspera = 0;
        double somaTurnaround = 0;
        double somaResposta = 0;
        List<ResultadoEscalonamento.Metrica> metricas = new ArrayList<>();
        for (Processo processo : processos) {
            somaEspera += processo.tempoEspera;
            somaTurnaround += processo.turnaround();
            somaResposta += processo.resposta();
            metricas.add(new ResultadoEscalonamento.Metrica(processo));
        }

        String nome = algoritmo == Algoritmo.ROUND_ROBIN ? "Round-Robin" : "Prioridades com Aging";
        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Algoritmo", nome);
        if (algoritmo == Algoritmo.ROUND_ROBIN) resumo.put("Quantum", quantum + " u.t.");
        else resumo.put("Intervalo de aging", INTERVALO_AGING + " u.t.");
        resumo.put("Tempo médio de espera (TME)", decimal(somaEspera / processos.size()) + " u.t.");
        resumo.put("Tempo médio de turnaround (TMT)", decimal(somaTurnaround / processos.size()) + " u.t.");
        resumo.put("Tempo médio de resposta", decimal(somaResposta / processos.size()) + " u.t.");
        resumo.put("Tempo total", tempoTotal + " u.t.");
        resumo.put("Preempções", String.valueOf(preempcoes));
        resumo.put("Bloqueios por E/S", String.valueOf(bloqueios));

        String descricao = algoritmo == Algoritmo.ROUND_ROBIN
                ? "Fila circular preemptiva: cada processo recebe no máximo " + quantum + " unidades de CPU por vez."
                : "O próximo processo é escolhido pela menor prioridade numérica; o aging melhora a prioridade de quem espera.";
        return new ResultadoEscalonamento(nome, descricao, eventos, resumo, linhaTempo, metricas);
    }

    private void registrar(int tempo, String mensagem) {
        eventos.add(String.format("t=%02d | %s", tempo, mensagem));
    }

    private static String decimal(double valor) {
        return String.format(Locale.US, "%.2f", valor).replace('.', ',');
    }

    private static List<Processo> criarCarga() {
        List<Processo> carga = new ArrayList<>();
        carga.add(new Processo("P1", 0, 2, new int[]{4, 3}, new int[]{3}));
        carga.add(new Processo("P2", 1, 1, new int[]{5}, new int[]{}));
        carga.add(new Processo("P3", 2, 4, new int[]{2, 4}, new int[]{2}));
        carga.add(new Processo("P4", 4, 3, new int[]{4}, new int[]{}));
        return carga;
    }
}
