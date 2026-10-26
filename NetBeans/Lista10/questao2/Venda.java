/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao2;

/**
 *
 * @author mdspezia
 */
public class Venda {
    
    private double valor;

    public Venda(double valor) throws IllegalArgumentException {
        setValor(valor);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da venda deve ser maior que 0"); 
        }
        this.valor = valor;
    }
    
    
}
