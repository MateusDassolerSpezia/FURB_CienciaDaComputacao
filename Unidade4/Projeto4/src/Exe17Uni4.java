import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe17Uni4 {
    public Exe17Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite a renda anual: R$ ");
        float rendaAnual = sc.nextFloat();

        System.out.println("Digite o número de dependentes: ");
        int numDependentes = sc.nextInt();

        if (rendaAnual <= 2000) {
            System.out.println("Não paga imposto");
        } else if (rendaAnual > 2000 && rendaAnual <= 5000) {
            float rendaLiquida = rendaAnual - rendaAnual * (numDependentes * 2/100f);
            float imposto = rendaLiquida * 5/100f;
            System.out.println("Imposto de Renda a ser pago = R$ " + df.format(imposto));
        } else if (rendaAnual > 5000.01 && rendaAnual <= 10000) {
            float rendaLiquida = rendaAnual - rendaAnual * (numDependentes * 2/100f);
            float imposto = rendaLiquida * 10/100f;
            System.out.println("Imposto de Renda a ser pago = R$ " + df.format(imposto));
        } else if (rendaAnual > 10000) {
            float rendaLiquida = rendaAnual - rendaAnual * (numDependentes * 2/100f);
            float imposto = rendaLiquida * 15/100f;
            System.out.println("Imposto de Renda a ser pago = R$ " + df.format(imposto));
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe17Uni4();
    }
}
