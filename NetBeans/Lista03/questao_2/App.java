/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_2;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {

    private Funcionario[] funcionarios;

    public App() {
        DecimalFormat df = new DecimalFormat("0.00");
        funcionarios = new Funcionario[5];
        //Funcionario imposto = new Funcionario();

        for (int i = 0; i < 5; i++) {
            Funcionario imposto = new Funcionario();
            String nome = JOptionPane.showInputDialog("Nome: ");
            double salario = Double.parseDouble(JOptionPane.showInputDialog("Salário: "));

            imposto.setNome(nome);
            imposto.setSalario(salario);

            funcionarios[i] = imposto;
        }

        String mensagem = "";
        for (int i = 0; i < 5; i++) {
            mensagem += funcionarios[i].getNome() + ": Salário = R$ " + df.format(funcionarios[i].getSalario()) + "\nValor de imposto de renda a ser pago: R$ " + df.format(funcionarios[i].calcularIrpf()) + "\n";

        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public static void main(String[] args) {
        new App();
    }
}
