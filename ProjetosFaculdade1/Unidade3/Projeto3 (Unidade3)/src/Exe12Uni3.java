import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe12Uni3 {
    public Exe12Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do funcionário: ");
        String nome = sc.next();

        System.out.print("Digite o número de horas trabalhadas: ");
        float horasTrabalhadas = sc.nextFloat();

        System.out.print("Digite o número de dependentes: ");
        int numeroDependentes = sc.nextInt();

        float salarioBruto = horasTrabalhadas * 10 + numeroDependentes * 60;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(nome + " tem um salário bruto de: R$ " + df.format(salarioBruto));

        float salarioLiquido = salarioBruto - salarioBruto * (13.5f / 100);
        System.out.print(nome + " tem um salário líquido de: R$ " + df.format(salarioLiquido));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe12Uni3();
    }
    
}
