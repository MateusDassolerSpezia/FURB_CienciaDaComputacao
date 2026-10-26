import java.util.Scanner;

public class Prova1Questao1 {
    public Prova1Questao1() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite o código de categoria (A, X ou W):");
        char codigoCategoria = sc.next().toUpperCase().charAt(0);

        if (codigoCategoria == 'A') {
            System.out.println("Classe A");
        } else if (codigoCategoria == 'X') {
            System.out.println("Classe Média");
        } else if (codigoCategoria == 'W') {
            System.out.println("Cliente Idoso");
        } else {
            System.out.println("Entrada Incorreta");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Prova1Questao1();
    }
}
