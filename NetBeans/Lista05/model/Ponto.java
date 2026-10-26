/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class Ponto {

    private int x;
    private int y;
    /**
     * cria uma instância de um ponto vazio
     */
    public Ponto() {
        
    }
    /**
     * Cria uma instância de ponto com valores definidos
     * @param x cordenada x do ponto
     * @param y cordenada y do ponto
     */
    public Ponto(int x, int y) throws IllegalArgumentException {
        setX(x);
        setY(y);
    }
    /**
     * Permite ler o atributo x
     * @return valor de x do ponto
     */
    public int getX() {
        return x;
    }
    /**
     * Permite alterar o x do ponto
     * @param x valor de x do ponto
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Permite ler o atributo y
     * @return valor de y do ponto
     */
    public int getY() {
        return y;
    }
    /**
     * alterar o y do ponto
     * @param y valor de y do ponto
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Identifica o quadrante em que o ponto se localiza
     * @return identificar quadrante
     */
    public Quadrante identificarQuadrante() {
        if (x == 0 || y == 0) {
            return Quadrante.NENHUM;
        } else if (x > 0 && y > 0) {
            return Quadrante.PRIMEIRO;
        } else if (x < 0 && y > 0) {
            return Quadrante.SEGUNDO;
        } else if (x < 0 && y < 0) {
            return Quadrante.TERCEIRO;
        } else if (x > 0 && y < 0) {
            return Quadrante.QUARTO;
        }
        return null;
    }
    /**
     * Identifica se o ponto está incidindo sobre x
     * @return ponto incidindo sobre x
     */
    public boolean estaIncidindoSobreX() {
        if (y == 0 && x != 0) {
            return true;
        }
        return false;
    }
    /**
     * Identifica se o ponto está incidindo sobre y
     * @return ponto incidindo sobre y
     */
    public boolean estaIncidindoSobreY() {
        if (x == 0 && y != 0) {
            return true;
        }
        return false;
    }
    /**
     * Calcula a distância de 2 pontos
     * @param outroPonto posição de outro ponto
     * @return distância entre 2 pontos
     */
    public double calcularDistancia(Ponto outroPonto) {
        return Math.sqrt((Math.pow(outroPonto.x, 2) - Math.pow(this.x, 2)) + (Math.pow(outroPonto.y, 2) - Math.pow(this.y, 2)));
    }
    /**
     * Calcula a distância entre o ponto 1 e o ponto 2
     * @param p1 ponto 1
     * @param p2 ponto 2
     * @return distância entre o ponto 1 e o ponto 2
     */
    public double calcularDistancia(Ponto p1, Ponto p2) {
        return Math.sqrt((Math.pow(p2.x, 2) - Math.pow(p1.x, 2)) + (Math.pow(p2.y, 2) - Math.pow(p1.y, 2)));
    }
}
