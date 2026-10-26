/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelVetor;

/**
 *
 * @author Mateus
 */
public interface Fila<T> {
    
    void inserir(T valor);
    boolean estaVazia();
    T peek();
    T retirar();
    void liberar();
}
