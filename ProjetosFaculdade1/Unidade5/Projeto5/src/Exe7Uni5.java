import java.util.Scanner;

public class Exe7Uni5 {
    public Exe7Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de números a ser informado: ");
        int n = sc.nextInt();

        float maior = Float.MIN_VALUE; // Menor número possível
        float menor = Float.MAX_VALUE; // Maior número possível

        for (int quantidade = 0; quantidade < n; quantidade++) {
            System.out.print("Digite o número: ");
            float numero = sc.nextFloat();
            if (numero > maior){
                maior = numero;
            }
            if (numero < menor) {
                menor = numero;
            }
        }

        System.out.println("Maior número é " + maior + "\nMenor número é " + menor);


        sc.close();
    }
    public static void main(String[] args) {
        new Exe7Uni5();
    }
}
