/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heranca_atributos;

import java.util.Date;

/**
 *
 * @author mdspezia
 */
public class Aluno extends Pessoa{
    
    private int matricula;

    public Aluno(int matricula, String nomeAluno, int cpfAluno, Date dataNasc) { // Nome dos parâmetros não importa, o importante é o tipo de dado
        super(nomeAluno, cpfAluno, dataNasc);
        setMatricula(matricula);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matricula deve ser maior que zero");
        }
        this.matricula = matricula;
    }
    
    
}
