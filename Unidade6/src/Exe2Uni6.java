import java.util.Scanner;

public class Exe2Uni6 {
    public Exe2Uni6() {
        Scanner sc = new Scanner(System.in);

        float vetor[] = new float[12];
        
        ler(sc, vetor);
        float mediaA = media(vetor);
        escrever(vetor, mediaA);

        sc.close();
    }

    private void ler(Scanner sc, float[] vetor){   
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite o número " + (i+1) + ": ");   
            vetor[i] = sc.nextFloat();
        }

    }

    private float media(float vetor[]) {
        float media = 0;
        float soma = 0;
        for (int i = 0; i < vetor.length; i++) {
        soma += vetor[i];
        }
        media = soma / 12;
        return media;
    }

    private void escrever(float vetor[], float mediaA) {
        System.out.println("\nA média dos valores é: " + mediaA);
        System.out.println("Números maiores do que a média:");
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] > mediaA) {
                System.out.println(vetor[i]);
            }
        }
    }


        
    
    public static void main(String[] args) {
        new Exe2Uni6();
    }
}
