import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe17Uni5 {
    public Exe17Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o número de inscrição do atleta: ");
        int numeroInscriçao = sc.nextInt();
        while (numeroInscriçao <= 0) {
            System.out.println("Valor incorreto: número deve ser maior que 0");
            System.out.println("Digite o número de inscrição do atleta: ");
            numeroInscriçao = sc.nextInt();
        }

        float altura = 0;

        float quantidadeAtletas = 0;
        float atletaMaisAlto = Float.MIN_VALUE;
        float atletaMaisBaixo = Float.MAX_VALUE;
        float alturaTotal = 0;
        float alturaMedia = 0;

        while (numeroInscriçao != 0) {
            System.out.println("Digite a altura do atleta (em metros):");
            altura = sc.nextFloat();
            if (altura > atletaMaisAlto) {
                atletaMaisAlto = altura;
            }
            if (altura < atletaMaisBaixo) {
                atletaMaisBaixo = altura;
            }
            alturaTotal += altura;
            quantidadeAtletas ++;
            System.out.println("Digite o número de inscrição do atleta: ");
            numeroInscriçao = sc.nextInt();
        }

        alturaMedia = alturaTotal / quantidadeAtletas;

        System.out.println("O atleta mais alto tem " + df.format(atletaMaisAlto) + "m e o atleta mais baixo tem " + df.format(atletaMaisBaixo) + "m");
        System.out.println("A média de altura dos atletas é de: " + df.format(alturaMedia) + "m");


        sc.close();
    }
    public static void main(String[] args) {
        new Exe17Uni5();
    }
}
