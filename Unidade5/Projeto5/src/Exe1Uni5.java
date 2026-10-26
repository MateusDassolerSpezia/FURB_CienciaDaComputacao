import java.util.Scanner;

public class Exe1Uni5 {
    public Exe1Uni5() {
        Scanner sc = new Scanner(System.in);

        for (int quantidade = 0; quantidade < 20; quantidade++) {
            System.out.println("Digite um número: ");
            int numero = sc.nextInt();

            if (numero % 2 == 0) {
                System.out.println("Par");
            } else {
                System.out.println("Impar");
            }
        }


        sc.close();

    }
    
    public static void main(String[] args) {
        new Exe1Uni5();
    }
}
