import java.util.Scanner;

public class Exe6Uni5 {
    public Exe6Uni5() {
        Scanner sc = new Scanner(System.in);

        float media = 0;
        float alturaTotal = 0;

        for (int pessoas = 1; pessoas <= 20; pessoas++) {
            System.out.println("Digite as alturas: ");
            float altura = sc.nextFloat();
            alturaTotal += altura;
            media = alturaTotal / 20;
        }
        
        System.out.println("Média de altura: " + media + "m");


        sc.close();
    }
    public static void main(String[] args) {
        new Exe6Uni5();
    }
}
