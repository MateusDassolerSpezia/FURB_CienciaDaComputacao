/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cidade;

/**
 *
 * @author lpa
 */
public class ArquivoMunicipio {

    private ArrayList<Cidade> cidades;

    public ArquivoMunicipio() {
        cidades = new ArrayList();
        lerArquivo();
        Cidade maior = maiorPopulacao();
        System.out.println("Maior cidade: " + maior.getCidade() + " - " + 
                maior.getPopulacao());
        
        Cidade menor = menorPopulacao();
        System.out.println("Menor cidade: " + menor.getCidade() + " - " + 
                menor.getPopulacao());
    }

    private Cidade maiorPopulacao() {
        Cidade maiorCidade = null;
        int maiorPopulacao = 0;
        for (Cidade c: cidades) {
            if (c.getPopulacao() > maiorPopulacao) {
                maiorCidade = c;
                maiorPopulacao = c.getPopulacao();
            }
        }
        return maiorCidade;
    }
    
    private Cidade menorPopulacao() {
        Cidade menorCidade = null;
        int menorPopulacao = Integer.MAX_VALUE;
        for (Cidade c: cidades) {
            if (c.getPopulacao()  < menorPopulacao) {
                menorCidade = c;
                menorPopulacao = c.getPopulacao();
            }
        }
        return menorCidade;
    }
    
    public void lerArquivo() {
        try {
            Scanner arqTexto = new Scanner(new File("C:\\Users\\mdspezia\\Downloads\\L14_municipios.csv"));
            while (arqTexto.hasNext()) {
                //Criar cidade
                String linha = arqTexto.nextLine();
                String partesCidade[] = linha.split(";");
                Cidade cidade = null;
                try {
                    cidade = new Cidade(Integer.parseInt(partesCidade[0]),
                            partesCidade[1], partesCidade[2],
                            Integer.parseInt(partesCidade[3]));
                    cidades.add(cidade);
                } catch (Exception e) {
                }
               
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ArquivoMunicipio();
    }
}