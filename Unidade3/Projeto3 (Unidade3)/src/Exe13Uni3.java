import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe13Uni3 {
    public Exe13Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o comprimento da parede (em metros): ");
        float comprimentoParede = sc.nextFloat();

        System.out.print("Digite a altura da parede (em metros): ");
        float alturaParede = sc.nextFloat();

        float areaParede = comprimentoParede * alturaParede;

        float valorGasto = 12.5f * (areaParede * 9);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("valor gasto com a compra de azulejos: R$ " + df.format(valorGasto));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe13Uni3();
    }
    
}
