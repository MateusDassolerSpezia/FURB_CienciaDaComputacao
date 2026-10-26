/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heranca_atributos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class PrincipalPessoa {
    public PrincipalPessoa() {
        Aluno a1 = new Aluno(1, "Ana", 123456, new Date());
        Aluno a2 = new Aluno(2, "Bruno", 987654, new Date());
        
        Professor p1 = new Professor("Luciana", 111222, new Date(), 12);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String dados = a1.getNome() + "\n" + a1.getCpf() + "\n" + sdf.format(a1.getDataNascimento() + "\n" + a1.getMatricula());
        JOptionPane.showMessageDialog(null, dados);
        
        dados = a2.getNome() + "\n" + a2.getCpf() + "\n" + sdf.format(a2.getDataNascimento() + "\n" + a2.getMatricula());
        JOptionPane.showMessageDialog(null, dados);
        
        dados = p1.getNome() + "\n" + p1.getCpf() + "\n" + sdf.format(p1.getDataNascimento() + "\n" + p1.getCodigo());
        JOptionPane.showMessageDialog(null, dados);
        
        /*Pessoa pessoas[] = new Pessoa[5];
        pessoas[0] = a1;
        pessoas[1] = a2;
        pessoas[2] = p1;*/
        
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(a1);
        pessoas.add(a2);
        pessoas.add(p1);
        
        dados = "";
        //for (int i = 0; i < pessoas.size(); i++) {
        //    Pessoa p = pessoas.get(i);
        for (Pessoa p: pessoas) {
            if (p instanceof Aluno) {
                Aluno a= (Aluno) p;
                dados += a.getMatricula() + "\n";
            } else if (p instanceof Professor) {
                dados += ((Professor)p).getCodigo() + "\n";
            }
            dados += p.getNome() + "\n" + p.getCpf() + "\n" + sdf.format(p.getDataNascimento()) + "\n\n";
        }
        JOptionPane.showMessageDialog(null, dados);
    }
    public static void main(String[] args) {
        new PrincipalPessoa();
    }
}
