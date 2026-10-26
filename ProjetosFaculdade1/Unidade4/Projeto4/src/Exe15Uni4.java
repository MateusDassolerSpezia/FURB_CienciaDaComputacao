import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe15Uni4 {
    public Exe15Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite a quantidade de meses admitido: ");
        int meses = sc.nextInt();

        System.out.println("Digite o salário atual: R$ ");
        float salarioAtual = sc.nextFloat();

        if (meses < 13) {
            float salarioReajuste = salarioAtual + (salarioAtual * 5/100f);
            System.out.println("Valor do reajuste no salário = R$ " + df.format(salarioReajuste));
        } else if (meses > 12 && meses < 49) {
            float salarioReajuste = salarioAtual + (salarioAtual * 7/100f);
            System.out.println("Valor do reajuste do salário = R$ " + df.format(salarioReajuste));
        }

        
        sc.close();
    }
    public static void main(String[] args) {
        new Exe15Uni4();
    }
}
