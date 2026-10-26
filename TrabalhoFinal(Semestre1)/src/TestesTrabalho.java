import java.util.Scanner;

public class TestesTrabalho {
    public TestesTrabalho() {
        Scanner sc = new Scanner(System.in);

        char agua[][] = new char[8][8];
        char navios[][] = new char[8][8];
        //System.out.println("0 1 2 3 4 5 6 7");
        
        for (int i = 0; i < agua.length; i++) { //Linhas
            for (int j = 0; j < agua[i].length; j++) { //Colunas
                agua[i][j] = '~';
                System.out.print(agua[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println();

        for (int i = 0; i < navios.length; i++) { //Linhas
            for (int j = 0; j < navios[i].length; j++) { //Colunas
                navios[i][j] = '~';
                navios[4][4] = 'N';
                System.out.print(navios[i][j] + " ");
            }
            System.out.println("");
        }

        //navios[4][4] = 'N';

        interacaoJogador(sc, agua, navios);

        for (int i = 0; i < agua.length; i++) { //Linhas
            for (int j = 0; j < agua[i].length; j++) { //Colunas
                System.out.print(agua[i][j] + " ");
            }
            System.out.println("");
        }

        sc.close();
    }

    private void inicializacaoTabuleiro(char agua[]) {
        
}

    private void posicionamnetoNavios() {

}

    private void interacaoJogador(Scanner sc, char agua[][], char navios[][]) {
        System.out.println("Digite as coordenadas de ataque:");
        System.out.print("Linha: ");
        int linha = sc.nextInt();
        System.out.print("Coluna: ");
        int coluna = sc.nextInt();

        if (navios[linha][coluna] == 'N') {
            agua[linha][coluna] = 'X';
        } else {
            agua[linha][coluna] = 'O';
        }
}

    private void feedbackAtaque() {

}

    private void condicaoVitoria() {
    
}

    public static void main(String[] args) {
        new TestesTrabalho();
    }
}
