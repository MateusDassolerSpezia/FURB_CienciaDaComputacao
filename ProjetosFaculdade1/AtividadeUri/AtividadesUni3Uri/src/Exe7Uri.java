import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe7Uri {
    public Exe7Uri() {
    
    
        Scanner sc = new Scanner(System.in);

        String nome = sc.next();
        double salarioFixo = sc.nextDouble();
        double valorVendas = sc.nextDouble();

        double salarioFinal = salarioFixo + valorVendas * (15/100.0);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("TOTAL = " + nome + "R$ " + df.format(salarioFinal));
        //atividade no uri sem o "nome"

        sc.close();

    }
        public static void main(String[] args) {
            new Exe7Uri();
    }
}
