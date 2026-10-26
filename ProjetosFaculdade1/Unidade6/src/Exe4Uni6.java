import java.util.Scanner;

public class Exe4Uni6 {
    public Exe4Uni6() {

        int vetor1[] = new int[10];
        int vetor2[] = new int[10];

        ler(vetor1, vetor2);
        int[] v3 = somar(vetor1, vetor2);
        escrever(v3);
    }    

        private void ler(int[] vetor1, int vetor2[]) {
            Scanner sc = new Scanner(System.in);
    
            System.out.println("Popular vetor 1: ");
            for (int i = 0; i < vetor1.length; i++) {
                System.out.print("Número " + (i+1) + ": ");
                vetor1[i] = sc.nextInt();
            }
    
            System.out.println("\nPopular vetor 2:");
            for (int i = 0; i < vetor2.length; i++) {
                System.out.print("Número " + (i+1) + ":");
                vetor2[i] = sc.nextInt();
            }
            sc.close();
    }

        private int[] somar(int vetor1[], int vetor2[]) {
            int vetor3[] = new int[10];
            for (int i = 0; i < vetor3.length; i++) {
                vetor3[i] = vetor1[i] + vetor2[i];
            }
            return vetor3;
        }

        private void escrever(int vetor[]) {
            for (int pos = 0; pos < vetor.length; pos++) {
                System.out.println(vetor[pos]);
            }
        }

    public static void main(String[] args) {
        new Exe4Uni6();
    }
}
