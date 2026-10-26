/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class Arvore<T> {

    private NoArvore raiz;

    public Arvore() {
        raiz = null;
    }

    public NoArvore getRaiz() {
        return raiz;
    }

    public void setRaiz(NoArvore raiz) {
        this.raiz = raiz;
    }

    @Override
    public String toString() {
        if(raiz == null) {
            return "";
        }
        return obterRepresentacaoTextual(raiz);
    }

    private String obterRepresentacaoTextual(NoArvore no) {
        String s = "<";
        s = s + no.getInfo();
       
        NoArvore p = no.getPrimeiro();
        while (p != null) {
            s = s + obterRepresentacaoTextual(p);
            p = p.getProximo();
        }
        s = s + ">";
        return s;
    }

    public boolean pertence(T info) {
        if (raiz == null) {
            return false;
        }
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvore no, T info) {
        if (no.getInfo() == info) {
            return true;
        }
        NoArvore p = no.getPrimeiro();
        while (p != null) {
            if (pertence(p, info)) {
                return true;
            }
            p = p.getProximo();
        }
        return false;
    }

    public int contarNos() {
        if (raiz == null) {
            return 0;
        }
        return contarNos(raiz);
    }

    private int contarNos(NoArvore no) {
        int contador = 1;
        NoArvore p = no.getPrimeiro();
        while(p != null) {
            contador += contarNos(p);
            p = p.getProximo();
        }
        return contador;
    }
}
