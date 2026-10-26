import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe5Uni3 {
    public Exe5Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a quantidade de galinhas: ");
        int quantidadeGalinhas = sc.nextInt();
        int anelShip = quantidadeGalinhas;

        int aneisAlimentacao = quantidadeGalinhas * 2;

        float precoPagar = anelShip * 4 + aneisAlimentacao * 3.5f;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Gasto total para marcar todos os frangos: R$ " + df.format(precoPagar));


        sc.close();

    }
    public static void main(String[] args) {
        new Exe5Uni3();
    }
}
