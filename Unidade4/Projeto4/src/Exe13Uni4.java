import java.util.Scanner;

public class Exe13Uni4 {
    public Exe13Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe as 3 cartas: ");
        int carta1 = sc.nextInt();
        int carta2 = sc.nextInt();
        int carta3 = sc.nextInt();

        int qtCartasBoas = 0;

        if (carta1 == 1 || carta1 == 2 || carta1 == 3) {
            qtCartasBoas += 1;
        } if (carta2 == 1 || carta2 == 2 || carta2 == 3) {
            qtCartasBoas = qtCartasBoas + 1;
        } if (carta3 == 1 || carta3 == 2 || carta3 == 3) {
            qtCartasBoas++;
        } if (qtCartasBoas == 1){
            System.out.println("TRUCO");
        } else if (qtCartasBoas == 2) {
            System.out.println("SEIS");
        } else if (qtCartasBoas == 3) {
            System.out.println("NOVE");
        }

        
        sc.close();
    }
    public static void main(String[] args) {
        new Exe13Uni4();
    }
}
