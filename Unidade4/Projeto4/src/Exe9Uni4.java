import java.util.Scanner;

public class Exe9Uni4 {
    public Exe9Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com 2 valores inteiros:");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        if (num1 % num2 == 0 || num2 % num1 == 0) {
            System.out.println("São múltiplos");
        } else {
            System.out.println("Não são múltiplos");
        }

        sc.close();
    }
    public static void main(String[] args) {
        new Exe9Uni4();
    }
    
}
