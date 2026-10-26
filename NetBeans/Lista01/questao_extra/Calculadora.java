/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_extra;

/**
 *
 * @author mdspezia
 */
public class Calculadora {
    private int num1;
    private int num2;
    private int resultado;
    
    public int somar() {
        return num1 + num2;
    }
    public void subtrair() {
        resultado = num1 - num2;
    }
    public void dividir() {
        resultado = num1 / num2;
    }
    public int multiplicar(int n1, int n2) {
        return n1 * n2;
    }
    
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public void setNum2(int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException ("O numero2 não pode ser 0");
        }
        this.num2 = num2;
    }
    public int getNum1() {
        return this.num1;
    }
    public int getNum2() {
        return this.num2;
    }
    public int getResultado() {
        return resultado;
    }
}
