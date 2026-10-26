/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao2;

import java.util.Scanner;

/**
 *
 * @author mdspezia
 */
public class App {

    public App() {
        Scanner sc = new Scanner(System.in);
        Pessoa p = new Pessoa();

        System.out.println("Digite sua altura:");
        p.altura = sc.nextDouble();
        System.out.println("Digite seu peso:");
        p.peso = sc.nextDouble();

        System.out.println("IMC = " + p.calcularImc());
    }

    public static void main(String[] args) {
        new App();
    }
}
