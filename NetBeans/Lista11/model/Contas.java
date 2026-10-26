/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Contas {

    private ArrayList<Pagavel> contas;

    public Contas() {
        contas = new ArrayList();
    }

    public ArrayList<Pagavel> getContas() {
        return contas;
    }

    public void incluirConta(Pagavel conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta deve ser informada");
        }
        contas.add(conta);
    }

    public double calcularTotalContas() {
        double totalContas = 0;
        for (Pagavel c : contas) {
            totalContas = c.calcularValorPagar();
        }
        return totalContas;
    }
}
