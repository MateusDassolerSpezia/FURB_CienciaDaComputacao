/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class ContadorTags {
    private String[] tags;
    private int[] contadores;
    private int tamanho;

    /**
     * Construtor que inicializa os arrays internos.
     */
    public ContadorTags() {
        tags = new String[50];
        contadores = new int[50];
        tamanho = 0;
    }

    /**
     * Adiciona uma tag ao contador ou incrementa se ela já estiver presente.
     * @param tag Nome da tag a ser adicionada.
     */
    public void adicionar(String tag) {
        tag = tag.toLowerCase();
        for (int i = 0; i < tamanho; i++) {
            if (tags[i].equals(tag)) {
                contadores[i]++;
                return;
            }
        }
        tags[tamanho] = tag;
        contadores[tamanho] = 1;
        tamanho++;
    }

    public String[] getTags() {
        return tags;
    }

    public int[] getContadores() {
        return contadores;
    }

    public int getTamanho() {
        return tamanho;
    }
}

