/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author mdspezia
 */
public class Turma {
    
    private String disciplina;
    private Professor professorTurma;
    private Turno turno;
    private ArrayList<Aluno> alunos;

    public Turma(String disciplina, Professor professor, Turno turno) {
        setDisciplina(disciplina);
        setProfessorTurma(professorTurma);
        setTurno(turno);
        alunos = new ArrayList();
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        if (disciplina.isBlank()) {
            throw new IllegalArgumentException("Disciplina não pode ser vazia");
        }
        this.disciplina = disciplina;
    }

    public Professor getProfessorTurma() {
        return professorTurma;
    }

    public void setProfessorTurma(Professor professor) {
        if (professorTurma == null) {
            throw new IllegalArgumentException("Professor deve ser informado");
        }
        this.professorTurma = professor;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("Turno deve ser informado");
        }
        this.turno = turno;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void incluirAluno(Aluno a) {
        if (a == null) {
            throw new IllegalArgumentException("Aluno inválido");
        }
        if (buscar(a.getMatricula(), a.getNome()) != null) {
            throw new IllegalArgumentException("Aluno ja existe");
        }
        alunos.add(a);
    }
    
    private Aluno buscar(int matricula, String nome) {
        for (Aluno a: alunos) {
            if (a.getMatricula() == matricula && a.getNome().equalsIgnoreCase(nome)) {
                return a;
            }
        }
        return null;
    }
    
    public void removerAluno(int matricula, String nome) {              
        Aluno alunoRemover = buscar(matricula, nome);
        if (alunoRemover == null) {
            throw new IllegalArgumentException("Aluno deve ser informado"); 
        }
        alunos.remove(alunoRemover);
    }
    
    public Aluno obterAlunoMelhorNotaEnem() {
        double notaEnem = 0;
        Aluno notaMaior = null;
        
        /*for (int i = 0; i < alunos.size(); i++) {
            if (valorMaior < alunos.get(i).getNotaEnem()) {
                valorMaior = alunos.get(i).getNotaEnem();
                notaMaior = alunos.get(i);
            }
        }*/
        
        for (Aluno a : alunos) {
            if (a.getNotaEnem() > notaEnem) {
                notaEnem = a.getNotaEnem();
                notaMaior = a;
            }
        }
        return notaMaior;
    }
    
    public String listarAlunos(){
        String dados = "";
        for (Aluno a : alunos){
            dados += a.getNome() +" " +a.getNotaEnem()+ "\n";
        }
        return dados;
    }
}
