/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package teste;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.nio.file.*;
import model.ArquivoBinario;
import model.Serializacao;

public class ArquivoBinarioTest {
    private ArquivoBinario arquivoBinario;
    private JTextPane textPane;
    private JTextPane novoTextPane;
    private static final String TESTE_CAMINHO_EXISTENTE = "teste_existente.poo";
    private static final String TESTE_CAMINHO_INEXISTENTE = "teste_inexistente.poo";

    @BeforeEach
    void setUp() throws Exception {
        arquivoBinario = new ArquivoBinario();
        textPane = new JTextPane();

        // Cria um arquivo existente para o teste
        textPane.setText("Teste de salvar");
        textPane.setStyledDocument(textPane.getStyledDocument());
        arquivoBinario.salvar(TESTE_CAMINHO_EXISTENTE, textPane);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Remove arquivos criados nos testes
        Files.deleteIfExists(Paths.get(TESTE_CAMINHO_EXISTENTE));
        Files.deleteIfExists(Paths.get(TESTE_CAMINHO_INEXISTENTE));
    }

    @Test
    void testSalvarArquivo() throws IOException {
        String texto = "Teste de salvar";
        textPane.setText(texto);
        arquivoBinario.salvar(TESTE_CAMINHO_EXISTENTE, textPane);

        assertTrue(Files.exists(Paths.get(TESTE_CAMINHO_EXISTENTE)), "O arquivo não foi salvo corretamente.");
    }

    @Test
    void testExisteArquivoInexistente() {
        assertFalse(arquivoBinario.existeArquivo(TESTE_CAMINHO_INEXISTENTE), "O método deveria retornar falso para arquivo inexistente.");
    }

    @Test
    void testExisteArquivoExistente() {
        assertTrue(arquivoBinario.existeArquivo(TESTE_CAMINHO_EXISTENTE), "O método deveria retornar verdadeiro para arquivo existente.");
    }

    @Test()
    void testAbrirArquivo() throws IOException, ClassNotFoundException {
        textPane = new JTextPane();
        textPane.setText("Texto original");
    StyledDocument originalDoc = textPane.getStyledDocument();
    arquivoBinario.salvar(TESTE_CAMINHO_EXISTENTE, textPane);

    
    novoTextPane = new JTextPane();
    arquivoBinario.abrir(TESTE_CAMINHO_EXISTENTE, novoTextPane);
    StyledDocument novoDoc = novoTextPane.getStyledDocument();

    // Comparar texto
    assertEquals(textPane.getText(), novoTextPane.getText(), "O texto não foi carregado corretamente.");

    // Comparar número de elementos
    assertEquals(originalDoc.getLength(), novoDoc.getLength(), "O documento carregado não possui o mesmo tamanho.");
    }

    @Test
    void testAbrirArquivoInexistente() throws IOException{
        assertThrows(IOException.class, () -> {
            arquivoBinario.abrir(TESTE_CAMINHO_INEXISTENTE, textPane);
        }
                , "A exceção não foi lançada para arquivo inexistente.");
    }

    @Test
    void testSerializacao() {
        String texto = "Texto Teste";
        StyledDocument doc = new JTextPane().getStyledDocument();

        Serializacao serializacao = new Serializacao(texto, doc);

        assertEquals(texto, serializacao.getTexto(), "O texto armazenado não está correto.");
        assertEquals(doc, serializacao.getDoc(), "O StyledDocument armazenado não está correto.");
    }
}
