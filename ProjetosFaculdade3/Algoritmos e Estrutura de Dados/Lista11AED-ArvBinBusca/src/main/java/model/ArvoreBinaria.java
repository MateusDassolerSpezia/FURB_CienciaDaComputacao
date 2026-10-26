/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class ArvoreBinaria<T extends Comparable<T>> extends ArvoreBinariaAbstract<T>{

    @Override
    public void setRaiz(NoArvoreBinaria raiz) {
        super.setRaiz(raiz);
    }
    
    @Override
    public NoArvoreBinaria buscar(T info) {
        return buscar(getRaiz(), info);
    }
    
    private NoArvoreBinaria buscar(NoArvoreBinaria<T> no, T info) {
        if (no == null) {
            return null;
        }
        
        if (info.equals(no.getInfo())) {
            return no;
        } else {
            if (info.compareTo(no.getInfo()) < 0) {
                return buscar(no.getEsquerda(), info);
            } else {
                return buscar(no.getDireita(), info);
            }
        }
    }
}
