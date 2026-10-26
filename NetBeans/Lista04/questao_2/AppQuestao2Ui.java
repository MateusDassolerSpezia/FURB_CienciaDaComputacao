/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_2;

import javax.swing.JOptionPane;
import questao_1.Retangulo;

/**
 *
 * @author mdspezia
 */
public class AppQuestao2Ui {

    public AppQuestao2Ui() {
        try {
            Retangulo ret = new Retangulo();
            int comprimento = Integer.parseInt(JOptionPane.showInputDialog("Comprimento (em cm): "));
            int altura = Integer.parseInt(JOptionPane.showInputDialog("Altura (em cm): "));

            ret.setAltura(altura);
            ret.setComprimento(comprimento);

            JOptionPane.showMessageDialog(null, "Perímetro: " + ret.calcularPerimetro() + " cm" + "\nÁrea: " + ret.calcularArea() + " cm²");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AppQuestao2Ui();
    }
}
