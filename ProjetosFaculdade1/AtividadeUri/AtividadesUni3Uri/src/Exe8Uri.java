import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe8Uri {
    public Exe8Uri() {
        Scanner sc = new Scanner(System.in);

        
        int codigo1 = sc.nextInt(); int numeroPecas1 = sc.nextInt(); double valorUnitario1 = sc.nextDouble(); 

        int codigo2 = sc.nextInt(); int numeroPecas2 = sc.nextInt(); double valorUnitario2 = sc.nextDouble();

        double valorFinal = valorUnitario1 * numeroPecas1 + valorUnitario2 * numeroPecas2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("VALOR A PAGAR PELA" + codigo1 + "e" + codigo2 + ":R$ "  + df.format(valorFinal));
        //atividade no uri sem os códigos


        sc.close();
    }
    public static void main(String[] args) {
        new Exe8Uri();
    }
    
}
