import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe14Uni3 {
    public Exe14Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a distância percorrida (em metros): ");
        int distanciaPercorrida = sc.nextInt();

        System.out.print("Digite o tempo gasto na viagem (em minutos): ");
        int tempoGasto = sc.nextInt();

        float velocidadeMedia = (distanciaPercorrida / tempoGasto) * 3.6f;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Velocidade méida = " + df.format(velocidadeMedia) + " Km/h");

        float combustivelGasto = (distanciaPercorrida / 1000f) / 12;

        System.out.print("Combustível gasto = " + df.format(combustivelGasto) + " L");


        sc.close();
    }
    public static void main(String[] args) {
        new Exe14Uni3();
    }
    
}
