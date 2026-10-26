/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.io.*;
import java.nio.file.*;
import javax.swing.JTextPane;

/**
 * Classe ArquivoBinario
 * @author Mateus e Vinícius - 2024
 */
public class ArquivoBinario {
        
    /**
     * Salvar o arquivo com extensão .poo
     *
     * @param caminho é o "endereço" do arquivo
     * @param textPane é o jTextPane1 onde é escrito o texto e os estilos
     * @throws IOException lida com erros relacionados a saída de arquivos
     */
    public void salvar(String caminho, JTextPane textPane) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            // Cria um objeto Serializacao com o texto e o StyledDocument
            Serializacao s = new Serializacao(textPane.getText(), textPane.getStyledDocument());
            // Serializa o objeto
            oos.writeObject(s);
        } 
    }

    /**
     * Carregar o arquivo .poo
     *
     * @param caminho é o "endereço" do arquivo
     * @param textPane é o jTextPane1 onde é escrito o texto e os estilos
     * @throws IOException lida com erros relacionados a entrada de arquivos
     * @throws ClassNotFoundException trata a classe que está sendo desserializado
     */
    public void abrir(String caminho, JTextPane textPane) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            // Desserializa o objeto DocumentoSerializado
            Serializacao s = (Serializacao) ois.readObject();

            // Aplica o texto e o StyledDocument ao JTextPane
            textPane.setText(s.getTexto());
            textPane.setStyledDocument(s.getDoc());
        } 
    }

    /**
     * Verificar se o arquivo existe
     *
     * @param caminho é o "endereço" do arquivo
     * @return retorna o "endereço" do arquivo
     */
    public boolean existeArquivo(String caminho) {
        return Files.exists(Paths.get(caminho));
    }
 }

