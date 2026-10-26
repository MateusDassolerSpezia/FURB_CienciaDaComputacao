/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {

    public App() {
        int x = Integer.parseInt(JOptionPane.showInputDialog("X: "));
        int y = Integer.parseInt(JOptionPane.showInputDialog("Y: "));
        Ponto p = new Ponto(x, y);

        JOptionPane.showMessageDialog(null, p.identificarQuadrante() + " quadrante");
        if (p.estaIncidindoSobreX() == true) {
            JOptionPane.showMessageDialog(null, "Incide sobre o eixo X");
        }
        if (p.estaIncidindoSobreY()) {
            JOptionPane.showMessageDialog(null, "Incide sobre o eixo Y");
        }

    }

    public static void main(String[] args) {
        new App();
    }
}
