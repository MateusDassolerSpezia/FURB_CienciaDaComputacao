/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

/**
 *
 * @author Mateus
 */
public class Cliente {

    private String nome;
    private String endereco;

    public Cliente(String nome, String endereco) {
        setNome(nome);
        setEndereco(endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco.isBlank()) {
            throw new IllegalArgumentException("Endereco não pode ser vazio");
        }
        this.endereco = endereco;
    }
}
