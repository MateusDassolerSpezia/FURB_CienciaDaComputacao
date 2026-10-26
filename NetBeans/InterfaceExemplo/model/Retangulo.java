/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class Retangulo implements FormaGeometrica {

    private float lado1;
    private float lado2;

    public Retangulo(float lado1, float lado2) throws ValorInvalidoException {
        setLado1(lado1);
        setLado2(lado2);
    }

    public void setLado1(float lado1) throws ValorInvalidoException {
        if (lado1 <= 0) {
            throw new ValorInvalidoException();
        }
        this.lado1 = lado1;
    }

    public void setLado2(float lado2) throws ValorInvalidoException {
        if (lado2 <= 0) {
            throw new ValorInvalidoException();
        }
        this.lado2 = lado2;
    }

    @Override
    public float perimetro() {
        return 2 * lado1 + 2 * lado2;
    }

    @Override
    public float area() {
        return lado1 * lado2;
    }

}
