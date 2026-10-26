/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class MensalidadeEnsino implements Pagavel {
    
    private int nrCreditos;
    private double precoCredito;

    public MensalidadeEnsino(int nrCreditos, double precoCredito) {
        setNrCreditos(nrCreditos);
        setPrecoCredito(precoCredito);
    }

    public int getNrCreditos() {
        return nrCreditos;
    }

    public void setNrCreditos(int nrCreditos) {
        this.nrCreditos = nrCreditos;
    }

    public double getPrecoCredito() {
        return precoCredito;
    }

    public void setPrecoCredito(double precoCredito) {
        this.precoCredito = precoCredito;
    }
    
    @Override
    public double calcularValorPagar() {
        return nrCreditos * precoCredito;
    }
}
