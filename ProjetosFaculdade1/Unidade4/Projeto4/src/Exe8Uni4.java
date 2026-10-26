import java.util.Scanner;

public class Exe8Uni4 {
    public Exe8Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe a letra: ");
        char letra = sc.next().charAt(0);

        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
            System.out.println("É vogal");
        }
        else {
            System.out.println("NÃO é vogal");
        }
        

        sc.close();
    }
    public static void main(String[] args) {
        new Exe8Uni4();
    }
}
