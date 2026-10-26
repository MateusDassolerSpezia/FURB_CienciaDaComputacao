/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class ListaEstatica {

    private int[] info;
    private int tamanho;

    public ListaEstatica() {
        info = new int[10];
        tamanho = 0;
        //liberar();
    }

    private void redimensionar() {
        int[] novo;
        int novoTamanho = info.length + 10;
        novo = new int[novoTamanho];
        for (int i = 0; i <= tamanho - 1; i++) {
            novo[i] = info[i];
        }
        info = novo;
    }

    public void inserir(int valor) {
        if (tamanho == info.length) {
            redimensionar();
        } //else 
        info[tamanho] = valor;
        tamanho++;

    }

    public void exibir() {
        String numeros = "";
        for (int i = 0; i < tamanho; i++) {
            numeros += info[i] + " ";
        }
        JOptionPane.showMessageDialog(null, numeros); // para vetores pequenos
    }

    public int buscar(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(int valor) {
        int indiceValor = buscar(valor);

        if (indiceValor != -1) {

            for (int i = indiceValor; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            tamanho--;
        }
    }

    public void liberar() {
        tamanho = 0;
        info = new int[10];
    }

    public int obterElemento(int posicao) throws IndexOutOfBoundsException{
        if (posicao < tamanho) {
            return info[posicao];
        }

        throw new IndexOutOfBoundsException();
    }

    public boolean estaVazia() {
        /*if (tamanho == 0) {
            return true;
        }
        return false;*/

        return (tamanho == 0);
    }

    public int getTamanho() {
        return tamanho;
    }

    public String toString() {
        String numeros = "";
        for (int i = 0; i < tamanho; i++) {
            numeros += info[i];
            if (i < tamanho - 1) {
                numeros += ", ";
            }
        }
        return numeros;
    }
}
