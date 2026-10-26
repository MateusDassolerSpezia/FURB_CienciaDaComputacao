import java.text.DecimalFormat;
import java.util.Scanner;

public class Prova1Questao2 {
    public Prova1Questao2() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o valor das 3 últimas inscrições:");
        float valorInscricao1 = sc.nextFloat();
        float valorInscricao2 = sc.nextFloat();
        float valorInscricao3 = sc.nextFloat();

        System.out.println("R$ " + df.format(valorInscricao1));
        System.out.println("R$ " + df.format(valorInscricao2));
        System.out.println("R$ " + df.format(valorInscricao3));

        System.out.println("Menu:");
        System.out.println("a) Valor da inscrição mais alta");
        System.out.println("b) Valor da inscrição mais baixa");
        System.out.println("c) Valores impressos em ordem decrescente (mais alta para a mais baixa)");
        System.out.println("d) Média das três inscrições informadas");

        char opcao = sc.next().charAt(0);

        float maior = 0;
        float menor = 0;
        float meio = 0;

        if (valorInscricao1 > valorInscricao2 && valorInscricao1 > valorInscricao3) {
            maior = valorInscricao1;
        } else if (valorInscricao2 > valorInscricao3) {
            maior = valorInscricao2;
        } else {
            maior = valorInscricao3;
        }

        if (valorInscricao1 < valorInscricao2 && valorInscricao1 < valorInscricao3) {
            menor = valorInscricao1;
        } else if (valorInscricao2 < valorInscricao3) {
            menor = valorInscricao2;
        } else {
            menor = valorInscricao3;
        }

        if (valorInscricao1 != maior && valorInscricao1 != menor) {
            meio = valorInscricao1;
        } else if (valorInscricao2 != maior && valorInscricao2 != menor) {
            meio = valorInscricao2;
        } else {
            meio = valorInscricao3;
        }

        switch (opcao) {
            case 'a': 
            System.out.println("R$ " + df.format(maior));
                break;
            case 'b': 
            System.out.println("R$ " + df.format(menor));
                break;
            case 'c':
            System.out.println("R$ " + df.format(maior) + " R$ " + df.format(meio) + " R$ " + df.format(menor));
                break;
            case 'd':
            float media = (maior + meio + menor) / 3;
            System.out.println("R$ " + df.format(media));
        }
            

        sc.close();
    }
    public static void main(String[] args) {
        new Prova1Questao2();
    }
}
