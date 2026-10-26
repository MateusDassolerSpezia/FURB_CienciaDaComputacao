/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;


import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class ContaBancaria {
    
    private String numero;
    private Cliente titular;
    private double saldo;
    private ArrayList<Movimento> movimentos;

    public ContaBancaria(String numero, Cliente titular, double saldo) {
        setNumero(numero);
        setTitular(titular);
        setSaldo(saldo);
        movimentos = new ArrayList();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor a ser depositado deve ser maior que 0!");
        }
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor a ser sacado deve ser maior que 0!");
        }
        if (valor > this.saldo) {
            throw new IllegalArgumentException("Sem limite para esse saque! Saldo: R$ " + getSaldo());
        } 
        this.saldo -= valor;
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (contaDestino == null) {
            throw new IllegalArgumentException("Conta de destino invalida");
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    public ArrayList<Movimento> getMovimentos() {
        return movimentos;
    }
    
    protected void incluirMovimento(Movimento m) {
        movimentos.add(m);
    }
    
    
}
