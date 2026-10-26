/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class App {

    public App() {
        ArrayList<Pagavel> contas = new ArrayList();
        Pagavel conta1;
        //Pagavel conta2;
        //Pagavel conta3;
        
        
        conta1 = new ContaAgua(10, 5);
        conta1.calcularValorPagar();
        conta1 = new MensalidadeEnsino(8, 5);
        conta1.calcularValorPagar();
        //c.incluirConta();
        /*
        conta2 = new ContaAgua(5, 15);
        conta2 = new MensalidadeEnsino(10, 9);
        conta2.calcularValorPagar();
        c.incluirConta(conta2);
        
        conta3 = new ContaAgua(8, 4);
        conta3 = new MensalidadeEnsino(15, 2);
        conta3.calcularValorPagar();
        c.incluirConta(conta3);
        */        
        
        //ContaAgua conta1 = new ContaAgua(0, 0);
        //MensalidadeEnsino conta1 = new MensalidadeEnsino(0, 0);
        
        ContaAgua conta2 = new ContaAgua(0, 0);
        
        
        ContaAgua conta3 = new ContaAgua(0, 0);
        
        
        JOptionPane.showMessageDialog(null, "Contas: ");
    }

    public static void main(String[] args) {
        new App();
    }
}
