import java.util.Scanner;

public class Exe9Uni5 {
    public Exe9Uni5() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite a quantidade de alunos:");
        int n = sc.nextInt();

        int quantidadeMaior20 = 0;
        String nomeAlunos18 = "";

        for (int cont = 1; cont <= n; cont ++) {
            System.out.println("Digite o nome do(a) aluno(a):");
            String nome = sc.next();
            System.out.println("Digite a idade do(a) aluno(a):");
            int idade = sc.nextInt();
        
            if (idade == 18) {
                nomeAlunos18 += nome + "\n";
            } 
            if (idade > 20) {
                quantidadeMaior20 ++;
            }
        
        }
        
        System.out.print("Alunos com 18 anos:" + "\n" + nomeAlunos18);
        System.out.println("aluno(s) com mais de 20 anos:" + "\n" + quantidadeMaior20);


        sc.close();
    }
    public static void main(String[] args) {
        new Exe9Uni5();
    }
}
