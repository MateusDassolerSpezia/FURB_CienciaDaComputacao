import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe7Uni3 {
    public Exe7Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Escreva a quantidade de latas: ");
        int quantidadeLatas = sc.nextInt();

        System.out.print("Escreva a quantidade de garrafas 600mL: ");
        int quantidadeGarrafas600mL = sc.nextInt();

        System.out.print("Escreva a quantidade de garrafas 2L: ");
        int quantidadeGarrafas2L = sc.nextInt();

        float litrosComprados = quantidadeLatas * 0.35f + quantidadeGarrafas600mL * 0.6f + quantidadeGarrafas2L * 2;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Litros comprados: " + df.format(litrosComprados) + "L");
        



        sc.close();

    }

    public static void main(String[] args) {
        new Exe7Uni3();
    }
    
}
