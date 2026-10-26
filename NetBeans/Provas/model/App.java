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
        
        Periodo periodo = Periodo.MEDIA_TEMPORADA;
        int quantidadeDias = Integer.parseInt(JOptionPane.showInputDialog("Dias: "));

        Estadia e = new Estadia(periodo, quantidadeDias);

        JOptionPane.showMessageDialog(null, e.precoPagar());
    }

    public static void main(String[] args) {
        new App();
    }
}
