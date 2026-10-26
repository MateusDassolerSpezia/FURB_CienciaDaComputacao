import java.util.Scanner;

public class Exe1Uni6 {
    public Exe1Uni6() {
        
        int numeros[] = new int[10];

        ler(numeros); //this.ler(numeros);

        escrever(numeros);
    
    }

    //Método sem retorno (void) e sem parâmetro / entrada
    private void ler(int[] numeros) {
        Scanner sc = new Scanner(System.in);
        for (int posicao = 0; posicao < 10 /*ou numeros.length */; posicao++) {
            System.out.print("Digite o número " + (posicao+1) + ": ");   
            numeros[posicao] = sc.nextInt();
        }

        sc.close();
    }

    public void escrever(int[] numeros) {
        for (int i = 9 /*ou vetor.length-1*/; i >= 0; i--)  {
            System.out.println(numeros[i]);
        }
    }

    public static void main(String[] args) {
        new Exe1Uni6();
        }

    }

