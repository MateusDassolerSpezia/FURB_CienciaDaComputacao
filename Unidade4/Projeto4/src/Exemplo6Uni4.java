import java.util.Scanner;

public class Exemplo6Uni4 {
    public Exemplo6Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um valor true ou false: ");
        boolean valor = sc.nextBoolean();

        if (valor) {
            System.out.println("É verdade");
        } else {
            System.out.println("É falso");
        }

        if (!valor) {
            System.out.println("É falso");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exemplo6Uni4();
    }
}
