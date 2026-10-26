/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author mdspezia
 */
public class Funcionario {

    private String nome;
    private double salario;
    
    /**
     * Cronstrutor da calsse Funcionario
     * @param nome nome do funcionário
     * @param salario salário do funcionario
     * @throws IllegalArgumentException 
     */
    public Funcionario(String nome, double salario) throws IllegalArgumentException {
        setNome(nome);
        setSalario(salario);
    }

    /**
     * Permite alterar o atributo nome
     * @param nome valor atribuído ao atributo nome
     * @throws IllegalArgumentException 
     */
    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.trim().equals("")) {
            throw new IllegalArgumentException("Nome do funcionario não pode ser vazio");
        }
        this.nome = nome;
    }

    /**
     * Permite ler o atributo nome
     * @return valor atribuído ao atributo nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Permite alterar o atributo salario
     * @param salario valor atribuído ao atributo salario
     * @throws IllegalArgumentException 
     */
    public void setSalario(double salario) throws IllegalArgumentException {
        if (salario <= 0) {
            throw new IllegalArgumentException("Salario nao pode ser igual ou menor que 0");
        }
        this.salario = salario;
    }

    /**
     * Permite ler o atributo salario
     * @return valor atribuído ao atributo salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Método que calcula o IRPF
     * @return o valor a ser pago
     */
    public double calcularIrpf() {
        if (salario <= 1903.98) {

            salario -= salario;

        } else if (salario >= 1903.99 && salario <= 2826.65) {

            salario = (salario - 1903.98) * 0.075;

        } else if (salario >= 2826.66 && salario <= 3751.05) {

            salario = (salario - 2826.65) * 0.15 + ((2826.65 - 1903.98) * 0.075);

        } else if (salario >= 3751.06 && salario <= 4664.68) {

            salario = (salario - 3751.05) * 0.225 + ((3751.05 - 2826.65) * 0.15) +((2826.65 - 1903.98) * 0.075);

        } else if (salario >= 4664.69) {

            salario = (salario - 4664.68) * 0.275 + ((4664.68 - 3751.05) * 0.225) + ((3751.05 - 2826.65) * 0.15) + ((2826.65 - 1903.98) * 0.075);

        }
        return salario;
    }

    /**
     * Método que verifica em qual faixa se encontra o IRPF
     * @return a enumeração correspondente a faixa
     */
    public FaixaIrpf identificarFaixaIrpf() {
        if (salario <= 1903.98) {
            return FaixaIrpf.PRIMEIRA;
        }
        if (salario >= 1903.99 && salario <= 2826.65) {
            return FaixaIrpf.SEGUNDA;
        }
        if (salario >= 2826.66 && salario <= 3751.05) {
            return FaixaIrpf.TERCEIRA;
        }
        if (salario >= 3751.06 && salario <= 4664.68) {
            return FaixaIrpf.QUARTA;
        }
        return FaixaIrpf.QUINTA;
    }
}
