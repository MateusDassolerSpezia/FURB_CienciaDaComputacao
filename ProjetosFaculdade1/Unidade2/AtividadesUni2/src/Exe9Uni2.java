import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe9Uni2 {
    public Exe9Uni2() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o valor em dolares: ");
        float valorDolar = sc.nextFloat();

        float valorReal = valorDolar * 4.97f;
        System.out.println("valor em reais: " + df.format(valorReal));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe9Uni2();
    }
}
