/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_1;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {
     public App() {
        DecimalFormat df = new DecimalFormat("0.00");
        Funcionario imposto = new Funcionario();
        
        String nome = JOptionPane.showInputDialog("Nome: ");
        double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário: "));
        
        imposto.setNome(nome);
        imposto.setSalario(salario);
       
        
        JOptionPane.showMessageDialog(null, "Valor de imposto de renda a ser pago: R$ " + df.format(imposto.calcularIrpf()));
    }
    public static void main(String[] args) {
        new App();
    }
}
