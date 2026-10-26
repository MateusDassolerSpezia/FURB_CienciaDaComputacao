/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public class Dinheiro extends Pagamento {
    
    private float valorPago;

    public Dinheiro(float valorPago, Comanda comanda) throws IllegalArgumentException {
        super(comanda);
        setValorPago(valorPago);
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) throws IllegalArgumentException {
        if (valorPago <= 0) {
            throw new IllegalArgumentException("Valor pago deve ser maior que zero");
        }
        this.valorPago = valorPago;
    }
    
    @Override
    public float calcularTotalComanda() {
        return calcularDesconto();
    }
    
    @Override
    public void pagar(float valor) throws IllegalArgumentException {
        if (calcularDesconto() > valor) {
            throw new IllegalArgumentException("Dinheiro insuficiente");
        }
    }
    
    @Override
    public float calcularDesconto() {
        float descontoDinheiro = comanda.calcularValorComanda() - (comanda.calcularValorComanda() * 0.10f);
        
        return descontoDinheiro;
    }
    
    public float calcularTroco() {
        return calcularDesconto() - valorPago;
    }
}
