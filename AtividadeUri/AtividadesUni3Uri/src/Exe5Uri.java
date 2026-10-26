import java.util.Scanner;

public class Exe5Uri {
    public Exe5Uri() {
    
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        int DIFERENCA = (A * B - C * D);

        System.out.println("DIFERENCA = " + DIFERENCA);

        sc.close();

    }
        public static void main(String[] args) {
            new Exe5Uri();
    }
    
}
