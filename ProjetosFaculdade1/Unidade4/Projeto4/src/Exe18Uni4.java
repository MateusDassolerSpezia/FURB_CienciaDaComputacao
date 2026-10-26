import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe18Uni4 {
    public Exe18Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o dia do vencimento (até o dia 10): ");
        int diaVencimento = sc.nextInt();

        System.out.println("Digite o dia do pagamento: ");
        int diaPagamento = sc.nextInt();

        int atraso = diaPagamento - diaVencimento;
        
        System.out.println("Digite o valor da prestação: R$ ");
        float valorPrestaçao = sc.nextFloat();

        if (atraso <= 0) {
            Float valorFinal = valorPrestaçao - valorPrestaçao * 10/100f;
            System.out.println("Valor a ser pago: R$ " + df.format(valorFinal));
            System.out.println("O pagamento está em dia");
        } else if (atraso > 0 && atraso <= 5) {
            System.out.println("Valor a ser pago: R$ " + df.format(valorPrestaçao));
            System.out.println("O pagamento está atrasado em " + atraso + " dias");
        } else if (atraso > 5) {
            Float valorFinal = valorPrestaçao + (valorPrestaçao * (atraso * 2/100f));
            System.out.println("Valor a ser pago: R$ " + df.format(valorFinal));
            System.out.println("O pagamento está atrasado em " + atraso + " dias");
        }


        sc.close();
        }
    public static void main(String[] args) {
        new Exe18Uni4();
    }
}
