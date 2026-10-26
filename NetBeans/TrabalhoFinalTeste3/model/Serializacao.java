/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.swing.text.StyledDocument;


/**
 *
 * @author Mateus
 */
public class Serializacao implements Serializable{
    private String texto;
    private StyledDocument doc;

    // Construtor
    public Serializacao(String texto, StyledDocument doc) {
        this.texto = texto;
        this.doc = doc;
    }

    // Getters e Setters
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
