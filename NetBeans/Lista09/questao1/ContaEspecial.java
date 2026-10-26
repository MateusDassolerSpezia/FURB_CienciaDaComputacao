/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author Mateus
 */
public class ContaEspecial extends ContaBancaria  {
    
    private double limiteCredito;

    public ContaEspecial(double limiteCredito, String numero, Cliente titular, double saldo) {
        super(numero, titular, saldo);
        setLimiteCredito(limiteCredito);
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    
    @Override
    public void sacar(double valor) {
        double limiteTotal = limiteCredito + super.getSaldo();
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor a ser sacado deve ser maior que 0!");
        }
        if (valor > limiteTotal) {
            throw new IllegalArgumentException("Sem limite para esse saque! Saldo: R$ " + limiteTotal);
        }
        
        limiteTotal = limiteTotal - (valor + limiteCredito);
        super.setSaldo(limiteTotal);
    }
}
