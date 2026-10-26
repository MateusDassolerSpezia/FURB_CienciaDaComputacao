import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe13Uni5 {
    public Exe13Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite a quantidade de paradas:");
        int QuantidaParadas = sc.nextInt();

        float odometroDuranteViagem = 0;
        float odometroAposViagem = 0;
        float quantidadeCombustivelTotal = 0;

        for (int paradas = 0; paradas < QuantidaParadas; paradas++) {
            System.out.println("Digite o valor do odômetro antes da parada:");
            float odometro = sc.nextFloat();
            System.out.println("Digite o valor do odômetro na parada:");
            odometroDuranteViagem = sc.nextFloat();
            System.out.println("Digite a quantidade de combustível comprado:");
            float quantidadeCombustivel = sc.nextFloat();
            float quilometragemLitroParada = (odometroDuranteViagem - odometro) / quantidadeCombustivel;
            System.out.println("A quilometragem por litro até a parada foi de " + df.format(quilometragemLitroParada) + "Km/L");
            odometroAposViagem += odometroDuranteViagem - odometro;
            quantidadeCombustivelTotal += quantidadeCombustivel;
        }

        float mediaViagem = odometroAposViagem / quantidadeCombustivelTotal;
        System.out.println("A média na viagem foi de " + df.format(mediaViagem) + "Km/L");


        sc.close();
        
    }
    public static void main(String[] args) {
        new Exe13Uni5();
    }
}
