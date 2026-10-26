/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class Turma {

    private Aluno aluno;
    private float n1;
    private float n2;
    private float n3;

    public Turma(Aluno aluno) {
        setAluno(aluno);
    }

    public float calcularMedia() {
        return (n1 + n2 + n3) / 3;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) throws IllegalArgumentException {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser vazio");
        }
        this.aluno = aluno;
    }

    public float getN1() {
        return n1;
    }

    public void setN1(float n1) throws IllegalArgumentException {
        if (n1 < 0) {
            throw new IllegalArgumentException("Nota deve ser maior que zero");
        }
        this.n1 = n1;
    }

    public float getN2() {
        return n2;
    }

    public void setN2(float n2) throws IllegalArgumentException {
        if (n2 < 0) {
            throw new IllegalArgumentException("Nota deve ser maior que zero");
        }
        this.n2 = n2;
    }

    public float getN3() {
        return n3;
    }

    public void setN3(float n3) throws IllegalArgumentException {
        if (n3 < 0) {
            throw new IllegalArgumentException("Nota deve ser maior que zero");
        }
        this.n3 = n3;
    }

}
