import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe8Uni3 {
    public Exe8Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Escreva o valor em dólares: US$");
        float valorDolar = sc.nextFloat();

        float cotacao = 4.97f;

        float valorReal = valorDolar * cotacao;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Valor em reais: R$ " + df.format(valorReal));


        sc.close();
    }

    public static void main(String[] args) {
        new Exe8Uni3();
    }
    
}
