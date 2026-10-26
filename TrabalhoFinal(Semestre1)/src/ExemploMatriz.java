import java.util.Scanner;

public class ExemploMatriz {
    public ExemploMatriz() {

        int numeros [][] = new int[3][3];

        //numeros[0][0] = 5;

        //Popular valores:
        Scanner sc = new Scanner(System.in);
        for (int linha = 0; linha < numeros.length; linha++) {
            for (int coluna = 0; coluna < numeros[linha].length; coluna++) {
                System.out.print("Número[" + linha + "][" + coluna + "]: ");
                numeros[linha][coluna] = sc.nextInt();
            }
        }

        for (int i = 0; i < numeros.length; i++) { //Linhas
            for (int j = 0; j < numeros[i /*ou 0*/].length; j++) { //Colunas
                System.out.print(numeros[i][j] + " ");
            }
            System.out.println("");
        }

        sc.close();
    }
    public static void main(String[] args) {
        new ExemploMatriz();
    }
}
