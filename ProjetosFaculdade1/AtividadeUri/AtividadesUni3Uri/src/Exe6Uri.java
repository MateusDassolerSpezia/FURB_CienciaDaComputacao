import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe6Uri {
    public Exe6Uri() {
    
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        int horasTrabalhadas = sc.nextInt();
        double valorHora = sc.nextDouble();

        double salario = horasTrabalhadas * valorHora;

        System.out.println("NUMBER = " + number);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("SALARY = U$ " + df.format(salario));

        sc.close();

    }    
        public static void main(String[] args) {
            new Exe6Uri();
    }
    
}
