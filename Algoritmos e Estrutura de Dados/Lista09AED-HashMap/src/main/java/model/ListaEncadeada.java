/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 *
 */
public class ListaEncadeada<T> {

    private NoLista primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    public NoLista getPrimeiro() {
        return primeiro;
    }

    public void inserir(T info) {
        NoLista novo = new NoLista();
        novo.setInfo(info);
        novo.setProximo(primeiro);
        primeiro = novo;
    }

    public Boolean estaVazia() {
        return (primeiro == null);
    }

    public NoLista buscar(T info) {
        NoLista p = primeiro;
        while (p != null) {
            if (p.getInfo().equals(info)) {
                return p;
            }
            //p.setProximo(p.getProximo());
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T info) {
        NoLista anterior = null;
        NoLista p = primeiro;

        while (p != null && !p.getInfo().equals(info)) {
            anterior = p;
            //p.setProximo(p.getProximo());
            p = p.getProximo();
        }
        if (p != null) {
            if (p == primeiro) {
                primeiro = p.getProximo();
            } else {
                anterior.setProximo(p.getProximo());
            }
        }
    }

    public int obterComprimento() {
        int comprimento = 0;
        NoLista p = primeiro;
        while (p != null) {
            //p.setProximo(p.getProximo());
            p = p.getProximo();
            comprimento++;
        }
        return comprimento;
    }

    public NoLista obterNo(int idx) throws IndexOutOfBoundsException {
        int indice = 0;
        NoLista no = primeiro;
        while (no != null) {
            if (indice == idx) {
                return no;
            }
            indice++;
            //p.setProximo(p.getProximo());
            no = no.getProximo();
        }
        throw new IndexOutOfBoundsException();
    }

    public String toString() {
        String S = "";
        NoLista p = primeiro;
        while (p != null) {
            S += p.getInfo();
            if (p.getProximo() != null) {
                S += (", ");
            }
            //p.setProximo(p.getProximo());
            p = p.getProximo();
        }
        return S;
    }

    public ListaEncadeada<T> criarSublista(int inicio, int fim) throws IndexOutOfBoundsException {
        NoLista no = primeiro;
        ListaEncadeada sublista = new ListaEncadeada();
        int i = 0;
        if (inicio > 0 || inicio < obterComprimento() || fim > 0 || fim < obterComprimento()) { // inverti as comprarações de maior e menor pois do jeito que estava elas levavam direto para a exceção no caso de valores corretos 
            while (fim != i - 1) { // tive q colocar o -1 pois o While terminava em um nó antes do necessário
                while (inicio > i) {
                    no = no.getProximo();
                    i++;
                }
                sublista.inserir(no.getInfo());
                no = no.getProximo();
                i++;
            }
            return sublista;
        }
        throw new IndexOutOfBoundsException();
    }
}
