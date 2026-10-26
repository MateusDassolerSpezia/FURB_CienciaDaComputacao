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
public class ListaEstaticaGenerica<T> {

    private T[] info;
    private int tamanho;

    public ListaEstaticaGenerica() {
        //info = (T[]) new Object[10];
        //tamanho = 0;
        liberar();
    }

    private void redimensionar() {
        T[] novo;
        novo = (T[]) new Object[tamanho + 10];
        for (int i = 0; i <= tamanho - 1; i++) {
            novo[i] = info[i];
        }
        info = novo;
    }

    public void inserir(T valor) {
        if (info.length == tamanho) {
            redimensionar();
        } //else 
        //info[tamanho++] = valor;
        info[tamanho] = valor;
        tamanho++;

    }

    public void exibir() {
        /*String numeros = "";
        for (int i = 0; i < tamanho; i++) {
            numeros += info[i] + " ";
        }*/
        JOptionPane.showMessageDialog(null, toString()); // para vetores pequenos
    }

    public int buscar(T valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(T valor) {
        /*int indiceValor = -1;
        for (int i = 0; i < tamanho; i++) {
            if (valor == info[i]) {
                indiceValor = i;
            }
        }*/
        int indiceValor = buscar(valor);

        if (indiceValor != -1) {
            for (int i = indiceValor; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            tamanho--;
        }
    }

    public void liberar() {
        info = (T[]) new Object[10];
        tamanho = 0;
    }

    @SuppressWarnings("unchecked")
    public T obterElemento(int posicao) throws IndexOutOfBoundsException {
        if (posicao < tamanho) {
            return (T) info[posicao];
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
        String s = "";
        for (int i = 0; i < tamanho; i++) {
            s += info[i];
            if (i < tamanho - 1) {
                s += ", ";
            }
        }
        /*for (int i = 0; i < tamanho; i++) {
            s += info[i].toString() + ", ";
        }*/
        return s;
    }

    public void inverter() {
        for (int i = 0; i < tamanho / 2; i++) {
            T n = (T) info[i];
            info[i] = info[tamanho - 1 - i];
            info[tamanho - 1 - i] = n;
        }

        /*int i = 0;
        int j = tamanho - 1;
        while (i < j) {
            T temp = info[i];  // Guarda o valor temporariamente
            info[i] = info[j]; // Troca os elementos
            info[j] = temp;    // Finaliza a troca
            i++;
            j--;
        }*/
    }

}
