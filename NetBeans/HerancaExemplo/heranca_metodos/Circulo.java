/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heranca_metodos;

/**
 *
 * @author mdspezia
 */
public class Circulo extends FormaGeometrica{
    
    /**
     * valor do raio do cirulo
     */
    private float raio;

    
    public Circulo(float raio) {
        setRaio(raio);
    }

    public float getRaio() {
        return raio;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }

    /**
     * Calcula a área do circulo
     * @return valor da área
     */
    @Override
    public float area() {
        return (float) (Math.PI * Math.pow(raio, 2));
    }

    @Override
    public float perimetro() {
        return 2 * (float) (Math.PI * raio);
    }
     
    
    
}
