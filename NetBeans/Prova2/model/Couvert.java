/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public class Couvert extends Item {

    public Couvert(String nome, float valor) {
        super(nome, valor, "Couvert");
    }
    
    @Override
    public String imprimirItem() {
        return ("O valor do couvert de hoje é de R$ " + super.getValor() + " Divirta-se.");
    }
}