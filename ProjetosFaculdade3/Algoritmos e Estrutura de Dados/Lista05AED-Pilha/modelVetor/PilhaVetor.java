/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelVetor;

/**
 *
 * @author Mateus
 */
public class PilhaVetor<T> implements Pilha<T> {

    private T info[];
    private int limite;
    private int tamanho;

    public PilhaVetor(int limite) {
        this.limite = limite;
        this.info = (T[]) new Object[limite];
        this.tamanho = 0;
    }

    @Override
    public void push(T info) throws PilhaCheiaException {
        if (limite == tamanho) {
            throw new PilhaCheiaException();
        }
        this.info[tamanho] = info;
        this.tamanho++;
    }

    @Override
    public T pop() throws PilhaVaziaException {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        T valor = peek();
        this.info[tamanho] = null;
        this.tamanho--;
        return valor;
    }

    @Override
    public T peek() throws PilhaVaziaException {
        if (estaVazia()) {
            throw new PilhaVaziaException();
        }
        return this.info[tamanho - 1];
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public void liberar() {
        tamanho = 0;
        info = (T[]) new Object[limite];
    }

    @Override
    public String toString() {
        String dados = "";
        for (int i = tamanho - 1; i >= 0; i--) {
            dados += info[i];
            if (i != 0) {
                dados += ", ";
            }
        }
        return dados;
    }

    public void concatenar(PilhaVetor<T> p) throws PilhaCheiaException {
        PilhaVetor<T> pAux = new PilhaVetor<>(p.info.length);

        /*while(!p.estaVazia()) {
            pAux.push(p.pop());
        }*/
        
        if (this.tamanho + p.tamanho > limite) {
            throw new PilhaCheiaException();
        }
        
        for (int i = p.tamanho - 1; i >= 0; i--) {
            pAux.push(p.info[i]);
        }

        while (!pAux.estaVazia()) {
            this.push(pAux.pop());
        }
    }
}
