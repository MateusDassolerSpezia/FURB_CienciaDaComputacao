/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

import java.util.Scanner;

/**
 *
 * @author mdspezia
 */
public class App {
    public App() {
        Scanner sc = new Scanner(System.in);
        Pessoa[] pessoas = new Pessoa[3];
        
        
        for (int i = 0; i < 3; i++) {
            Pessoa p = new Pessoa();
            System.out.print("Digite seu nome: ");
            p.setNome(sc.next());
            System.out.print("Digite sua altura: ");
            p.setAltura(sc.nextDouble());
            System.out.print("Digite seu peso: ");
            p.setPeso(sc.nextDouble());
            pessoas[i] = p;
        }
        for (int i = 2; i >= 0; i--) {
            System.out.println();
            System.out.println(pessoas[i].getNome() + " / " + pessoas[i].getPeso() + "Kg / " + pessoas[i].getAltura() + "m");
            System.out.println("IMC = " + pessoas[i].calcularImc());
        }
    }
    public static void main(String[] args) {
        new App();
    }
}
