import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe8Uni2 {
    public Exe8Uni2() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o código da peça 1: ");
        int codigo1 = sc.nextInt();

        System.out.print("Digite o número de peças do código " + codigo1 + ": ");
        int numeroPecas1 = sc.nextInt();

        System.out.print("Digite o valor unitário da peça " + codigo1 + ": R$");
        float valorUnitario1 = sc.nextFloat();

        System.out.print("Digite o código da peça 2: ");
        int codigo2 = sc.nextInt();

        System.out.print("Digite o número de peças do código " + codigo2 + ": ");
        int numeroPecas2 = sc.nextInt();

        System.out.print("Digite o valor unitário da peça " + codigo2 + ": R$" );
        float valorUnitario2 = sc.nextFloat();

        float valorFinal = valorUnitario1 * numeroPecas1 + valorUnitario2 * numeroPecas2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Valor final da compra: R$" + df.format(valorFinal));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe8Uni2();
    }
}
