/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author mdspezia
 */
public class ListaEncadeada<T> {

    private NoLista primeiro;

    /**
     * Construtor que inicializa a lista como vazia.
     */
    public ListaEncadeada() {
        primeiro = null;
    }

    public NoLista getPrimeiro() {
        return primeiro;
    }

    /**
     * Insere um novo elemento no início da lista.
     * @param info Informação a ser inserida.
     */
    public void inserir(T info) {
        NoLista novo = new NoLista();
        novo.setInfo(info);
        novo.setProximo(primeiro);
        primeiro = novo;
    }

    /**
     * Verifica se a lista está vazia.
     * @return true se estiver vazia, false caso contrário.
     */
    public Boolean estaVazia() {
        return (primeiro == null);
    }

    /**
     * Busca um nó com a informação fornecida.
     * @param info Valor a ser buscado.
     * @return Nó correspondente, ou null se não encontrado.
     */
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

    /**
     * Retira o nó que contém a informação fornecida.
     * @param info Informação a ser removida.
     */
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

    /**
     * Retorna a quantidade de elementos da lista.
     * @return Comprimento da lista.
     */
    public int obterComprimento() {
        int comprimento = 0;
        NoLista p = primeiro;
        while (p != null) {
            p = p.getProximo();
            comprimento++;
        }
        return comprimento;
    }

    /**
     * Retorna o nó correspondente ao índice fornecido (0-based).
     * @param idx Índice desejado.
     * @return Nó na posição indicada.
     */
    public NoLista obterNo(int idx) {
        NoLista p = primeiro;
        p.setInfo(idx);
        return p;
    }

    /**
     * Retorna uma representação textual da lista.
     * @return String com os elementos separados por vírgula.
     */
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
