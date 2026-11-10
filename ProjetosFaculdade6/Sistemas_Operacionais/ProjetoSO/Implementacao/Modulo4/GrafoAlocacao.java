package miniso.modulo4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Grafo direcionado de processos e recursos de instância única com detecção de ciclos. */
final class GrafoAlocacao {
    ResultadoDeadlock executar(boolean comCiclo) {
        List<String> processos = Arrays.asList("P1", "P2", "P3");
        List<String> recursos = Arrays.asList("R1", "R2", "R3");
        List<ResultadoDeadlock.Aresta> arestas = new ArrayList<>();
        arestas.add(new ResultadoDeadlock.Aresta("R1", "P1", "Alocação"));
        arestas.add(new ResultadoDeadlock.Aresta("P1", "R2", "Solicitação"));
        arestas.add(new ResultadoDeadlock.Aresta("R2", "P2", "Alocação"));
        if (comCiclo) {
            arestas.add(new ResultadoDeadlock.Aresta("P2", "R1", "Solicitação"));
        } else {
            arestas.add(new ResultadoDeadlock.Aresta("P3", "R1", "Solicitação"));
        }
        arestas.add(new ResultadoDeadlock.Aresta("R3", "P3", "Alocação"));

        List<String> ciclo = detectarCiclo(processos, recursos, arestas);
        boolean deadlock = !ciclo.isEmpty();
        List<String> eventos = new ArrayList<>();
        eventos.add("Grafo criado com recursos de instância única.");
        for (ResultadoDeadlock.Aresta aresta : arestas) {
            eventos.add(aresta.tipo + ": " + aresta.origem + " → " + aresta.destino + ".");
        }
        if (deadlock) {
            eventos.add("Ciclo encontrado: " + String.join(" → ", ciclo) + ".");
            eventos.add("Como cada recurso tem uma única instância, o ciclo confirma um deadlock.");
        } else {
            eventos.add("A busca em profundidade terminou sem encontrar ciclos.");
            eventos.add("Não há dependência circular; nenhum deadlock foi detectado.");
        }

        Map<String, String> resumo = new LinkedHashMap<>();
        resumo.put("Processos", String.valueOf(processos.size()));
        resumo.put("Recursos", recursos.size() + " (instância única)");
        resumo.put("Arestas", String.valueOf(arestas.size()));
        resumo.put("Ciclo", deadlock ? String.join(" → ", ciclo) : "Não encontrado");
        resumo.put("Deadlock", deadlock ? "Detectado" : "Não detectado");

        ResultadoDeadlock.DadosGrafo dados = new ResultadoDeadlock.DadosGrafo(
                processos, recursos, arestas, ciclo, deadlock);
        String titulo = deadlock ? "Deadlock detectado" : "Grafo sem deadlock";
        String descricao = deadlock
                ? "Uma dependência circular impede que os processos avancem com os recursos atuais."
                : "As dependências formam caminhos acíclicos e permitem que os recursos sejam liberados.";
        return new ResultadoDeadlock("grafo", titulo, descricao, eventos, resumo, null, dados);
    }

    private List<String> detectarCiclo(List<String> processos, List<String> recursos,
                                       List<ResultadoDeadlock.Aresta> arestas) {
        Map<String, List<String>> adjacencias = new LinkedHashMap<>();
        for (String processo : processos) adjacencias.put(processo, new ArrayList<>());
        for (String recurso : recursos) adjacencias.put(recurso, new ArrayList<>());
        for (ResultadoDeadlock.Aresta aresta : arestas) {
            adjacencias.get(aresta.origem).add(aresta.destino);
        }

        Map<String, Integer> cor = new HashMap<>();
        List<String> pilha = new ArrayList<>();
        List<String> ciclo = new ArrayList<>();
        for (String no : adjacencias.keySet()) {
            if (cor.getOrDefault(no, 0) == 0 && visitar(no, adjacencias, cor, pilha, ciclo)) break;
        }
        return ciclo;
    }

    private boolean visitar(String no, Map<String, List<String>> adjacencias, Map<String, Integer> cor,
                            List<String> pilha, List<String> ciclo) {
        cor.put(no, 1);
        pilha.add(no);
        for (String vizinho : adjacencias.get(no)) {
            int estado = cor.getOrDefault(vizinho, 0);
            if (estado == 0 && visitar(vizinho, adjacencias, cor, pilha, ciclo)) return true;
            if (estado == 1) {
                int inicio = pilha.indexOf(vizinho);
                ciclo.addAll(pilha.subList(inicio, pilha.size()));
                ciclo.add(vizinho);
                return true;
            }
        }
        pilha.remove(pilha.size() - 1);
        cor.put(no, 2);
        return false;
    }
}
