/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao2;

import java.util.ArrayList;

/**
 *
 * @author mdspezia
 */
public class Vendedor extends Funcionario {

    private double percentualComissao;
    private ArrayList<Venda> vendas;

    public Vendedor(double percentualComissao, String nome, double salarioBase) throws IllegalArgumentException, NomeVazioException {
        super(nome, salarioBase); //super é sempre a primeira linha do construtor
        setPercentualComissao(percentualComissao);
        vendas = new ArrayList();
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) throws IllegalArgumentException {
        if (percentualComissao <= 0) {
            throw new IllegalArgumentException("Percentual de venda deve ser maior que zero"); 
        }
        this.percentualComissao = percentualComissao;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void incluirVenda(Venda v) {
        if (v == null) {
            throw new IllegalArgumentException("Venda inválida");
        }
        vendas.add(v);
    }

    @Override
    public double calcularSalario() {
        double totalVendas = 0;
        for (Venda v : vendas) {
            totalVendas += v.getValor();
        }
        return super.getSalarioBase() + (totalVendas * (percentualComissao / 100));
    }
}
