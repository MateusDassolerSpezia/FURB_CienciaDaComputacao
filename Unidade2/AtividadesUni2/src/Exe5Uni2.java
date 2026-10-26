import java.util.Scanner;

public class Exe5Uni2 {
    public Exe5Uni2() {
        Scanner teclado = new Scanner(System.in);

        System.out.print("A: ");
        int a = teclado.nextInt();

        System.out.print("B: ");
        int b = teclado.nextInt();

        System.out.print("C: ");
        int c = teclado.nextInt();

        System.out.print("D: ");
        int d = teclado.nextInt();

        int diferenca = (a * b) - (c * d);
        System.out.print("Diferença = " + diferenca);


        teclado.close();
    }
    
    
    public static void main(String[] args) {
        new Exe5Uni2();
    }
    
}
