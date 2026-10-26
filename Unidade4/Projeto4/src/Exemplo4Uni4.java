import java.util.Scanner;

public class Exemplo4Uni4 {
    public Exemplo4Uni4() {
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

        
        //if (qtCartasBoas == 1){
        //    System.out.println("TRUCO");
        //} else if (qtCartasBoas == 2) {
        //    System.out.println("SEIS");
        //} else if (qtCartasBoas == 3) {
        //    System.out.println("NOVE");
        //}

        switch (qtCartasBoas) {
            case 1: System.out.println("TRUCO");
                break;
            case 2:  System.out.println("SEIS");
                break;
            case 3: System.out.println("NOVE");
                break;
            default: System.out.println("Segue o jogo");        
        }

        sc.close();
    }
    
    public static void main(String[] args) {
        new Exemplo4Uni4();
    }
}
