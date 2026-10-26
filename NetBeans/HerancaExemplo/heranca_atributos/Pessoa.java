/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heranca_atributos;

import java.util.Date;

/**
 * Classe base para pessoas
 * @author mdspezia
 */
public abstract class Pessoa {
    
    private String nome;
    private int cpf;
    private Date dataNascimento;

    public Pessoa(String nome, int cpf, Date dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome nao pode ser vazio");
        }
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        if (cpf == 0) {
            throw new IllegalArgumentException("Cpf incorreto");
        }
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data incorreta");
        }
        this.dataNascimento = dataNascimento;
    }
    
    
}
