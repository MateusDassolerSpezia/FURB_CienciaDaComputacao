import java.util.Scanner;

public class Exe31Uni5 {
    public Exe31Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um número inteiro positivo:");
        int numero = sc.nextInt();

        int decompositor = 2;

        System.out.println("Decompositores do número: \nNúmero | Decomposição");
        while (numero != 1) {
            if (numero % decompositor == 0) {
            System.out.println(numero + " | " + decompositor);
		    numero = numero / decompositor;
	    } else{
		decompositor += 1;
        }
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe31Uni5();
    }
}
