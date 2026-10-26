import java.util.Scanner;

public class Exe1Uni2 {
    public Exe1Uni2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite 2 valores: ");
        int valor1 = sc.nextInt();
        int valor2 = sc.nextInt();

        int soma = valor1 + valor2;
        System.out.println("soma = " + soma);


        sc.close();
    }
    public static void main(String[] args) {
        new Exe1Uni2();
    }
}
