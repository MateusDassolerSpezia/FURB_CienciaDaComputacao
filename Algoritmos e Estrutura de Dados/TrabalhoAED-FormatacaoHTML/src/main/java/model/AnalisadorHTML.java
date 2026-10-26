/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Mateus
 */
public class AnalisadorHTML {
    private static final String[] SINGLETON_TAGS = {
        "meta", "base", "br", "col", "command", "embed", "hr",
        "img", "input", "link", "param", "source", "!doctype"
    };

    private ContadorTags contadorTags;
    private Pilha<String> pilha;

    /**
     * Construtor padrão que inicializa o contador de tags e a pilha.
     */
    public AnalisadorHTML() {
        contadorTags = new ContadorTags();
        pilha = new PilhaLista<>();
    }

    /**
     * Analisa o conteúdo de um arquivo HTML e retorna o resultado da análise.
     * @param caminhoArquivo Caminho para o arquivo HTML.
     * @return Um objeto {@link ResultadoAnalise} com informações sobre formatação e erros.
     */
    public ResultadoAnalise analisarArquivo(String caminhoArquivo) {
        ResultadoAnalise resultado = new ResultadoAnalise();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                while (linha.contains("<")) {
                    int inicio = linha.indexOf("<");
                    int fim = linha.indexOf(">", inicio);
                    if (fim == -1) break;

                    String tagCompleta = linha.substring(inicio + 1, fim).trim();
                    String tagNome = tagCompleta.split("\\s+")[0].toLowerCase();

                    boolean ehFechamento = tagNome.startsWith("/");
                    if (ehFechamento) {
                        tagNome = tagNome.substring(1);
                        if (ehSingleton(tagNome)) {
                            resultado.adicionarErro("Tag final inesperada para singleton tag: </" + tagNome + ">");
                            return resultado;
                        }
                        if (pilha.estaVazia()) {
                            resultado.adicionarErro("Tag final inesperada: </" + tagNome + ">");
                            return resultado;
                        }
                        String topo = pilha.pop();
                        if (!topo.equals(tagNome)) {
                            resultado.adicionarErro("Esperava </" + topo + ">, mas encontrou </" + tagNome + ">");
                            return resultado;
                        }
                    } else {
                        if (!ehSingleton(tagNome)) {
                            pilha.push(tagNome);
                        }
                        contadorTags.adicionar(tagNome);
                    }

                    linha = linha.substring(fim + 1);
                }
            }

            if (!pilha.estaVazia()) {
                while (!pilha.estaVazia()) {
                    String faltante = pilha.pop();
                    resultado.adicionarErro("Tag de abertura <" + faltante + "> sem fechamento correspondente.");
                }
                return resultado;
            }

            resultado.setBemFormatado(true);
            resultado.setContadorTags(contadorTags);
            return resultado;

        } catch (IOException e) {
            resultado.adicionarErro("Erro ao ler arquivo: " + e.getMessage());
            return resultado;
        }
    }

    /**
     * Verifica se uma tag é do tipo singleton (não requer fechamento).
     * @param tag Nome da tag.
     * @return true se for singleton, false caso contrário.
     */
    private boolean ehSingleton(String tag) {
        for (String singleton : SINGLETON_TAGS) {
            if (singleton.equalsIgnoreCase(tag)) return true;
        }
        return false;
    }
}
