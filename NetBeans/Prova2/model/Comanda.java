/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public class Comanda {

    private TipoBuffet tipoBuffet;
    private ArrayList<Item> itens;

    public Comanda(TipoBuffet tipoBuffet) throws IllegalArgumentException {
        setTipoBuffet(tipoBuffet);
        itens = new ArrayList();
    }

    public TipoBuffet getTipoBuffet() {
        return tipoBuffet;
    }

    public void setTipoBuffet(TipoBuffet tipoBuffet) throws IllegalArgumentException {
        if (tipoBuffet == null) {
            throw new IllegalArgumentException("Tipo do buffet não pode ser vazio");
        }
        this.tipoBuffet = tipoBuffet;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public float calcularValorComanda() {
        float valor = 0;
        if (tipoBuffet == TipoBuffet.LIVRE) {
            valor += 30;
            for (Item i : itens) {
                if (i instanceof Bebida || i instanceof Couvert) {
                    valor += i.getValor();
                }
            }
        } else if (tipoBuffet == TipoBuffet.QUILO) {
            for (Item i : itens) {
                valor += i.getValor();
            }
        }
        return valor;
    }

    public void incluirItem(Item item) throws IllegalArgumentException {
        if (itens == null) {
            throw new IllegalArgumentException("Item não pode ser vazio");
        }
        itens.add(item);
    }

    private Item buscar(String nome) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return null;
    }

    public void removerItem(String nome) throws IllegalArgumentException {
        Item itemRemover = buscar(nome);
        if (itemRemover == null) {
            throw new IllegalArgumentException("Item deve ser informado");
        }
        itens.remove(itemRemover);
    }
}
