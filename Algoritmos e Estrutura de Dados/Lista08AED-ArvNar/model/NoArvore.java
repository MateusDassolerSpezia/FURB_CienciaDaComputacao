/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class NoArvore<T> {
    private T info;
    private NoArvore primeiro;
    private NoArvore proximo;

    public NoArvore(T info) {
        this.info = info;
        primeiro = null;
        proximo = null;
    }
    
    public void inserirFilho(NoArvore sa) {
        sa.setProximo(primeiro);
        primeiro = sa;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoArvore getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoArvore primeiro) {
        this.primeiro = primeiro;
    }

    public NoArvore getProximo() {
        return proximo;
    }

    public void setProximo(NoArvore proximo) {
        this.proximo = proximo;
    }
}
