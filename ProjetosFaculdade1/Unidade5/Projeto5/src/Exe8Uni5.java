import java.util.Scanner;

public class Exe8Uni5 {
    public Exe8Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de números a ser informado: ");
        int n = sc.nextInt();

        int menorNegativo = 0;
        float mediaPositivos = 0;
        float somaPositivos = 0;
        float quantidadePositivos = 1;

        for (int quantidade = 0; quantidade < n; quantidade++) {
            System.out.print("Digite o número: ");
            int numero = sc.nextInt();

            if (numero < menorNegativo) {
                menorNegativo = numero;
            }
            if (numero > 0) {
                somaPositivos += numero;
                quantidadePositivos++;
            }
        }

        mediaPositivos = somaPositivos / quantidadePositivos;

        System.out.println("Menor número negativo é " + menorNegativo + "\nA média dos números positivos é " + mediaPositivos);


        sc.close();
        
    }
    public static void main(String[] args) {
        new Exe8Uni5();
    }
}
