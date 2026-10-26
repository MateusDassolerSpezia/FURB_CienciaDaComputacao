/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Serializacao implements Serializable{
    private static final long serialVersionUID = 1L;  // ID de versão para garantir compatibilidade entre versões de serialização
    private String texto;
    private List<String> formatacoes;

    // Construtor
    public Serializacao(String texto, List<String> formatacoes) {
        this.texto = texto;
        this.formatacoes = formatacoes;
    }

    // Getters e Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getFormatações() {
        return formatacoes;
    }

    public void setFormatações(List<String> formatações) {
        this.formatacoes = formatações;
    }

    @Override
    public String toString() {
        return "Texto: " + texto + "\nFormatações: " + formatacoes.toString();
    }
}
