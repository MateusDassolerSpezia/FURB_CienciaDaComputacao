/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public abstract class Item {
    
    private String nome;
    private float valor;
    private String tipoItem;

    public Item(String nome, float valor, String tipoItem) throws IllegalArgumentException {
        setNome(nome);
        setValor(valor);
        setTipoItem(tipoItem);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
        this.valor = valor;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }
    
    public abstract String imprimirItem();
}
