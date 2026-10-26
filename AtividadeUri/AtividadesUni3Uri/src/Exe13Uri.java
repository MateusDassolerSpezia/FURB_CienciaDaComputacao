import java.util.Scanner;

public class Exe13Uri {
    public Exe13Uri() {
        Scanner sc = new Scanner(System.in);

        int totalSegundos = sc.nextInt();

        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = ((totalSegundos % 3600) % 60) / 1;

        System.out.println(horas + " : " + minutos + " : " + segundos);


        sc.close();

    }
    public static void main(String[] args) {
        new Exe13Uri();
    }
    
}
