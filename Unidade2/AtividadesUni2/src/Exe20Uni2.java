import java.util.Scanner;

public class Exe20Uni2 {
    public Exe20Uni2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número par de dobras feitas no papel:");
        int numeroDobras = sc.nextInt();
        
        if (numeroDobras % 2 == 0) {
            int novosQuadrados = numeroDobras * 2;
            System.out.println("Podem ser vistos " + novosQuadrados + " quadrados após desdobra-lo");
        } else {
            System.out.println("Valor inválido");
        }
        
        sc.close();
    
    }
    public static void main(String[] args) {
        new Exe20Uni2();
    }
}
