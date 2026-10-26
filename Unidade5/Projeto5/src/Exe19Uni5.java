import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe19Uni5 {
    public Exe19Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o valor da compra:");
        float valorCompra = sc.nextFloat();

        float valorDesconto = 0;
        float valorFinal = 0;
        float totalRecebido = 0;

        while (valorCompra != 0) {
            if (valorCompra > 500) {
                valorDesconto = valorCompra * 0.2f;
                valorFinal = valorCompra - valorDesconto;
                System.out.println("Total a pagar: R$ " + df.format(valorFinal));
            }
            if (valorCompra <= 500) {
                valorDesconto = valorCompra * 0.15f;
                valorFinal = valorCompra - valorDesconto;
                System.out.println("Total a pagar: R$ " + df.format(valorFinal));
            }
            totalRecebido += valorFinal;
            System.out.println("Digite o valor da compra:");
            valorCompra = sc.nextFloat();
        }

        System.out.println("Total recebido pela loja: R$ " + df.format(totalRecebido));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe19Uni5();
    }
}
