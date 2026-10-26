/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
import java.io.*;
import java.nio.file.*;
import javax.swing.JTextPane;

public class ArquivoBinario {

    // Salvar o arquivo com extensão .poo
    public void salvar(String caminho, JTextPane textPane) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            // Cria um objeto Serializacao com o texto e o StyledDocument
            Serializacao s = new Serializacao(textPane.getText(), textPane.getStyledDocument());            
            // Serializa o objeto
            oos.writeObject(s);
        }
    }

    // Carregar o arquivo .poo
    public void abrir(String caminho, JTextPane textPane) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            // Desserializa o objeto DocumentoSerializado
            Serializacao s = (Serializacao) ois.readObject();
            
            // Aplica o texto e o StyledDocument ao JTextPane
            textPane.setText(s.getTexto());
            textPane.setStyledDocument(s.getDoc());
        }
    }

    // Verificar se o arquivo existe
    public boolean existeArquivo(String caminho) {
        return Files.exists(Paths.get(caminho));
    }
}
