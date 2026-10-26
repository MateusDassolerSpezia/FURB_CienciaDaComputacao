import java.util.InputMismatchException;
import java.util.Scanner;

public class Exe9Uni6 {
    public Exe9Uni6() {
        Scanner sc = new Scanner(System.in);

        int sexo[] = new int[30];
        int nota[] = new int[30];
        int idade[] = new int[30];

        ler(sexo, nota, idade, sc);
        pesquisa(sexo, nota, idade);

        sc.close();

    }
    
    private void ler(int sexo[], int nota[], int idade[], Scanner sc) {
        for (int i = 0; i < idade.length; i++) {
            do { 
                try {
                    System.out.print("Digite o sexo (1 = feminino, 2 = masculino) ["+ (i+1) +"]: ");
                    sexo[i] = sc.nextInt();
                } catch (InputMismatchException e) {  // Tratamento de dado
                    sc = new Scanner(System.in);
                    System.out.println("Informação inválida, deve ser 1 ou 2");
                }
            } while (sexo[i] != 1 && sexo[i] != 2);
            do {
                try {
                    System.out.print("Digite a nota (zero até dez, valor inteiro) ["+ (i+1) +"]: ");
                    nota[i] = sc.nextInt();
                } catch (InputMismatchException e) {  // Tratamento de dado
                    sc = new Scanner(System.in);
                }
            } while (nota[i] < 0 || nota[i] > 10);
            do {
                try {
                    System.out.print("Digite a idade ["+ (i+1) +"]: ");
                    idade[i] = sc.nextInt();
                } catch (InputMismatchException e) {  // Tratamento de dado
                    sc = new Scanner(System.in);
                }
            } while (idade[i] <= 0);
        }
    }
    
    private void pesquisa(int sexo[], int nota[], int idade[]) {
        float somaNotaGeral = 0;
        float mediaGeral = 0;
        float quantidadeHomens = 0;
        float somaNotaHomens = 0;
        float mediaHomens = 0;
        float quantidadeMulheresMais50 = 0;
        float mulherMaisJovem = Float.MAX_VALUE;
        float notaMulherMaisJovem = 0;

        for (int i = 0; i < 5; i++) {
            somaNotaGeral += nota[i];
            if (sexo[i] == 2) {
                somaNotaHomens += nota[i];
                quantidadeHomens++;
            }
        }

        mediaGeral = somaNotaGeral / 5;
        System.out.println("\nNota média recebida pelo cinema: " + mediaGeral);
        mediaHomens = somaNotaHomens / quantidadeHomens;
        System.out.println("Nota média atribuida pelos homens: " + mediaHomens);

        for (int i = 0; i < 5; i++) {
            if (idade[i] < mulherMaisJovem && sexo[i] == 1) {
                mulherMaisJovem = idade[i];
                notaMulherMaisJovem = nota[i];
            }
        }
        System.out.println("Nota atribuída pela mulher mais jovem: " + notaMulherMaisJovem);

        for (int i = 0; i < 5; i++) {
            if (sexo[i] == 1 && idade[i] > 50 && nota[i] > mediaGeral) {
                quantidadeMulheresMais50++;
            }
        }

        System.out.println(quantidadeMulheresMais50 + " mulher(es) com mais de 50 anos deram nota superior a média recebida pelo cinema");
    
    }
    
    public static void main(String[] args) {
        new Exe9Uni6();
    }
}
