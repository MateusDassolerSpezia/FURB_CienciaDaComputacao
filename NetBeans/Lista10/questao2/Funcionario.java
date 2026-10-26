/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao2;

/**
 *
 * @author mdspezia
 */
public class Funcionario {
    
    private String nome;
    private double salarioBase;

    public Funcionario(String nome, double salarioBase) throws IllegalArgumentException, NomeVazioException {
        setNome(nome);
        setSalarioBase(salarioBase);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException, NomeVazioException {
        if (nome.isBlank()) {
            throw new NomeVazioException();
        }
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) throws IllegalArgumentException {
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("Salário tem que ser maior que zero");
        } 
        this.salarioBase = salarioBase;
    }
    
    public double calcularSalario() {
        return salarioBase;
    }
    
    //public abstract double calcularSalario();
}
