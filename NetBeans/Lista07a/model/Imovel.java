/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class Imovel {
    
    private String endereco;
    private int area;
    private Bairro bairro;
    private Finalidade finalidade;

    public Imovel(Bairro bairro, Finalidade finalidade, String endereco, int area) throws IllegalArgumentException {
        setEndereco(endereco);
        setArea(area);
        setBairro(bairro);
        setFinalidade(finalidade);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        if (area < 0) {
            throw new IllegalArgumentException("Área não pode ser negativa");
        }
        this.area = area;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Finalidade getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(Finalidade finalidade) {
        if (finalidade == null) {
          throw new IllegalArgumentException("A finalidade deve ser informada");
        }            
        this.finalidade = finalidade;
    }
    
    public double calcularIptu() throws IllegalArgumentException {  
        if (bairro == null) {
            throw new IllegalArgumentException("Bairro não pode ser vazio");
        }
        else if (bairro.getCoeficienteIptu() < 0) {
            throw new IllegalArgumentException("Coeficiente não pode ser negativo");
        }
        else if (finalidade == null) {
            throw new IllegalArgumentException("Finalidade não pode ser vazia");
        }
        double iptu = 0;
        switch(finalidade) {
            case RESIDENCIAL:
                iptu = area * bairro.getCoeficienteIptu();
                break;
            case COMERCIAL:
                if (area <= 100) {
                    iptu = 500;
                } else if (area > 100 && area <= 400) {
                    iptu = 1000;
                } else if (area > 400) {
                    iptu = area * 2.55;
                }
                break;
            case INDUSTRIAL:
                if (area <= 2000) {
                    iptu = 1000;
                } else if (area > 2000) {
                    iptu = area * 0.55;
                }
                break;
        }
         return iptu;
    }  
}
