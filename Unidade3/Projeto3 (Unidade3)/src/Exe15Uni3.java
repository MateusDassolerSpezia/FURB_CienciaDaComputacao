import java.util.Scanner;

public class Exe15Uni3 {
    public Exe15Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número inteiro de até 3 digitos: ");
        int numero = sc.nextInt();

        int x = numero / 100;
        numero = numero % 100;
        int y = numero / 10;
        numero = numero % 10;
        int k = numero / 1;

        System.out.println(x + " centena(s)");
        System.out.println(y + " dezena(s)");
        System.out.println(k + " unidade(s)");


        sc.close();


    }
    
    public static void main(String[] args) {
        new Exe15Uni3();
    }
    
}
