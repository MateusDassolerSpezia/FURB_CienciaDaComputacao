/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.swing.text.StyledDocument;

/**
 * Classe Serializacao
 * @author Mateus e Vinícius - 2024
 */
public class Serializacao implements Serializable {

    private String texto;
    private StyledDocument doc;

    /**
     * Construtor da classe Serializacao
     *
     * @param texto é a String que armazena o texto
     * @param doc é o parametro que armazena os estilos do texto
     */
    public Serializacao(String texto, StyledDocument doc) {
        setTexto(texto);
        setDoc(doc);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public StyledDocument getDoc() {
        return doc;
    }

    public void setDoc(StyledDocument doc) {
        this.doc = doc;
    }
}
