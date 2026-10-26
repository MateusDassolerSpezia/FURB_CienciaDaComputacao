/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {

    public App() {
        try {
            ArrayList<FormaGeometrica> formas = new ArrayList();
            formas.add(new Circulo(5));
            formas.add(new Quadrado(2));
            formas.add(new Retangulo(4, 5));

            String dados = "";

            for (FormaGeometrica forma : formas) {
                dados += forma.getClass().getSimpleName() + "\n";
                dados += forma.area() + " - " + forma.perimetro() + "\n";
            }

            JOptionPane.showMessageDialog(null, dados);
            formas.add(new Circulo(0));

        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
