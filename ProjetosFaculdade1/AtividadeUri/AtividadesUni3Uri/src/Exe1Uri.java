import java.util.Scanner;

public class Exe1Uri {
    public Exe1Uri() {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        int X = A + B;

        System.out.println("X = " + X);

        
        sc.close();

    }
        public static void main(String[] args) {
            new Exe1Uri();
    }
}
