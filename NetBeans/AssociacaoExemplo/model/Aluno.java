/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author mdspezia
 */
public class Aluno {

    private String nome;
    private Date dataNascimento;
    private int matricula;

    public Aluno(String nome, Date dataNascimento, int matricula) throws IllegalArgumentException {
        setNome(nome);
        setDataNascimento(dataNascimento);
        setMatricula(matricula);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) throws IllegalArgumentException {
        Date dataLimite = new Date(); //inicia com a data de hoje

        if (dataNascimento == null || dataNascimento.after(dataLimite)) {
            throw new IllegalArgumentException("data deve ser menor que data atual");
        }
        this.dataNascimento = dataNascimento;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) throws IllegalArgumentException {
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matricula deve ser maior que zero");
        }
        this.matricula = matricula;
    }

}
