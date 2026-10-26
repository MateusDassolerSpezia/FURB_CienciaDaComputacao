/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_1;

/**
 *
 * @author mdspezia
 */
public class Retangulo {

    private int altura;
    private int comprimento;

    /**
     * cria uma instância de um retângulo vazio
     */
    public Retangulo() {

    }

    /**
     * Cria uma instância de um retângulo com valores definidos
     * @param comprimento valor da altura do retângulo
     * @param altura valor do comprimento do retângulo
     * @throws IllegalArgumentException valores não podem ser negativos, nem 0
     */
    public Retangulo(int altura, int comprimento) throws IllegalArgumentException {
        setAltura(altura);
        setComprimento(comprimento);
    }

    public void setAltura(int altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException("Valor incorreto para a altura: " + altura);
        }
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setComprimento(int comprimento) {
        if (comprimento <= 0) {
            throw new IllegalArgumentException("Valor incorreto para o comprimento: " + comprimento);
        }
        this.comprimento = comprimento;
    }

    public int getComprimento() {
        return comprimento;
    }

    /**
     * Calcula o perímetro do retângulo
     * @return perímetro
     */
    public int calcularPerimetro() {
        return (2 * altura) + (2 * comprimento);
    }

    /**
     * Calcula a área do retângulo
     * @return área
     */
    public int calcularArea() {
        return altura * comprimento;
    }
}
