/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLista;


/**
 *
 * @author mdspezia
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
            if (p.getInfo() == info) {
                return p;
            }
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T info) {
        NoLista anterior = null;
        NoLista p = primeiro;

        while (p != null && p.getInfo() != info) {
            anterior = p;
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
            p = p.getProximo();
            comprimento++;
        }
        return comprimento;
    }

    public NoLista obterNo(int idx) {
        NoLista p = primeiro;
        p.setInfo(idx);
        return p;
    }

    public String toString() {
        String S = "";
        NoLista p = primeiro;
        while (p != null) {
            S += p.getInfo();
            if (p.getProximo() != null) {
                S += (", ");
            }
            p = p.getProximo();
        }
        return S;
    }

}
