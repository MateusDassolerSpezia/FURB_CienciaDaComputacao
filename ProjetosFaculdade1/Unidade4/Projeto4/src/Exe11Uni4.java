import java.util.Scanner;

public class Exe11Uni4 {
    public Exe11Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o ano de nascimento de 3 irmãos: ");
        int irmao1 = sc.nextInt();
        int irmao2 = sc.nextInt();
        int irmao3 = sc.nextInt();

        if (irmao1 == irmao2  && irmao1 == irmao3) {
            System.out.println("São trigêmeos");
        } else if (irmao1 == irmao2 && irmao1 != irmao3 || irmao1 != irmao2 && irmao1 == irmao3 || irmao2 != irmao1 && irmao2 == irmao3) {
            System.out.println("São gêmeos");
        } else {
            System.out.println("São apenas irmãos");
        }

        /*if (irmao1 == irmao2  && irmao1 == irmao3) {
            System.out.println("São trigêmeos");
        } else if (irmao1 != irmao2 && irmao1 != irmao3 && irmao2 != irmao3) {
            System.out.println("São apenas irmãos");
        } else {
            System.out.println("São Gêmeos");
        }/* */


        sc.close();
    }
    public static void main(String[] args) {
        new Exe11Uni4();
    }
}
