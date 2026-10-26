import java.util.Scanner;

public class Exe30Uni5 {
    public Exe30Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número para iniciar a sequência (N):");
        int n = sc.nextInt();
        while (n < 0) {
            System.out.println("Valor incorreto: número deve ser maior que 0");
            System.out.println("Digite um número para iniciar a sequência (N):");
            n = sc.nextInt();
        }

        int k = 0;
        do {
            System.out.println("Digite o decremento (K):");
            k = sc.nextInt();
            if (k < 0) {
                System.out.println("Valor incorreto: número deve ser maior que 0");
        }
        } while (k < 0);

        int m = 0;
        do {
            System.out.println("Digite o tamanho da mochila (M):");
            m = sc.nextInt();
        } while (m < 0);

        String elementosNaMochila = "";
        String elementosForaMochila = "";
        int somaMochila = 0;
        int somaForaMochila = 0;

        System.out.print("Os elementos a serem colocados na mochila são: ");
        while (n > 0) {
            System.out.print(n + " ");
            n -= k;
            
            if (m > 0) {
                elementosNaMochila += n + ", ";
                somaMochila += n;
                m--;
            } else {
                elementosForaMochila += n + ", ";
                somaForaMochila += n;
            }
        }

        System.out.println("Elementos na mochila: " + elementosNaMochila);
        System.out.println("Soma da mochila: " + somaMochila);
        System.out.println("Não couberam na mochila: " + elementosForaMochila);
        System.out.println("Soma fora da mochila: " + somaForaMochila);
        


        sc.close();
    }
    public static void main(String[] args) {
        new Exe30Uni5();
    }
}
