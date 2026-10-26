import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe7Uni2 {
    public Exe7Uni2(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do vendedor: ");
        String nome = sc.next();

        System.out.print("Digite o salário fixo: R$");
        float salarioFixo = sc.nextFloat();

        System.out.print("Digite o total de vendas efetuadas no mês (em dinheiro): R$");
        float valorVendas = sc.nextFloat();

        float salarioFinal = salarioFixo + valorVendas * (15/100f);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("O vendedor" + nome + "tem salário final de: R$" + df.format(salarioFinal));



        

        sc.close();

    }
    public static void main(String[] args) {
        new Exe7Uni2();
    }
    
}
