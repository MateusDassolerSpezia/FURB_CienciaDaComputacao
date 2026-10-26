/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

import java.io.File;

/**
 *
 * @author mdspezia
 */
public class Diretorio {

    private String nomeDiretorio;
    private int tamanho;

    public Diretorio(String nomeDiretorio) throws Exception {
        setDiretorio(nomeDiretorio);
        File diretorio = new File(nomeDiretorio);
        if (!diretorio.isDirectory()) {
            throw new Exception("Não é um diretório");
        }
        tamanho = 0;
        tamanho = infoDiretorios(diretorio);
        System.out.println(tamanho);
    }

    public void setDiretorio(String nomeDiretorio) throws Exception {
        if (nomeDiretorio.isBlank()) {
            throw new Exception("Nome deve ser informado");
        }
        this.nomeDiretorio = nomeDiretorio;
    }

    public int infoDiretorios(File diretorio) throws Exception {
        File[] conteudoDiretorio = diretorio.listFiles();
        for (File item : conteudoDiretorio) {
            if (item.isDirectory()) {
                System.out.println(item.getName());
                infoDiretorios(item);
            } else {
                System.out.println(item.getName());
                tamanho += item.length();
            }
        }

        return tamanho;
    }

    public static void main(String[] args) throws Exception {
        new Diretorio("C:\\Users\\mdspezia\\Documents\\NetBeansProjects\\Lista10");
    }
}
