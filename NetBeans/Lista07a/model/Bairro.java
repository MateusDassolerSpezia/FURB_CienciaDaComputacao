/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class Bairro {
    
    private String nome;
    private double coeficienteIptu;

    public Bairro(String nome, double coeficienteIptu) throws IllegalArgumentException {
        setNome(nome);
        setCoeficienteIptu(coeficienteIptu);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }            
        this.nome = nome;
    }

    public double getCoeficienteIptu() {
        return coeficienteIptu;
    }

    public void setCoeficienteIptu(double coeficienteIptu) {
        if (coeficienteIptu < 0) {
            throw new IllegalArgumentException("O coeficiente não pode ser menor que 0");
        }
        this.coeficienteIptu = coeficienteIptu;
    }
    
    
}
