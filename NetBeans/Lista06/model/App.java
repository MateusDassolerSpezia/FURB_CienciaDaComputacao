/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {
     public App() {
        boolean certo = false;
        do {
            try {
                DecimalFormat df = new DecimalFormat("0.00");

                String nome = JOptionPane.showInputDialog("Nome: ");
                double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário: "));

                Funcionario imposto = new Funcionario(nome, salario);

                JOptionPane.showMessageDialog(null, "O salario esta na " + imposto.identificarFaixaIrpf() + " faixa" + "\nValor de imposto de renda a ser pago: R$ " + df.format(imposto.calcularIrpf()));
                certo = true;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (!certo);
    }

    public static void main(String[] args) {
        new App();
    }
}
