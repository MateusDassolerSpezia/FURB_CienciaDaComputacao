/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author mdspezia
 */
public class App {
    public App() {
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        
        p1.altura = 1.75;
        p1.peso = 78;

        p2.altura = 1.77;
        p2.peso = 60;

        System.out.println("IMC p1 = " + p1.calcularImc());
        System.out.println("IMC p2 = " + p2.calcularImc());
    }
    public static void main(String[] args) {
        new App();
}
}
