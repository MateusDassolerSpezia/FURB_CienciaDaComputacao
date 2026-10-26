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
        float valorQuilo = 0;
        float valorLivre = 30;
        for (Item i : itens) {
            if (tipoBuffet == TipoBuffet.LIVRE && i instanceof Couvert && i instanceof Bebida) {
                valorLivre += i.getValor();
                return valorLivre;
            } else {
                valorQuilo += i.getValor();
                return valorQuilo;
            }
        }
        return 0;
    }

    public void incluirItem(Item item) throws IllegalArgumentException {
        if (itens == null) {
            throw new IllegalArgumentException("Item não pode ser vazio");
        }
        itens.add(item);
    }

    public void removerItem(Item item) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(i.getNome()) && i.getTipoItem().equalsIgnoreCase(i.getTipoItem())) {
                itens.remove(i);
                break;
            }
        }
    }
}
