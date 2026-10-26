/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model2;

/**
 *
 * @author Mateus
 */
import java.io.*;
import java.nio.file.*;

public class ArquivoBinario {

    // Salvar o arquivo com extensão .poo
    public static void salvar(String caminho, String texto) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(texto);
        }
    }

    // Carregar o arquivo .poo
    public static String abrir(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (String) ois.readObject();
        }
    }

    // Verificar se o arquivo existe
    public static boolean existeArquivo(String caminho) {
        return Files.exists(Paths.get(caminho));
    }
}
