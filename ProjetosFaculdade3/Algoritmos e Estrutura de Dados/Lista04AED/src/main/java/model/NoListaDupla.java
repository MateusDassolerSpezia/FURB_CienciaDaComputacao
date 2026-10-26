/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class NoListaDupla<T> {
    
    private T info;
    private NoListaDupla proximo;
    private NoListaDupla anterior;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoListaDupla getProximo() {
        return proximo;
    }

    public void setProximo(NoListaDupla proximo) {
        this.proximo = proximo;
    }

    public NoListaDupla getAnterior() {
        return anterior;
    }

    public void setAnterior(NoListaDupla anterior) {
        this.anterior = anterior;
    }
}
