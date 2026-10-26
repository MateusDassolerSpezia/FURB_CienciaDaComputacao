/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus Dassoler Spezia
 */
public class Pagamento {

    private Comanda comanda;

    public Pagamento(Comanda comanda) throws IllegalArgumentException {
        setComanda(comanda);
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) throws IllegalArgumentException {
        if (comanda == null) {
            throw new IllegalArgumentException("Comanda não pode ser vazia");
        }
        this.comanda = comanda;
    }

    public float calcularTotalComanda() {
        return calcularDesconto();
    }

    public void pagar(float valor) throws IllegalArgumentException {
        if(calcularTotalComanda() != valor) {
            throw new IllegalArgumentException("Valor pago está incorreto");
        }
    }

    public float calcularDesconto() {
        if (comanda.calcularValorComanda() > 50) {
            return comanda.calcularValorComanda() - (comanda.calcularValorComanda() * 0.05f);
        }
        return comanda.calcularValorComanda();
    }
}
