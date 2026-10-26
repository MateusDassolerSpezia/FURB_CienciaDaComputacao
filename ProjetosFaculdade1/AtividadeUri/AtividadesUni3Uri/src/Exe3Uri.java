import java.util.Scanner;

public class Exe3Uri {
    public Exe3Uri() {
    
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        int PROD = A * B;

        System.out.println("PROD = " + PROD);


        sc.close();

    }
        public static void main(String[] args) {
            new Exe3Uri();
    }
    
}
