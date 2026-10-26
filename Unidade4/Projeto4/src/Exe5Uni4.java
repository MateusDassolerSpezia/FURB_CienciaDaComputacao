import java.util.Scanner;

public class Exe5Uni4 {
    public Exe5Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("A cor é azul?");
        boolean resposta = sc.nextBoolean();

        if (resposta) {
            System.out.println("Sim");
        }
        if (!resposta) {
            System.out.println("Não");
        }



        sc.close();
    }
    public static void main(String[] args) {
        new Exe5Uni4();
    }
}
