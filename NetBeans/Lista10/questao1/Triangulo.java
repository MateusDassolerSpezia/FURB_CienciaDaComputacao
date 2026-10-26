/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author mdspezia
 */
public class Triangulo extends Figura {
    
    private int lado1;
    private int lado2;
    private int lado3;

    public Triangulo(int lado1, int lado2, int lado3) {
        setLado1(lado1);
        setLado2(lado2);
        setLado3(lado3);
    }

    public int getLado1() {
        return lado1;
    }

    public void setLado1(int lado1) {
        this.lado1 = lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    public int getLado3() {
        return lado3;
    }

    public void setLado3(int lado3) {
        this.lado3 = lado3;
    }
    
    @Override
    public double calcularArea() {
        int p = (lado1 + lado2 + lado3) / 2;
        double a = Math.sqrt(p * (p - lado1) * (p - lado2) * (p - lado3));     
        return a;
    }
}
