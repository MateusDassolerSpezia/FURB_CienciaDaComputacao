package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mateus
 */
import java.io.Serializable;
import java.util.List;

public class TextoFormatado implements Serializable {
    private static final long serialVersionUID = 1L;  // ID de versão para garantir compatibilidade entre versões de serialização
    private String texto;
    private List<String> formatações;

    // Construtor
    public TextoFormatado(String texto, List<String> formatações) {
        this.texto = texto;
        this.formatações = formatações;
    }

    // Getters e Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<String> getFormatações() {
        return formatações;
    }

    public void setFormatações(List<String> formatações) {
        this.formatações = formatações;
    }

    @Override
    public String toString() {
        return "Texto: " + texto + "\nFormatações: " + formatações.toString();
    }
}
