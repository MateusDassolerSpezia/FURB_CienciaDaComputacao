import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe2Uni3 {
    public Exe2Uni3() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Valor do sapato: R$ ");
        double valorSapato = sc.nextDouble();

        double desconto = valorSapato * (12/100.0);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Valor do desconto é de: R$ " + df.format(desconto));

        double valorFinal = valorSapato - desconto;
        System.out.print("Valor final: R$ " + df.format(valorFinal));
        

        sc.close();

    }
    
    public static void main(String[] args) {
        new Exe2Uni3();
    }
}
