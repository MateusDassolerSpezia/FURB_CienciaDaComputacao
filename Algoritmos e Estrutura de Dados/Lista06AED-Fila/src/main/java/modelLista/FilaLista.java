/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLista;

/**
 *
 * @author Mateus
 */
public class FilaLista<T> implements Fila<T> {

    private ListaEncadeada lista;

    public FilaLista() {
        this.lista = new ListaEncadeada();
    }  
    
    @Override
    public void inserir(T valor) {
        lista.inserirNoFinal(valor);
    }

    @Override
    public boolean estaVazia() {
        return lista.getPrimeiro() == null;
    }

    @Override
    public T peek() throws FilaVaziaException {
        if(estaVazia()) {
            throw new FilaVaziaException();
        }
        return (T) this.lista.getPrimeiro().getInfo();
    }

    @Override
    public T retirar() {
        T valor = peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public void liberar() {
        lista = new ListaEncadeada();
    }
    
    @Override
    public String toString() {
        return lista.toString();
    }
}