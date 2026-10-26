/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model2;

/**
 *
 * @author Mateus
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class EditorTexto {

    private JFrame frame;
    private JTextPane editorPane;
    private JFileChooser fileChooser;
    private File arquivoAtual;
    private JScrollPane scroll;
    private boolean modoNoturno = true;

    public EditorTexto() {
        frame = new JFrame("Editor de Texto");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Usando JTextPane para permitir formatação de texto
        editorPane = new JTextPane();
        editorPane.setContentType("text/plain");  // Definindo o tipo de conteúdo do editor

        // Criando um JScrollPane que envolve o JTextPane
        scroll = new JScrollPane(editorPane);

        // Configurar as barras de rolagem:
        // Elas aparecem apenas quando necessário
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   // Aparece apenas se necessário

        // Adiciona o JScrollPane à janela (frame) na posição central
        frame.add(scroll, BorderLayout.CENTER);
        
        // Configurar a janela
        frame.setVisible(true);
        
        

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false); // tira a borda do menuBar
        frame.setJMenuBar(menuBar);

        // Menu de Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        menuBar.add(menuArquivo);

        JMenuItem itemAbrir = new JMenuItem("Abrir");
        itemAbrir.addActionListener(e -> abrirArquivo());
        menuArquivo.add(itemAbrir);

        JMenuItem itemSalvar = new JMenuItem("Salvar");
        itemSalvar.addActionListener(e -> salvarArquivo());
        menuArquivo.add(itemSalvar);

        // Menu de Estilo
        JMenu menuEstilo = new JMenu("Estilo");
        menuBar.add(menuEstilo);

        JMenuItem itemFontePadrao = new JMenuItem("Fonte Padrão");
        itemFontePadrao.addActionListener(e -> aplicarFontePadrao());
        menuEstilo.add(itemFontePadrao);

        JMenuItem itemNegrito = new JMenuItem("Negrito");
        itemNegrito.addActionListener(e -> aplicarNegrito());
        menuEstilo.add(itemNegrito);

        JMenuItem itemItalico = new JMenuItem("Itálico");
        itemItalico.addActionListener(e -> aplicarItalico());
        menuEstilo.add(itemItalico);

        JMenuItem itemSublinhado = new JMenuItem("Sublinhado");
        itemSublinhado.addActionListener(e -> aplicarSublinhado());
        menuEstilo.add(itemSublinhado);

        JMenuItem itemCor = new JMenuItem("Cor de Texto");
        itemCor.addActionListener(e -> aplicarCor());
        menuEstilo.add(itemCor);

        // Menu de Estilo
        JMenu menuAlinhar = new JMenu("Alinhar");
        menuBar.add(menuAlinhar);
        
        JMenuItem itemAlinharEsquerda = new JMenuItem("Alinhar à Esquerda");
        itemAlinharEsquerda.addActionListener(e -> alinharTexto(StyleConstants.ALIGN_LEFT));
        menuAlinhar.add(itemAlinharEsquerda);

        JMenuItem itemAlinharCentro = new JMenuItem("Alinhar ao Centro");
        itemAlinharCentro.addActionListener(e -> alinharTexto(StyleConstants.ALIGN_CENTER));
        menuAlinhar.add(itemAlinharCentro);

        JMenuItem itemAlinharDireita = new JMenuItem("Alinhar à Direita");
        itemAlinharDireita.addActionListener(e -> alinharTexto(StyleConstants.ALIGN_RIGHT));
        menuAlinhar.add(itemAlinharDireita);
        
        // Menu modo Noturno
        JMenu menuOpcoes = new JMenu("Opções");
        menuBar.add(menuOpcoes);
        
                // Criando o item de menu para alternar modo noturno
        JMenuItem modoNoturnoItem = new JMenuItem("Alternar Modo Noturno");
        modoNoturnoItem.addActionListener(e -> alternarModoNoturno(e));
        menuOpcoes.add(modoNoturnoItem);

        // Menu sobre
        JMenu menuSobre = new JMenu("Sobre");
        menuBar.add(menuSobre);

        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> mostrarSobre());
        menuSobre.add(itemSobre);

        // Configurar JFileChooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos .poo", "poo"));
    }

    private void abrirArquivo() {
        int resultado = fileChooser.showOpenDialog(frame);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            try {
                String texto = ArquivoBinario.abrir(arquivoSelecionado.getAbsolutePath());
                editorPane.setText(texto);
                arquivoAtual = arquivoSelecionado;
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Erro ao abrir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvarArquivo() {
        if (arquivoAtual == null) {
            int resultado = fileChooser.showSaveDialog(frame);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File arquivoSelecionado = fileChooser.getSelectedFile();
                if (!arquivoSelecionado.getName().endsWith(".poo")) {
                    arquivoSelecionado = new File(arquivoSelecionado.getAbsolutePath() + ".poo");
                }
                try {
                    if (ArquivoBinario.existeArquivo(arquivoSelecionado.getAbsolutePath())) {
                        int resposta = JOptionPane.showConfirmDialog(frame,
                                "Arquivo já existe. Deseja substituir?", "Confirmar Substituição", JOptionPane.YES_NO_OPTION);
                        if (resposta == JOptionPane.NO_OPTION) {
                            return;
                        }
                    }
                    ArquivoBinario.salvar(arquivoSelecionado.getAbsolutePath(), editorPane.getText());
                    arquivoAtual = arquivoSelecionado;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void aplicarNegrito() {
        StyledDocument doc = editorPane.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setBold(atributos, true);  // Aplica o negrito
        doc.setCharacterAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
    }

    public void aplicarItalico() {
        StyledDocument doc = editorPane.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setItalic(atributos, true);  // Aplica o itálico
        doc.setCharacterAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
    }

    public void aplicarSublinhado() {
        StyledDocument doc = editorPane.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setUnderline(atributos, true);
        doc.setCharacterAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
    }

    public void aplicarFontePadrao() {
        StyledDocument doc = editorPane.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setBold(atributos, false);   // Remove o negrito
        StyleConstants.setItalic(atributos, false); // Remove o itálico
        StyleConstants.setUnderline(atributos, false); // Remove o sublinhado
        doc.setCharacterAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
    }

    private void alinharTexto(int alinhamento) {
        StyledDocument doc = editorPane.getStyledDocument();
        SimpleAttributeSet atributos = new SimpleAttributeSet();
        StyleConstants.setAlignment(atributos, alinhamento);
        doc.setParagraphAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
    }

    private void aplicarCor() {
        Color cor = JColorChooser.showDialog(frame, "Escolher cor", Color.BLACK);
        if (cor != null) {
            StyledDocument doc = editorPane.getStyledDocument();
            SimpleAttributeSet atributos = new SimpleAttributeSet();
            StyleConstants.setForeground(atributos, cor);
            doc.setCharacterAttributes(editorPane.getSelectionStart(), editorPane.getSelectionEnd() - editorPane.getSelectionStart(), atributos, false);
        }
    }
    
    private void alternarModoNoturno(ActionEvent e) {
    // Cores para modo escuro
    Color darkEditorBackground = new Color(0x1E1E1E); // #1E1E1E para o JTextPane
    Color darkTextColor = new Color(0xD4D4D4); // #D4D4D4 para o texto do JTextPane
    Color darkMenuBackground = new Color(0x2D2D30); // #2D2D30 para o fundo do JMenuBar e JMenuItem
    Color darkMenuTextColor = new Color(0xE0E0E0); // #E0E0E0 para os textos do JMenuBar e JMenuItem
    Color darkFrameBackground = new Color(0x2D2D30); // #2D2D30 para a cor de fundo da janela (JFrame)
    Color darkFrameTextColor = new Color(0xE0E0E0); // #E0E0E0 para os textos do JFrame

    // Cores para modo claro
    Color lightEditorBackground = new Color(0xFFFFFF); // #FFFFFF para o JTextPane
    Color lightTextColor = new Color(0x000000); // #000000 para o texto do JTextPane
    Color lightMenuBackground = new Color(0xF1F1F1); // #F1F1F1 para o fundo do JMenuBar e JMenuItem
    Color lightMenuTextColor = new Color(0x000000); // #000000 para os textos do JMenuBar e JMenuItem
    Color lightFrameBackground = new Color(0xF1F1F1); // #F1F1F1 para o fundo da janela (JFrame)
    Color lightFrameTextColor = new Color(0x000000); // #000000 para os textos do JFrame

    // Definir cores com base no estado atual do modo
    Color editorBackground = modoNoturno ? darkEditorBackground : lightEditorBackground;
    Color textColor = modoNoturno ? darkTextColor : lightTextColor;
    Color menuBackground = modoNoturno ? darkMenuBackground : lightMenuBackground;
    Color menuTextColor = modoNoturno ? darkMenuTextColor : lightMenuTextColor;
    Color frameBackground = modoNoturno ? darkFrameBackground : lightFrameBackground;
    Color frameTextColor = modoNoturno ? darkFrameTextColor : lightFrameTextColor;

    // Alterar cores do JTextPane
    editorPane.setBackground(editorBackground);
    editorPane.setForeground(textColor);

    // Alterar cores do JFrame
    frame.getContentPane().setBackground(frameBackground);
    frame.setForeground(frameTextColor);

    // Alterar cores da JMenuBar e seus itens
    JMenuBar menuBar = frame.getJMenuBar();
    if (menuBar != null) {
        menuBar.setBackground(menuBackground);
        menuBar.setForeground(menuTextColor);

        // Iterar por cada JMenu e alterar suas cores
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu != null) {
                menu.setBackground(menuBackground);
                menu.setForeground(menuTextColor);

                // Alterar cores de todos os JMenuItems
                for (int j = 0; j < menu.getItemCount(); j++) {
                    JMenuItem item = menu.getItem(j);
                    if (item != null) {
                        item.setBackground(menuBackground);
                        item.setForeground(menuTextColor);
                    }
                }
            }
        }
    }

    // Alternar o estado do modo noturno
    modoNoturno = !modoNoturno;
}
    
    
    

    private void mostrarSobre() {
        JOptionPane.showMessageDialog(frame, "Equipe: João, Maria, Pedro\nAno: 2024", "Sobre", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibir() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditorTexto editor = new EditorTexto();
            editor.exibir();
        });
    }
}
