/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelVetor;

/**
 *
 * @author Mateus
 */
public class FilaVetor<T> implements Fila<T> {

    private T[] info;
    private int limite;
    private int tamanho;
    private int inicio;

    public FilaVetor(int limite) {
        this.limite = limite;
        info = (T[]) new Object[limite];
        tamanho = 0;
        inicio = 0;
    }

    @Override
    public void inserir(T valor) throws FilaCheiaException {
        if (limite == tamanho) {
            throw new FilaCheiaException();
        }
        int posicaoInserir = (inicio + tamanho) % limite;
        info[posicaoInserir] = valor;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    @Override
    public T peek() throws FilaVaziaException {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return info[inicio];
    }

    @Override
    public T retirar() {
        T valor = peek();
        inicio = (inicio + 1) % limite;
        tamanho--;
        return valor;
    }

    @Override
    public void liberar() {
        info = (T[]) new Object[limite];
        tamanho = 0;
    }

    public FilaVetor criarFilaConcatenada(FilaVetor f2) {
        FilaVetor f3 = new FilaVetor(this.limite + f2.limite);
        /*for (int i = 0; i <= this.tamanho - 1; i++) {
            f3.inserir(this.info[i]);
        }
        for (int i = 0; i <= f2.tamanho - 1; i++) {
            f3.inserir(f2.info[i]);
        }*/
        for (int i = 0; i < this.tamanho; i++) {
            int pos = (this.inicio + i) % this.limite;
            f3.inserir(this.info[pos]);
        }
        for (int i = 0; i < f2.tamanho; i++) {
            int pos = (f2.inicio + i) % f2.limite;
            f3.inserir(f2.info[pos]);
        }
        return f3;
    }

    @Override
    public String toString() {
        /*String dados = "";
        for (int i = inicio; i < tamanho; i++) {
            dados += info[i];
            if (i < tamanho - 1) {
                dados += ", ";
            }
        }
        return dados;*/
        String dados = "";
        for (int i = 0; i < tamanho; i++) {
            int pos = (inicio + i) % limite;
            dados += info[pos];
            if (i < tamanho - 1) {
                dados += ", ";
            }
        }
        return dados;
    }

    public int getLimite() {
        return limite;
    }

    public void encolher() {
        T[] aux = (T[]) new Object[this.tamanho]; // Criei um vetor auxiliar com tamanho igual a quantidade de informações do vetor atual.
        for (int i = 0; i < this.tamanho; i++) { // Fiz um for que utiliza a variavel this.tamanho como referência, para percorrer apenas de acordo com a quantidade de informações do vetor atual.
            int pos = (this.inicio + i) % this.limite; // Utilizei essa conta pois, pela fila poder utilizar o vetor de forma circular, a primeira informação da fila não estará necessariamente no início do vetor.
            aux[i] = this.info[pos]; // Armazenei as informações do vetor atual no vetor auxiliar, para poder fazer mudanças no vetor atual sem perder suas informações.
        }
        this.limite = this.tamanho; // Ajustei o limite do vetor atual para ser igual a quantidade de informações da fila.
        this.info = (T[]) new Object[this.limite]; // Recriei o vetor da lista atual, utilizando o novo limite para o tamanho do vetor.
        inicio = 0; // Zerei o início, pois quando a fila encolhe, a primeira informação vai para o início do vetor.
        for (int i = 0; i < this.tamanho; i++) { // Fiz um for que utiliza a variavel this.tamanho como referência, para percorrer apenas de acordo com a quantidade de informações do vetor atual.
            info[i] = aux[i]; // Passei as informações do vetor auxiliar para o vetor atual (recuperando seus elementos).
        }
    }

}
