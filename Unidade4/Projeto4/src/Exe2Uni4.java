import java.util.Scanner;

public class Exe2Uni4 {
    
    public Exe2Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com um valor inteiro maior do que 0: ");
        int valor = sc.nextInt();

        if(valor % 2 == 0) {
            System.out.println("Número é par");
        }
        if(valor % 2 != 0) {
            System.out.println("Número é impar");
        }
    
        sc.close();
    }

    public static void main(String[] args) {
        new Exe2Uni4();
    }
    
}



