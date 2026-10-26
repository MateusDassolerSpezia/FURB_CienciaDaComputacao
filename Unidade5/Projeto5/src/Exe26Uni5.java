import java.util.Scanner;

public class Exe26Uni5 {
    public Exe26Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor limite do pedágio:");
        float pedagioLimite = sc.nextFloat();

        System.out.println("Digite o valor do pedágio:");
        float pedagio = sc.nextFloat();

        
        float distancia = 0;
        int negaPagar = 0;
        int trechos = 0;
        int km150Pagar = 0;

        while (pedagio > 0) {
            System.out.println("Digite a distância (em Km):");
            distancia = sc.nextFloat();
            trechos += 1;
            if (pedagio > pedagioLimite) {
                negaPagar += 1;
            } else if (pedagio <= pedagioLimite && distancia > 150) {
                km150Pagar += 1;
            }
            System.out.println("Digite o valor do pedágio:");
            pedagio = sc.nextFloat();
        }

        System.out.println(negaPagar + " trechos com valor acima do qual ele nega-se a pagar");
        System.out.println(trechos + " é a quantidade de trechos informados");
        System.out.println(km150Pagar + " trechos acima de 150km com valor aceito por ele");


        sc.close();
    }
    public static void main(String[] args) {
        new Exe26Uni5();
    }
}
