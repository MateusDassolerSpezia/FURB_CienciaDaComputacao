/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 * 
 */
public class ListaOrdenada<T extends Comparable<T>> extends ListaAbstract<T> {

    @Override
    public void inserir(T dado) {
        if (getTamanho() == this.getInfo().length) {
            redimensionar();
        }

        int pos = 0;
        while(pos < getTamanho() && dado.compareTo((T) this.getInfo()[pos]) > 0) {
            pos++;
        }
        
        for(int i = getTamanho(); i > pos; i--) {
            getInfo()[i] = getInfo()[i - 1];
        }
        
        getInfo()[pos] = dado;
        setTamanho(getTamanho() + 1);
    }

    @Override
    public int buscar(T dado) {
        //int n = getInfo().length;
        int inicio = 0;
        int fim = getTamanho() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (dado.compareTo(getInfo()[meio]) < 0) {
                fim = meio - 1;
            } else if (dado.compareTo(getInfo()[meio]) > 0) {
                inicio = meio + 1;
            } else {
                return meio;
            }
        }
        return -1;
    }
}
