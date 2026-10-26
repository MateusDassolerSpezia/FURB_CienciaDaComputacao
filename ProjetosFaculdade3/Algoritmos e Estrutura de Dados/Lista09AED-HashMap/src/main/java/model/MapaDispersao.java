/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class MapaDispersao<T> {

    private ListaEncadeada<NoMapa<T>>[] info;

    public MapaDispersao(int tamanho) {
        this.info = new ListaEncadeada[tamanho];
    }

    private int calcularHash(int chave) {
        int tamanho = info.length;
        return chave % tamanho;
    }

    public void inserir(int chave, T dado) {
        int indice = calcularHash(chave);

        if (info[indice] == null) {
            info[indice] = new ListaEncadeada<>();
        }

        NoMapa noMapa = new NoMapa();
        noMapa.setChave(chave);
        noMapa.setInfo(dado);

        info[indice].inserir(noMapa);
    }

    public void remover(int chave) {
        int indice = calcularHash(chave);

        if (info[indice] == null) {
            throw new RuntimeException("Essa chave não existe");
        }

        NoMapa noMapa = new NoMapa();
        noMapa.setChave(chave);

        info[indice].retirar(noMapa);
    }

    public T buscar(int chave) {
        int indice = calcularHash(chave);

        if (info[indice] != null) {
            NoMapa<T> noMapa = new NoMapa<>();
            noMapa.setChave(chave);

            NoLista<NoMapa<T>> no = info[indice].buscar(noMapa);
            if (no != null) {
                return no.getInfo().getInfo();
            }
        }
        return null;
    }

    public void realocarMapa(int novoTamanho) {
        ListaEncadeada<NoMapa<T>>[] aux;
        aux = new ListaEncadeada[novoTamanho]; // Criei o vetor auxiliar com o tamanho especificado

        NoMapa noMapa = new NoMapa(); // Criei um NoMapa
        
        NoLista<NoMapa<T>> no; // Criei um NoLista<NoMapa<T>>

        for (int i = 0; i < info.length; i++) { // For para percorrer o vetor atual
            if (info[i] != null) { // Se na posição não tiver nenhum dado, não faz a transferência
                no = info[i].getPrimeiro(); // Pega toda a informação que está na lista do vetor atual
                noMapa.setChave(no.getInfo().getChave()); // Pega a chave do dado que está na lista do vetor atual
                int indice = noMapa.getChave() % novoTamanho; // Calculo do índice com a chave encontrada o novo tamanho 
                aux[indice] = this.info[i]; // Transfere os dados do vetor atual para o vetor novo, utilizando o indice desse método para alocar os dados no local correto no novo vetor
            }
        }
        info = aux; // Info referenciando o novo vetor
    }
}
