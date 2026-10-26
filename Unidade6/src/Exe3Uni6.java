import java.util.Scanner;

public class Exe3Uni6 {
    public Exe3Uni6() {
        Scanner sc = new Scanner(System.in);

        float vetor1[] = new float[12];

        ler(sc, vetor1);
        modificacao(vetor1);
        escrever(vetor1);


        sc.close();
    }

    public void ler(Scanner sc, float vetor[]) {
        System.out.println("Popular vetor:");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Número [" + (i) + "]: ");
            vetor[i] = sc.nextFloat();
        }
    }
        

    public void modificacao(float vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            if (i % 2 == 0) {
                vetor[i] += vetor[i] * 0.02f;
            } else {
                vetor[i] += vetor[i] * 0.05f;
            }
        }
        
    }

    public void escrever(float vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(vetor[i]);
        }
    }

    

    
    public static void main(String[] args) {
        new Exe3Uni6();
    }
}
