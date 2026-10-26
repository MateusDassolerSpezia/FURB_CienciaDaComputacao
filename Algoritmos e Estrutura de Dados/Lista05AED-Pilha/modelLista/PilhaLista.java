/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLista;


/**
 *
 * @author Mateus
 */
public class PilhaLista<T> implements Pilha<T> {
    
    private ListaEncadeada<T> lista;

    public PilhaLista() {
        this.lista = new ListaEncadeada();
    }
    
    @Override
    public void push(T info) {
        this.lista.inserir(info);
    }
    
    @Override
    public T pop() {
        T valor = peek();
        this.lista.retirar(valor);
        return valor;
    }
    
   @Override
    public T peek() {
        if(estaVazia()) {
            throw new PilhaVaziaException();
        }
        return (T) this.lista.getPrimeiro().getInfo();
    }
    
    @Override
    public boolean estaVazia() {
        return lista.getPrimeiro() == null;
    }
    
    @Override
    public void liberar()  {
        lista = new ListaEncadeada();
    }
    
    public String toString(){
        return lista.toString();
    }
}
