/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class ResultadoAnalise {
    private boolean bemFormatado;
    private ContadorTags contadorTags;
    private String[] erros;
    private int qtdErros;

    /**
     * Construtor que inicializa os atributos da análise.
     */
    public ResultadoAnalise() {
        this.bemFormatado = false;
        this.contadorTags = null;
        this.erros = new String[20];
        this.qtdErros = 0;
    }

    /**
     * Adiciona um erro à lista de erros encontrados.
     * @param erro Mensagem de erro a ser adicionada.
     */
    public void adicionarErro(String erro) {
        if (qtdErros < erros.length) {
            erros[qtdErros++] = erro;
        }
    }

    public String[] getErros() {
        return erros;
    }

    public int getQtdErros() {
        return qtdErros;
    }

    public boolean isBemFormatado() {
        return bemFormatado;
    }

    public void setBemFormatado(boolean bemFormatado) {
        this.bemFormatado = bemFormatado;
    }

    public ContadorTags getContadorTags() {
        return contadorTags;
    }

    public void setContadorTags(ContadorTags contadorTags) {
        this.contadorTags = contadorTags;
    }
}

