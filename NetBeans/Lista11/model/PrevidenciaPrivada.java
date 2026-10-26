
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class PrevidenciaPrivada extends Investimento {
    
    private double valor;

    public PrevidenciaPrivada(double valor) {
        setValor(valor);
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public double calcularValorPagar() {
        return 0;
    }
    
    public void investir() {
        
    }
}
