import java.util.Scanner;

public class Exemplo3Uni4 {
    public Exemplo3Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um sinal +, -, /, *: ");
        char sinal = sc.nextLine().charAt(0);

        System.out.println("Número 1: ");
        int n1 = sc.nextInt();

        System.out.println("Número 2: ");
        int n2 = sc.nextInt();

        if (sinal == '/' && n2 != 0) {
            int resultado = n1 / n2;
            System.out.println(resultado);
        }

        //O código seguinte é equivalente ao uso do &&:
        if(sinal == '/') {
        } if (n2 != 0){
                int resultado = n1 / n2;
                System.out.println(resultado);
            }
        

        sc.close();
    }
    public static void main(String[] args) {
        new Exemplo3Uni4();
    }
    
}
