/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class TestePrincipal {
    public static void main(String[] args) {
        ListaGenerica lista = new ListaGenerica<String>();
        lista.setInfo("Oi");
        
        System.out.println(lista.getInfo());
        
        lista = new ListaGenerica<Integer>();
        lista.setInfo(2);
        
        System.out.println(lista.getInfo());
    }
}
