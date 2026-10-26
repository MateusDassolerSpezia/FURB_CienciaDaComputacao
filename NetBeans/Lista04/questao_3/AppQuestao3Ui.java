/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_3;

import javax.swing.JOptionPane;
import questao_1.Retangulo;

/**
 *
 * @author mdspezia
 */
public class AppQuestao3Ui {

    public AppQuestao3Ui() {
        boolean certo = false;
        do {
            try {
                int comprimento = Integer.parseInt(JOptionPane.showInputDialog("Comprimento (em cm): "));
                int altura = Integer.parseInt(JOptionPane.showInputDialog("Altura (em cm): "));
                Retangulo ret = new Retangulo(comprimento, altura);

                JOptionPane.showMessageDialog(null, "Perímetro: " + ret.calcularPerimetro() + " cm" + "\nÁrea: " + ret.calcularArea() + " cm²");
                certo = true;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (!certo);
    }

    public static void main(String[] args) {
        new AppQuestao3Ui();
    }
}
