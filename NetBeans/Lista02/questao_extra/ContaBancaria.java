/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_extra;

/**
 *
 * @author mdspezia
 */
public class ContaBancaria {

    private String numero;
    private String titular;
    private double saldo;
    
    public ContaBancaria(String numero, String titular) throws IllegalArgumentException{
        setNumero(numero);
        setTitular(titular);                      
    }

    /**
     * Permite ler o atributo numero
     *
     * @return valor do numero da conta
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Permite alterar o numero da conta
     *
     * @param numero valor do numero da conta
     */
    public void setNumero(String numero) {
        if (numero == null || numero.trim().equals("")) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio");
        }
        this.numero = numero;
    }

    /**
     * Permite ler o atributo titular
     *
     * @return valor do titular da conta
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Permite alterar o titular da conta
     *
     * @param titular valor do titular da conta
     */
    public void setTitular(String titular) {
        if (titular == null || titular.trim().equals("")) {
            throw new IllegalArgumentException("Titular da conta não pode ser vazio");
        }
        this.titular = titular;
    }

    /**
     * Permirte ler o atributo saldo
     *
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) throws IllegalArgumentException{
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor a ser depositado deve ser maior que 0!");
        }
        this.saldo += valor;
    }

    public void sacar(double valor) throws IllegalArgumentException{
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor a ser depositado deve ser maior que 0!");
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
}
