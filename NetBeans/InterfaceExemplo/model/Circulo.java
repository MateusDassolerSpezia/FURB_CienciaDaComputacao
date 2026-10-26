/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class Circulo implements FormaGeometrica {

    private float raio;

    public Circulo(float raio) throws ValorInvalidoException {
        setRaio(raio);
    }

    public void setRaio(float raio) throws ValorInvalidoException {
        if (raio <= 0) {
            throw new ValorInvalidoException();
        }
        this.raio = raio;
    }

    @Override
    public float perimetro() {
        return (float) (2 * Math.PI * raio);
    }

    @Override
    public float area() {
        return (float) (Math.PI * (raio * raio));
    }

}
