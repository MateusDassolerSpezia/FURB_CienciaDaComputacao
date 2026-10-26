/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditorTexto {

    private JTextArea areaTexto;
    private JFrame frame;
    private ArquivoBinario arquivoBinario;
    private List<String> formatações;

    public EditorTexto() {
        arquivoBinario = new ArquivoBinario();
        formatações = new ArrayList<>();
        
        frame = new JFrame("Editor de Texto");
        areaTexto = new JTextArea(20, 50);
        JScrollPane scroll = new JScrollPane(areaTexto);
        
        // Botões de estilização
        JButton btnNegrito = new JButton("Negrito");
        btnNegrito.addActionListener(e -> aplicarEstilo("Negrito"));
        
        JButton btnColorido = new JButton("Colorido");
        btnColorido.addActionListener(e -> aplicarEstilo("Colorido"));
        
        JButton btnAlinhamento = new JButton("Alinhar");
        btnAlinhamento.addActionListener(e -> aplicarEstilo("Alinhar"));
        
        // Menu de Arquivos
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemAbrir = new JMenuItem("Abrir");
        itemAbrir.addActionListener(e -> {
            try {
                abrirArquivo();
            } catch (IOException ex) {
                Logger.getLogger(EditorTexto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditorTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        itemSalvar.addActionListener(e -> {
            try {
                salvarArquivo();
            } catch (IOException ex) {
                Logger.getLogger(EditorTexto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuArquivo.add(itemAbrir);
        menuArquivo.add(itemSalvar);
        menuBar.add(menuArquivo);
        
        frame.setJMenuBar(menuBar);
        
        // Layout
        JPanel panel = new JPanel();
        panel.add(btnNegrito);
        panel.add(btnColorido);
        panel.add(btnAlinhamento);
        panel.add(scroll);
        
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void aplicarEstilo(String estilo) {
        // Estilos básicos de formatação
        if (estilo.equals("Negrito")) {
            areaTexto.setFont(new Font("Arial", Font.BOLD, 14));
            formatações.add("Negrito");
        } else if (estilo.equals("Colorido")) {
            areaTexto.setForeground(Color.RED);
            formatações.add("Colorido");
        } else if (estilo.equals("Alinhar")) {
            areaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
            formatações.add("Alinhar");
        }
    }

    private void salvarArquivo() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar Arquivo");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos POO", "poo"));
        int resposta = chooser.showSaveDialog(frame);
        
        if (resposta == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            if (!caminho.endsWith(".poo")) {
                caminho += ".poo";
            }

            if (arquivoBinario.arquivoExiste(caminho)) {
                int opcao = JOptionPane.showConfirmDialog(frame, "Arquivo já existe. Deseja substituir?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.NO_OPTION) {
                    salvarArquivo();  // Solicitar outro nome
                    return;
                }
            }

            TextoFormatado textoFormatado = new TextoFormatado(areaTexto.getText(), formatações);
            arquivoBinario.salvarArquivo(caminho, textoFormatado);
            JOptionPane.showMessageDialog(frame, "Arquivo salvo com sucesso!");
        }
    }

    private void abrirArquivo() throws IOException, ClassNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Abrir Arquivo");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos POO", "poo"));
        int resposta = chooser.showOpenDialog(frame);
        
        if (resposta == JFileChooser.APPROVE_OPTION) {
            String caminho = chooser.getSelectedFile().getAbsolutePath();
            TextoFormatado textoFormatado = arquivoBinario.abrirArquivo(caminho);
            
            if (textoFormatado != null) {
                areaTexto.setText(textoFormatado.getTexto());  // Carrega o texto
                formatações = textoFormatado.getFormatações();  // Carrega as formatações
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorTexto());
    }
}
