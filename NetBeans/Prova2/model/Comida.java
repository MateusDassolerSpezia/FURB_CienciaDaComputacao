/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public class Comida extends Item {

    public Comida(String nome, float valor) {
        super(nome, valor, "Comida");
    }

    @Override
    public String imprimirItem() {
        return (super.getNome() + " - R$ " + super.getValor());
    }

}