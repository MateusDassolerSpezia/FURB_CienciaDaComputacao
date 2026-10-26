import java.util.Scanner;

public class Exe19Uni4 {
    public Exe19Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.print("X = ");
        float x = sc.nextFloat();

        System.out.print("Y = ");
        float y = sc.nextFloat();

        if (x == 0 && y == 0) {
            System.out.println("Quadrante 0");
        } else if (x > 0 && y > 0) {
            System.out.println("Quadrante 1");
        } else if (x < 0 && y > 0) {
            System.out.println("Quadrante 2");
        } else if (x < 0 && y < 0) {
            System.out.println("Quadrante 3");
        } else if (x > 0 && y < 0) {
            System.out.println("Quadrante 4");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe19Uni4();
    }
}
