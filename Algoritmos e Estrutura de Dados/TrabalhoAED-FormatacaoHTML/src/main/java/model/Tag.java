/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class Tag {
    private String nome;

    /**
     * Construtor que define o nome da tag.
     * @param nome Nome da tag.
     */
    public Tag(String nome) {
        this.nome = nome.toLowerCase(); // Case insensitive
    }

    public String getNome() {
        return nome;
    }

    /**
     * Compara se duas tags têm o mesmo nome.
     * @param o Objeto a ser comparado.
     * @return true se os nomes forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) {
            return nome.equals(((Tag) o).nome);
        }
        return false;
    }
}

