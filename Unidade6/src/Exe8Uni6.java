import java.util.Scanner;

public class Exe8Uni6 {
    public Exe8Uni6() {
        Scanner sc = new Scanner(System.in);

        int n = 0;
        do {
        System.out.println("Digite o tamanho do vetor (max 20): ");
        n = sc.nextInt();
        } while (n > 20);
    
        float numeros[] = new float[n];

        ler(numeros, sc);
        imprimirValores(numeros);
    
        sc.close();
    }
        
    private void ler(float vetor[], Scanner sc) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Número [" + (i+1) + "]: ");
            vetor[i] = sc.nextFloat();
        }
    }

    private void imprimirValores(float numeros[]) {
        float numerosDiferentes[] = new float[numeros.length];
        int tamanho = 0;

        for (int i = 0; i < numeros.length; i++) {
            boolean achou = false;
            for (int j= 0; j < tamanho; j++) {
                if (numeros[i] == numerosDiferentes[j]) {
                    achou = true;
                }
            }
            if (!achou) {
                numerosDiferentes[tamanho] = numeros[i];
                tamanho++;
        }
    }
        for (int i = 0; i < tamanho; i++) {
            int quantidadeRepeticao = 0;
            for (int j = 0; j < numeros.length; j++) {
                if (numerosDiferentes[i] == numeros[j]) {
                    quantidadeRepeticao++;
                }
            }
            System.out.println(numerosDiferentes[i] + " | " + quantidadeRepeticao);
        }
        System.out.println("Fim");
    }

    public static void main(String[] args) {
        new Exe8Uni6();
    }
}
