import java.util.Scanner;

public class Exe6Uni6 {
    public Exe6Uni6() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o tamanho do vetor: ");
        int n = sc.nextInt();
        float numeros[] = new float[n];

        ler(numeros, sc);
        
        System.out.println("Digite um número para busca: ");
        float numeroBusca = sc.nextFloat();
        if (encontrarValor(numeroBusca, numeros)) {
            System.out.println("Encontrou o valor!");
        } else { 
            System.out.println("Valor não encontrado");
        }

        sc.close();
    }

    private void ler(float vetor[], Scanner sc) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Digite um número real [" + (i+1) + "]: ");
            vetor[i] = sc.nextFloat();
        }
    }
        
    private boolean encontrarValor(float numero, float vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == numero) {
                return true;
            }
        }
        return false;
    }
        
    public static void main(String[] args) {
        new Exe6Uni6();
    }
}
