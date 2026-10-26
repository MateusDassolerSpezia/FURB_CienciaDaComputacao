import java.util.Scanner;

public class Exe29Uni5 {
    public Exe29Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor da compra: ");
        int valorCompra = sc.nextInt();

        while (valorCompra != 0) {
        int cedula20 =  valorCompra / 20;
        valorCompra = valorCompra % 20;
        int cedula10 = valorCompra / 10;
        valorCompra = valorCompra % 10;
        int cedula5 = valorCompra / 5;
        valorCompra = valorCompra % 5;
        int cedula2 = valorCompra / 2;
        valorCompra = valorCompra % 2;
        int cedula1 = valorCompra;

        System.out.println(cedula20 + " cédula(s) de R$ 20,00");
        System.out.println(cedula10 + " cédula(s) de R$ 10,00");
        System.out.println(cedula5 + " cédula(s) de R$ 5,00");
        System.out.println(cedula2 + " cédula(s) de R$ 2,00");
        System.out.println(cedula1 + " cédula(s) de R$ 1,00");

        System.out.println("Digite o valor da compra: ");
        valorCompra = sc.nextInt();
        }

        sc.close();
    }
    public static void main(String[] args) {
        new Exe29Uni5();
    }
}
