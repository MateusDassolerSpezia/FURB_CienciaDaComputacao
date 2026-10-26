import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe23Uni5 {
    public Exe23Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o nome do vendedor:");
        String nome = sc.next();
        System.out.println("Digite a quantidade de produtos vendidos pelo vendedor no mês:");
        int produtos = sc.nextInt();

        float comissao = 0;
        float totalVendas = 0;

        int contador = 0;
        while (contador < produtos) {
            System.out.println("Digite o preço do produto:");
            float precoProduto = sc.nextFloat();
            comissao += precoProduto * 0.3f;
            totalVendas += precoProduto;
            contador++;
        }

        System.out.println(nome + " teve um total de vendas de R$ " + df.format(totalVendas) + " e um salário de R$ " + df.format(comissao));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe23Uni5();
    }
}
