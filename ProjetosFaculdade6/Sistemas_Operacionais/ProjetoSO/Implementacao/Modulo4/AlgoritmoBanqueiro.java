package miniso.modulo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Algoritmo do Banqueiro de Dijkstra com concessão tentativa e teste de segurança. */
final class AlgoritmoBanqueiro {
    private static final String[] PROCESSOS = {"P0", "P1", "P2", "P3", "P4"};
    private static final String[] RECURSOS = {"A", "B", "C"};
    private static final int[][] ALOCACAO_INICIAL = {
            {0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1}, {0, 0, 2}
    };
    private static final int[][] MAXIMO = {
            {7, 5, 3}, {3, 2, 2}, {9, 0, 2}, {2, 2, 2}, {4, 3, 3}
    };
    private static final int[] DISPONIVEL_INICIAL = {3, 3, 2};

    ResultadoDeadlock executar(boolean solicitacaoSegura) {
        int processoSolicitante = solicitacaoSegura ? 1 : 4;
        int[] solicitacao = solicitacaoSegura ? new int[]{1, 0, 2} : new int[]{3, 3, 0};
        int[][] alocacao = copiar(ALOCACAO_INICIAL);
        int[][] necessidade = calcularNecessidade(alocacao);
        int[] disponivel = DISPONIVEL_INICIAL.clone();
        List<String> eventos = new ArrayList<>();

        eventos.add("Estado inicial: Available = " + vetor(disponivel) + ".");
        eventos.add(PROCESSOS[processoSolicitante] + " solicita " + vetor(solicitacao) + ".");
        validarSolicitacao(processoSolicitante, solicitacao, necessidade, disponivel);
        eventos.add("A solicitação não excede Need nem Available; a alocação é aplicada temporariamente.");

        for (int recurso = 0; recurso < RECURSOS.length; recurso++) {
            disponivel[recurso] -= solicitacao[recurso];
            alocacao[processoSolicitante][recurso] += solicitacao[recurso];
        }
        necessidade = calcularNecessidade(alocacao);

        Verificacao verificacao = verificarSeguranca(alocacao, necessidade, disponivel, eventos);
        boolean aprovada = verificacao.seguro;
        if (aprovada) {
            eventos.add("Estado seguro confirmado; a solicitação foi APROVADA e efetivada.");
        } else {
            eventos.add("Nenhum processo restante satisfaz Need ≤ Work; o estado tentativo é INSEGURO.");
            eventos.add("A solicitação foi NEGADA. O sistema real mantém a alocação anterior.");
        }

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Solicitante", PROCESSOS[processoSolicitante]);
        resumo.put("Solicitação", vetor(solicitacao));
        resumo.put("Decisão", aprovada ? "Aprovada" : "Negada");
        resumo.put("Estado tentativo", verificacao.seguro ? "Seguro" : "Inseguro");
        resumo.put("Sequência segura", verificacao.sequencia.isEmpty()
                ? "Não existe" : String.join(" → ", verificacao.sequencia));
        resumo.put("Processos impedidos", verificacao.impedidos.isEmpty()
                ? "Nenhum" : String.join(", ", verificacao.impedidos));

        ResultadoDeadlock.DadosBanqueiro dados = new ResultadoDeadlock.DadosBanqueiro(
                PROCESSOS, RECURSOS, alocacao, MAXIMO, necessidade, disponivel,
                PROCESSOS[processoSolicitante], solicitacao, aprovada, verificacao.seguro,
                verificacao.sequencia, verificacao.impedidos);
        String titulo = aprovada ? "Solicitação aprovada" : "Solicitação negada";
        String descricao = aprovada
                ? "O Banqueiro encontrou uma ordem em que todos os processos podem terminar e liberar seus recursos."
                : "A concessão criaria um estado inseguro; por isso, o Banqueiro preserva a alocação anterior.";
        return new ResultadoDeadlock("banqueiro", titulo, descricao, eventos, resumo, dados, null);
    }

    private Verificacao verificarSeguranca(int[][] alocacao, int[][] necessidade, int[] disponivel,
                                           List<String> eventos) {
        int[] work = disponivel.clone();
        boolean[] finalizado = new boolean[PROCESSOS.length];
        List<String> sequencia = new ArrayList<>();
        boolean progresso;

        do {
            progresso = false;
            for (int processo = 0; processo < PROCESSOS.length; processo++) {
                if (!finalizado[processo] && menorOuIgual(necessidade[processo], work)) {
                    eventos.add(PROCESSOS[processo] + ": Need " + vetor(necessidade[processo])
                            + " ≤ Work " + vetor(work) + "; pode finalizar.");
                    for (int recurso = 0; recurso < RECURSOS.length; recurso++) {
                        work[recurso] += alocacao[processo][recurso];
                    }
                    finalizado[processo] = true;
                    sequencia.add(PROCESSOS[processo]);
                    eventos.add("Recursos liberados por " + PROCESSOS[processo] + "; Work = " + vetor(work) + ".");
                    progresso = true;
                }
            }
        } while (progresso);

        List<String> impedidos = new ArrayList<>();
        for (int processo = 0; processo < PROCESSOS.length; processo++) {
            if (!finalizado[processo]) impedidos.add(PROCESSOS[processo]);
        }
        return new Verificacao(impedidos.isEmpty(), sequencia, impedidos);
    }

    private void validarSolicitacao(int processo, int[] solicitacao, int[][] necessidade, int[] disponivel) {
        if (!menorOuIgual(solicitacao, necessidade[processo])) {
            throw new IllegalArgumentException("A solicitação excede a necessidade máxima declarada.");
        }
        if (!menorOuIgual(solicitacao, disponivel)) {
            throw new IllegalArgumentException("A solicitação excede os recursos disponíveis.");
        }
    }

    private int[][] calcularNecessidade(int[][] alocacao) {
        int[][] necessidade = new int[PROCESSOS.length][RECURSOS.length];
        for (int processo = 0; processo < PROCESSOS.length; processo++) {
            for (int recurso = 0; recurso < RECURSOS.length; recurso++) {
                necessidade[processo][recurso] = MAXIMO[processo][recurso] - alocacao[processo][recurso];
            }
        }
        return necessidade;
    }

    private boolean menorOuIgual(int[] esquerda, int[] direita) {
        for (int i = 0; i < esquerda.length; i++) {
            if (esquerda[i] > direita[i]) return false;
        }
        return true;
    }

    private static String vetor(int[] valores) {
        return Arrays.toString(valores);
    }

    private static int[][] copiar(int[][] origem) {
        int[][] copia = new int[origem.length][];
        for (int i = 0; i < origem.length; i++) copia[i] = origem[i].clone();
        return copia;
    }

    private static final class Verificacao {
        final boolean seguro;
        final List<String> sequencia;
        final List<String> impedidos;

        Verificacao(boolean seguro, List<String> sequencia, List<String> impedidos) {
            this.seguro = seguro;
            this.sequencia = sequencia;
            this.impedidos = impedidos;
        }
    }
}
