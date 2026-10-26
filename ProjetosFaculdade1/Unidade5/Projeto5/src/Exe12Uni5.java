import java.util.Scanner;

public class Exe12Uni5 {
    public Exe12Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de linhas a ser informado: ");
        int n = sc.nextInt();
        int numero = 1;

        for (int linhas = 1; linhas <= n; linhas++) {
            for (int colunas = 1; colunas <= linhas; colunas++) {
                System.out.print(numero + " ");
                numero++;
            }
            System.out.println();
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe12Uni5();
    }
}
