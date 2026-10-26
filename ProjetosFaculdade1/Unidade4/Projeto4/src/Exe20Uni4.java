import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe20Uni4 {
    public Exe20Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0");

        System.out.println("Digite a nota da prova1, prova2, prova3 e dos exercícios respectivamente: ");
        float notaProva1 = sc.nextFloat();
        float notaProva2 = sc.nextFloat();
        float notaProva3 = sc.nextFloat();
        float notaExercicios = sc.nextFloat();

        float media = (notaProva1 + notaProva2 * 2 + notaProva3 * 3 + notaExercicios) / 7;
        System.out.println("Média de aproveitamento do aluno = " + df.format(media));

        if (media >= 9){
            System.out.println("conceito do aluno: A");
            System.out.println("Aprovado");
        } else if (media >= 7.5 && media < 9) {
            System.out.println("conceito do aluno: B");
            System.out.println("Aprovado");
        } else if (media >= 6 && media < 7.5) {
            System.out.println("conceito do aluno: C");
            System.out.println("Aprovado");
        } else if (media >= 4 && media < 6) {
            System.out.println("conceito do aluno: D");
            System.out.println("Reprovado");
        } else if (media < 4) {
            System.out.println("conceito do aluno: E");
            System.out.println("Reprovado");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe20Uni4();
    }
}
