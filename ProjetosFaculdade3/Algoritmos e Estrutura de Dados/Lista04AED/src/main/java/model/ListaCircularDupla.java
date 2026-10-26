/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class ListaCircularDupla<T> {
    private NoListaDupla primeiro;
    private NoListaDupla ultimo;

    public ListaCircularDupla() {
        primeiro = null;
        ultimo = null;
    }

    public NoListaDupla getPrimeiro() {
        return primeiro;
    }

    public NoListaDupla getUltimo() {
        NoListaDupla p = primeiro;
        while (p.getProximo() != ultimo) {
            p = p.getProximo();
        }
        return p;
    }

    public void inserir(T valor) {
        NoListaDupla<T> novo = new NoListaDupla<T>();
        novo.setInfo(valor);
        novo.setProximo(primeiro);
        novo.setAnterior(ultimo);
        if (primeiro != null) {
            primeiro.setAnterior(novo);
        }
        primeiro = novo;
    }

    public NoListaDupla buscar(T valor) {
        NoListaDupla no = primeiro;
        while (no.getProximo() != primeiro) {
            if (no.getInfo() == valor) {
                return no;
            }
            no = no.getProximo();
        }
        return null;
    }

    public void retirar(T valor) {
        NoListaDupla no = buscar(valor);
        if (no != null) {
            if (primeiro == no) {
                primeiro = no.getProximo();
            } else {
                no.getAnterior().setProximo(no.getProximo());
            }
        }
        if (no.getProximo() != ultimo) {
            no.getProximo().setAnterior(no.getAnterior());
        }
    }

    public String exibirOrdemInversa() {
        String S = "";
        NoListaDupla p = getUltimo();
        while (p != ultimo) {
            S += p.getInfo();
            if (p.getAnterior() != ultimo) {
                S += (", ");
            }
            p = p.getAnterior();
        }
        return S;
    }

    public void liberar() {
        NoListaDupla no = primeiro;
        while (no != primeiro) {
            no.setProximo(null);
            no = no.getProximo();
        }
        primeiro = null;
    }

    public String toString() {
        String S = "";
        NoListaDupla p = primeiro;
        while (p != ultimo) {
            S += p.getInfo();
            if (p.getProximo() != ultimo) {
                S += (", ");
            }
            //p.setProximo(p.getProximo());
            p = p.getProximo();
        }
        return S;
    }
}
