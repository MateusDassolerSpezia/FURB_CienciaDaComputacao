import java.util.Scanner;

public class Exe15Uni5 {
    public Exe15Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do aluno:");
        String nome = sc.next();

        while (!nome.trim().equals("fim")) {
            System.out.println("Digite a nota das duas provas:");
            float nota1 = sc.nextFloat();
            float nota2 = sc.nextFloat();
            float media = (nota1 + nota2) / 2;
            System.out.println(nome + " tem média " + media);
            System.out.println("Digite o nome do aluno:");
            nome = sc.next();
            
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe15Uni5();
    }
}
