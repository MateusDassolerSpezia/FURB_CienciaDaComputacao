/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
// Mateus Dassoler Spezia
public class Estadia {

    private Periodo periodo;
    private int quantidadeDias;

    /**
     * Construtor da classe Estadia
     *
     * @param periodo período do ano o qual o hóspede se hospedará
     * @param quantidadeDias quantidade de diárias que o hospede se hospedará
     * @throws IllegalArgumentException
     */
    public Estadia(Periodo periodo, int quantidadeDias) throws IllegalArgumentException {
        setPeriodo(periodo);
        setQuantidadeDias(quantidadeDias);
    }

    /**
     * Permite ler o atributo periodo
     *
     * @return valor atribuído ao atributo periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Permite alterar o atributo periodo
     *
     * @param periodo valor atribuído ao atributo periodo
     * @throws IllegalArgumentException o periodo não pode ser vazio
     */
    public void setPeriodo(Periodo periodo) throws IllegalArgumentException {
        if (periodo == null) {                                                    //voltar aqui
            throw new IllegalArgumentException("Período não pode ser vazio");
        }
        this.periodo = periodo;
    }

    /**
     * Permite ler o atributo quantidadeDias
     *
     * @return valor atribuído ao atributo quantidadeDias
     */
    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    /**
     * Permite alterar o atributo quantidadeDias
     *
     * @param quantidadeDias Valor atribuído ao atributo quantidadeDias
     * @throws IllegalArgumentException a quantidade de dias deve ser sempre
     * maior que 0 e em alta temporada maior que 5
     */
    public void setQuantidadeDias(int quantidadeDias) throws IllegalArgumentException {
        if (quantidadeDias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias deve ser maior que 0");
        } else if (periodo == Periodo.ALTA_TEMPORADA && quantidadeDias < 5 && quantidadeDias > 0) {
            throw new IllegalArgumentException("A quantidade mínima de dias em alta temporada é 5");
        }
        this.quantidadeDias = quantidadeDias;
    }

    /**
     * Método que calcula o preço a pagar pela estadia no hotel
     *
     * @return o valor a ser pago
     */
    public float precoPagar() {
        float diaria = 150;
        if (periodo == Periodo.BAIXA_TEMPORADA && quantidadeDias > 1) {
            diaria = (diaria - diaria * 0.3f) * quantidadeDias;
        } else if (periodo == Periodo.BAIXA_TEMPORADA && quantidadeDias == 1) {
            diaria = (diaria - diaria * 0.3f) * quantidadeDias;
            diaria += diaria * 0.05;
        } else if (periodo == Periodo.ALTA_TEMPORADA && quantidadeDias < 7) {
            diaria = (diaria + diaria * 0.2f) * quantidadeDias;
        } else if (periodo == Periodo.ALTA_TEMPORADA && quantidadeDias >= 7) {
            diaria = (diaria + diaria * 0.2f) * quantidadeDias;
            diaria -= diaria * 0.02;
        } else if (periodo == Periodo.MEDIA_TEMPORADA && quantidadeDias > 1) {
            diaria = diaria * quantidadeDias;
        } else if (periodo == Periodo.MEDIA_TEMPORADA && quantidadeDias == 1) {
            diaria = diaria * quantidadeDias;
            diaria += diaria * 0.05;
        }
        return diaria;
    }

}
