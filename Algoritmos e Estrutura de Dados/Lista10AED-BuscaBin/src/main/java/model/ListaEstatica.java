/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class ListaEstatica<T> extends ListaAbstract<T> {

    @Override
    public void inserir(T dado) {
        if (getInfo().length == getTamanho()) {
            redimensionar();
        } //else 
        //info[tamanho++] = valor;
        getInfo()[getTamanho()] = dado;
        setTamanho(getTamanho() + 1);
    }

    @Override
    public int buscar(T dado) {
        for (int i = 0; i < getTamanho(); i++) {
            if (getInfo()[i] == dado) {
                return i;
            }
        }
        return -1;
    }

}
