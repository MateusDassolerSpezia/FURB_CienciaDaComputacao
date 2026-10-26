import java.util.Scanner;

public class Exe10Uni4 {
    public Exe10Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com a idade dos filhos respectivamente:");
        int idadeM = sc.nextInt();
        int idadeZ = sc.nextInt();
        int idadeL = sc.nextInt();

        if (idadeM < idadeZ && idadeM < idadeL) {
            System.out.println("Marquinhos é o caçula");
        }
        else if (idadeZ < idadeL) {
            System.out.println("Zezinho é o caçula");
        }
        else {
            System.out.println("Luluzinha é a caçula");
        }
    
        sc.close();
    }
    public static void main(String[] args) {
        new Exe10Uni4();
    }
    
}
