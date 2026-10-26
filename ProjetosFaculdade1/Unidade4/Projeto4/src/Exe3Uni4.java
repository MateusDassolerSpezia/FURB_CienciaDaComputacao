import java.util.Scanner;

public class Exe3Uni4 {
    public Exe3Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com 2 números inteiros: ");
        int numero1 = sc.nextInt();
        int numero2 = sc.nextInt();

        if(numero1 > numero2) {
            System.out.println(numero1 + " é o maior número");
        }
        else {
            System.out.println(numero2 + " é o maior número");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe3Uni4();
    }
}

