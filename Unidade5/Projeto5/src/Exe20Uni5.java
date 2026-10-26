import java.util.Scanner;

public class Exe20Uni5 {
    public Exe20Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a massa inicial do material (em Kg): ");
        float massaInicial = sc.nextFloat();

        float massaFinal = massaInicial;
        float tempo = 0;

        while (massaFinal > 0.005f) {
            massaFinal = massaFinal / 2;
            tempo += 50;
        }

        massaFinal = massaFinal * 1000;
        System.out.println("Massa inicial: " + massaInicial + "Kg" + "\n" + "Massa final: " + massaFinal + "g" + "\n" + "Tempo necessário: " + tempo + "s");

        sc.close();
    }
    public static void main(String[] args) {
        new Exe20Uni5();
    }
}
