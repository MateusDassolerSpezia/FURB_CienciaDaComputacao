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
public abstract class ListaAbstract<T> {

    private T[] info;
    private int tamanho;

    public ListaAbstract() {
        //info = (T[]) new Object[10];
        //tamanho = 0;
        liberar();
    }

    public int getTamanho() {
        return tamanho;
    }

    protected void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    protected void redimensionar() {
        T[] novo;
        novo = (T[]) new Comparable[tamanho + 10];
        for (int i = 0; i <= tamanho - 1; i++) {
            novo[i] = info[i];
        }
        info = novo;
    }
    
    protected T[] getInfo() {
        return info;
    }

    public abstract void inserir(T valor); /*{
        if (info.length == tamanho) {
            redimensionar();
        } //else 
        //info[tamanho++] = valor;
        info[tamanho] = valor;
        tamanho++;
    }*/

    public void exibir() {
        /*String numeros = "";
        for (int i = 0; i < tamanho; i++) {
            numeros += info[i] + " ";
        }*/
        JOptionPane.showMessageDialog(null, toString()); // para vetores pequenos
        //System.out.println(toString());
    }

    public abstract int buscar(T dado); /*{
        for (int i = 0; i < tamanho; i++) {
            if (info[i] == dado) {
                return i;
            }
        }
        return -1;
    }*/

    public void retirar(T dado) {
        /*int indiceValor = -1;
        for (int i = 0; i < tamanho; i++) {
            if (valor == info[i]) {
                indiceValor = i;
            }
        }*/
        int indiceValor = buscar(dado);

        if (indiceValor != -1) {
            for (int i = indiceValor; i < tamanho - 1; i++) {
                info[i] = info[i + 1];
            }
            tamanho--;
        }
    }

    public void liberar() {
        info = (T[]) new Comparable[10];
        tamanho = 0;
    }

    @SuppressWarnings("unchecked")
    public T obterElemento(int posicao) throws IndexOutOfBoundsException {
        if (posicao < tamanho) {
            return (T) info[posicao];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
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
}
