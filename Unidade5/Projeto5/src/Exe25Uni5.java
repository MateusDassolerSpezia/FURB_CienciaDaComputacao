import java.util.Scanner;

public class Exe25Uni5 {
    public Exe25Uni5() {
        Scanner sc = new Scanner(System.in);

        int pontosD = 0;
        int pontosE = 0;

        while (pontosD != 21) {
            System.out.println("Digite de quem foi o ponto:");
            char ponto = sc.next().toUpperCase().charAt(0);
            if (ponto == 'D') {
                pontosD += 1;
            } else if (ponto == 'E') {
                pontosE += 1;
            }
        }
        if (pontosD > pontosE) {
            System.out.println("O vencedor foi o jogador do lado direito");
        } else {
            System.out.println("O vencedor foi o jogador do lado esquerdo");
        }

        sc.close();
        
    }
    public static void main(String[] args) {
        new Exe25Uni5();
    }
}
