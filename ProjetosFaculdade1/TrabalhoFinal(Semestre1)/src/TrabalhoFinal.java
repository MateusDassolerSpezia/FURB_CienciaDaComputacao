import java.util.Scanner;

public class TrabalhoFinal {
    public TrabalhoFinal() {

        Scanner s = new Scanner(System.in);
        char[][] agua = new char[8][8];
        char[][] sorteioNavios = new char[8][8];
        int[] sorteioLinhas = new int[10];
        int[] sorteioColunas = new int[10];
        int contadorJogadas = 0;
        int contadorAcertos = 0;

        inicializarTabuleiro(agua);

        sortear(sorteioLinhas, sorteioColunas, sorteioNavios);

        for (int linha = 0; linha < sorteioNavios.length; linha++) { // print da matriz Navios (TIRAR DEPOIS)
            for (int coluna = 0; coluna < sorteioNavios.length; coluna++) {

                System.out.print(sorteioNavios[linha][coluna] + " ");
            }
            System.out.println("");
        }
        System.out.println(""); // print da matriz Navios (TIRAR DEPOIS)

        int linha = 0;
        int coluna = 0;
        while (contadorJogadas < 30 && contadorAcertos < 10) {
            do {
                System.out.println("Digite as coordenadas de ataque:");
                System.out.print("Linha: ");
                linha = s.nextInt();
                while (linha < 0 || linha > 7) {
                    System.out.println("\nVocê atacou em uma linha Inválida");
                    System.out.print("Linha: ");
                    linha = s.nextInt();
                }
                System.out.print("Coluna: ");
                coluna = s.nextInt();
                while (coluna < 0 || coluna > 7) {
                    System.out.println("\nVocê atacou em uma coluna Inválida!");
                    System.out.print("Coluna: ");
                    coluna = s.nextInt();
                }
                if (agua[linha][coluna] == 'N' || agua[linha][coluna] == 'O') {
                    System.out.println("\nVocê atacou no mesmo lugar, digite outra coordenada!");
                }
            } while (agua[linha][coluna] == 'N' || agua[linha][coluna] == 'O');

            interacaoJogador(agua, sorteioNavios, contadorJogadas, contadorAcertos, linha, coluna);

            if (feedbackJogador(agua, linha, coluna)) {
                contadorAcertos++;
            }
            contadorJogadas++;

            for (int i = 0; i < agua.length; i++) {
                for (int j = 0; j < agua.length; j++) {

                    System.out.print(agua[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("");

        }

        condicaoParada(contadorAcertos, contadorJogadas, sorteioNavios);

        s.close();
    }

    // Inicializa a matriz preenchendo com '~'
    // colocar retorno (oq é)
    // colocar tudo que vai utilizar nos parametros
    private void inicializarTabuleiro(char[][] agua) { // Preenche a matriz "agua" com '~'
        for (int linha = 0; linha < agua.length; linha++) {
            for (int coluna = 0; coluna < agua.length; coluna++) {

                agua[linha][coluna] = '~';
                System.out.print(agua[linha][coluna] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // Sorteia a posição dos 10 navios na matriz
    private char[][] sortear(int[] sorteioLinhas, int[] sorteioColunas, char[][] sorteioNavios) { //
        for (int linha = 0; linha < sorteioNavios.length; linha++) { // popular (Matriz Navios)
            for (int coluna = 0; coluna < sorteioNavios.length; coluna++) {

                sorteioNavios[linha][coluna] = '~';
            }
        }
        for (int i = 0; i < 10; i++) {
            sorteioLinhas[i] = (int) ((Math.random() * 7) - 0);
            sorteioColunas[i] = (int) ((Math.random() * 7) - 0);
            for (int j = 0; j < 10; j++) {
                if (j != i) {
                    if (sorteioLinhas[i] == sorteioLinhas[j] && sorteioColunas[i] == sorteioColunas[j]) {
                        i = i - 1;
                    }
                } else {
                    sorteioNavios[sorteioLinhas[i]][sorteioColunas[i]] = 'N';

                }
            }
        }
        return sorteioNavios;
    }

    private char[][] interacaoJogador(char agua[][], char sorteioNavios[][], int contadorJogadas, int contadorAcertos,
            int linha, int coluna) { //

        if (sorteioNavios[linha][coluna] == 'N') {
            agua[linha][coluna] = 'X';
            contadorAcertos++;
        } else {
            agua[linha][coluna] = 'O';
            contadorJogadas++;
        }
        return agua;
    }

    private boolean feedbackJogador(char[][] agua, int linha, int coluna) { //

        if (agua[linha][coluna] == 'X') {
            System.out.println("Você acertou um Barco!");
            return true;
        } else {
            System.out.println("Você errou!");
        }
        return false;
    }

    private void condicaoParada(int contadorAcertos, int contadorJogadas, char sorteioNavios[][]) { //
        if (contadorAcertos == 10) {
            System.out.println("Você venceu!");
            System.out.println("Posição dos navios:");
            for (int linha = 0; linha < sorteioNavios.length; linha++) {
                for (int coluna = 0; coluna < sorteioNavios.length; coluna++) {

                    System.out.print(sorteioNavios[linha][coluna] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        } else if (contadorJogadas == 30) {
            System.out.println("Você foi derrotado!");
            System.out.println("Posição dos navios:");
            for (int linha = 0; linha < sorteioNavios.length; linha++) {
                for (int coluna = 0; coluna < sorteioNavios.length; coluna++) {

                    System.out.print(sorteioNavios[linha][coluna] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        new TrabalhoFinal();
    }
}