/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Mateus
 */
public class PilhaLista<T> implements Pilha<T> {
    
    private ListaEncadeada<T> lista;

    /**
     * Construtor que inicializa a lista interna como vazia.
     */
    public PilhaLista() {
        this.lista = new ListaEncadeada();
    }
    
    /**
     * Insere um elemento no topo da pilha.
     * @param info Elemento a ser adicionado.
     */
    @Override
    public void push(T info) {
        this.lista.inserir(info);
    }
    
    /**
     * Remove e retorna o elemento no topo da pilha.
     * @return Elemento removido.
     */
    @Override
    public T pop() {
        T valor = peek();
        this.lista.retirar(valor);
        return valor;
    }
    
    /**
     * Retorna o elemento no topo da pilha sem removê-lo.
     * @return Elemento do topo.
     * @throws RuntimeException se a pilha estiver vazia.
     */
   @Override
    public T peek() throws RuntimeException{
        if(estaVazia()) {
            //throw new PilhaVaziaException();
            throw new RuntimeException("Pilha está vazia");
        }
        return (T) this.lista.getPrimeiro().getInfo();
    }
    
    /**
     * Verifica se a pilha está vazia.
     * @return true se estiver vazia, false caso contrário.
     */
    @Override
    public boolean estaVazia() {
        return lista.getPrimeiro() == null;
    }
    
    /**
     * Remove todos os elementos da pilha.
     */
    @Override
    public void liberar()  {
        lista = new ListaEncadeada();
    }
    
    /**
     * Retorna uma representação textual da pilha.
     * @return String com os elementos da pilha.
     */
    @Override
    public String toString(){
        return lista.toString();
    }
}
