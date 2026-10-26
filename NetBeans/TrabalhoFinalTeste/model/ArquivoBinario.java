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
//import java.util.ArrayList;

public class ArquivoBinario {

    //private Serializacao s;
    
    // Salvar o arquivo com extensão .poo
    public void salvarArquivo(String caminho, TextoFormatado textoFormatado) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(textoFormatado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o arquivo .poo
    public TextoFormatado abrirArquivo(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (TextoFormatado) in.readObject();  // Retorna o objeto TextoFormatado
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Verificar se o arquivo existe
    public boolean arquivoExiste(String caminho) {
        return Files.exists(Paths.get(caminho));
    }
}
