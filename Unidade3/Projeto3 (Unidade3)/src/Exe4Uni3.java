import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe4Uni3 {
    public Exe4Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a nota 1: ");
        float nota1 = sc.nextFloat();

        System.out.print("Digite a nota 2: ");
        float nota2 = sc.nextFloat();

        System.out.print("Digite a nota 3: ");
        float nota3 = sc.nextFloat();

        float media = (nota1 * 5 + nota2 * 3 + nota3 * 2) / 10;
        DecimalFormat df = new DecimalFormat("0.00"); 
        System.out.print("Média = " + df.format(media));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe4Uni3();
    }
    
}
