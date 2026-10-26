import java.util.Scanner;

public class Exe5Uni4Uri {
    public Exe5Uni4Uri() {
        Scanner sc = new Scanner(System.in);

        int horaInicio = sc.nextInt();
        int horaFim = sc.nextInt();

        if (horaFim > horaInicio && horaFim < 24 && horaInicio < 24) {
            System.out.println("O JOGO DUROU " + (horaFim - horaInicio) + " HORA(S)");
        } else if (horaFim <= horaInicio) {
            horaInicio = 24 - horaInicio;
            System.out.println("O JOGO DUROU " + (horaInicio + horaFim) + " HORA(S)"); 
        }

        sc.close();
    }
    public static void main(String[] args) {
        new Exe5Uni4Uri();
    }
}
