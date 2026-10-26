import java.util.Scanner;

public class Exe22Uni4 {
    public Exe22Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha um curso:");
        System.out.println("1: Ciência da Computação");
        System.out.println("2: Licenciatura da Computação");
        System.out.println("3: Sistemas de Informação");

        int curso = sc.nextInt();

        switch (curso) {
            case 1: System.out.println("Vai receber o título: Bacharel em Ciência da Computação");
                break;
            case 2: System.out.println("Vai receber o título: Licenciado em Computação");
                break;
            case 3: System.out.println("Vai receber o título: Bacharel em Sistemas de Informação");
                break;
            default: System.out.println("Escolha inválida");
        }

        sc.close();
    }
    public static void main(String[] args) {
        new Exe22Uni4();
    }
}
