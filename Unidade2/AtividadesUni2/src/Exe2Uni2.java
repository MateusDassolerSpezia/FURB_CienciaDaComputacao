import java.util.Scanner;

public class Exe2Uni2 {
    public Exe2Uni2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite 2 valores: ");
        int valor1 = sc.nextInt();
        int valor2 = sc.nextInt();

        int multiplicacao = valor1 * valor2;
        System.out.println("multiplicação = " + multiplicacao);


        sc.close();
    }
    public static void main(String[] args) {
        new Exe2Uni2();
    }
}
