import java.util.Scanner;

public class Exe7Uni6 {
    public Exe7Uni6() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o tamanho do vetor: ");
        int n = sc.nextInt();
        int numeros[] = new int[n];

        ler(numeros, sc);
        ordenar(numeros, n);
        escrever(numeros);

        sc.close();
    }

    private void ler(int vetor[], Scanner sc) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Número [" + (i+1) + "]: ");
            vetor[i] = sc.nextInt();
        }
    }

    private int ordenar(int vetor[], int n) {
        int bolha = 0;
        n = n - 1;
        for (int i = 0; i < n;) {
                if (vetor[i] > vetor[i+1]) {
                    bolha = vetor[i];
                    vetor[i] = vetor[i+1];
                    vetor[i+1] = bolha;
                    i = 0;
                } else {
                    i++;
                }
            }
        return bolha;
    }

    private void escrever(int vetor[]) {
        System.out.println("\nVetor ordenado:");
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }
    }

        
    
    public static void main(String[] args) {
        new Exe7Uni6();
    }
}
