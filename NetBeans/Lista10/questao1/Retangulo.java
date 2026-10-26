/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author mdspezia
 */
public class Retangulo extends Figura{
    
    private int largura;
    private int altura;

    public Retangulo(int largura, int altura) {
        setLargura(largura);
        setAltura(altura);
    }

    
    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return largura * altura;
    }
}
