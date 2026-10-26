import java.util.Scanner;

public class Exe10Uni2 {
    public Exe10Uni2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o tempo (em segundos) do evento:");
        int tempoSegundos = sc.nextInt();

        int horas = tempoSegundos / 3600;
        tempoSegundos %= 3600;
        int minutos = tempoSegundos / 60;
        tempoSegundos %= 60;
        int segundos = tempoSegundos;

        System.out.println("Horas: " + horas + "\n" + "Minutos: " + minutos + "\n" + "Segundos: " + segundos);


        sc.close();
    }
    public static void main(String[] args) {
        new Exe10Uni2();
    }
}
