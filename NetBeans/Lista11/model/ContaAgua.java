/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class ContaAgua implements Pagavel {
    
    private int metrosCubicos;
    private double precoMetroCubico;

    public ContaAgua(int metrosCubicos, double precoMetroCubico) {
        setMetrosCubicos(metrosCubicos);
        setPrecoMetroCubico(precoMetroCubico);
    }

    public int getMetrosCubicos() {
        return metrosCubicos;
    }

    public void setMetrosCubicos(int metrosCubicos) {
        this.metrosCubicos = metrosCubicos;
    }

    public double getPrecoMetroCubico() {
        return precoMetroCubico;
    }

    public void setPrecoMetroCubico(double precoMetroCubico) {
        this.precoMetroCubico = precoMetroCubico;
    }
      
    @Override
    public double calcularValorPagar() {
        return metrosCubicos * precoMetroCubico;
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
}
