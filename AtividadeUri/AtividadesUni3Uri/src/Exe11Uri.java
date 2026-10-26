import java.util.Scanner;

public class Exe11Uri {
    public Exe11Uri() {
        Scanner sc = new Scanner(System.in);

        int distancia = sc.nextInt();

        int tempo = distancia * 2;

        System.out.println(tempo + " minutos");

        sc.close();


    }
    public static void main(String[] args) {
        new Exe11Uri();
    }

}