import java.util.Scanner;

public class Exe12Uni4 {
    public Exe12Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o comprimento do lado1: ");
        float lado1 = sc.nextFloat();

        System.out.println("Entre com o comprimento do lado2: ");
        float lado2 = sc.nextFloat();

        System.out.println("Entre com o comprimento do lado3: ");
        float lado3 = sc.nextFloat();

        if (lado1 < (lado2 + lado3) && lado2 < (lado1 + lado3) && lado3 < (lado1 + lado2)) {
        } else {
            System.out.println("não formam um triângulo");
        } 
        if (lado1 == lado2 && lado1 == lado3) {
            System.out.println("é equilatero");
        } else if (lado1 == lado2 || lado2 ==lado3 || lado1 == lado3) {
            System.out.println("é isóceles");
        } else {
            System.out.println("é escaleno");
        }
    
        
        sc.close();
    }
    public static void main(String[] args) {
        new Exe12Uni4();
    }
}
