/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public abstract class ArvoreBinariaAbstract<T> {
    
    private NoArvoreBinaria raiz;

    public ArvoreBinariaAbstract() {
        raiz = null;
    }

    protected void setRaiz(NoArvoreBinaria raiz) {
        this.raiz = raiz;
    }

    public NoArvoreBinaria getRaiz() {
        return raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public boolean pertence(T info) {
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvoreBinaria no, T info) {
        if (no == null) {
            return false;
        } else {
            return (no.getInfo() == info) || pertence(no.getEsquerda(), info) || pertence(no.getDireita(), info);
        }
    }

    public abstract NoArvoreBinaria buscar(T info);
    
    private String arvorePre(NoArvoreBinaria no) { //Prova, fazer a arvorePos e a ordemSistematica
        if (no == null) {
            return "<>";
        } else {           
            String esquerdaStr = arvorePre(no.getEsquerda()); //pega toda a esquerda até chegar no null
            String direitaStr = arvorePre(no.getDireita()); //pega toda a direita até chegar no null
            return "<" + no.getInfo() + esquerdaStr + direitaStr + ">"; //imprimi do ultimo elemento até a raiz
        }
    }

    @Override
    public String toString() {
        return arvorePre(raiz);
    }
    
    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvoreBinaria no) {
        if (no == null) {
            return 0;
        } else {
            return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
        }
    }
}
