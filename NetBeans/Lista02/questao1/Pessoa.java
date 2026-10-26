package questao1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mdspezia
 */
public class Pessoa {
    private String nome;
    private double altura;
    private double peso;
    
    public double calcularImc() {
        return peso / (altura * altura);
    }
    
    public String getNome() {
        return nome;
    }
    public double getAltura() {
        return altura;
    }
    public double getPeso() {
        return peso;
    }
    
    public void setAltura(double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que 0");
        }
        this.altura = altura;
    }
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("peso deve ser maior que 0");
        }
        this.peso = peso;
    }
    public void setNome(String nome) {
        if (nome == null || nome.trim().equals("")) {
            throw new IllegalArgumentException("Digite o nome");
        }
        this.nome = nome;
    }
}
