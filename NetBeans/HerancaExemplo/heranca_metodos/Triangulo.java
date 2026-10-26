/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heranca_metodos;

/**
 *
 * @author mdspezia
 */
public class Triangulo extends FormaGeometrica{
    
    private float base;
    private float altura;
    private float lado;
    
    public Triangulo(float base, float altura) {
        setBase(base);
        setAltura(altura);
        setLado(lado);
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLado() {
        return lado;
    }

    public void setLado(float lado) {
        this.lado = lado;
    }
    
    

    @Override
    public float area() {
        return (base * altura) / 2;
    }

    @Override
    public float perimetro() {
        return base + lado + lado;
    }
    
}
