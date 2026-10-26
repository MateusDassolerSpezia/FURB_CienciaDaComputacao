import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe6Uni3 {
    public Exe6Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o peso do prato montado pelo cliente (em Kg): ");
        float pesoPratoMontado = sc.nextFloat();

        float precoPagar = (pesoPratoMontado - 0.75f) * 25;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Valor a pagar: R$ " + df.format(precoPagar));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe6Uni3();
    }
}
