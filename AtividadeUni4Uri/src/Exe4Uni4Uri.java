import java.util.Scanner;

public class Exe4Uni4Uri {
    public Exe4Uni4Uri() {
        Scanner sc = new Scanner(System.in);

        int valor1 = sc.nextInt();
        int valor2 = sc.nextInt();
        int valor3 = sc.nextInt();

        int maior, menor, meio; 

        if (valor1 > valor2 && valor1 > valor3) {
            maior = valor1;
        } else if (valor2 > valor3) {
            maior = valor2;
        } else {
            maior = valor3;
        } 

        if (valor1 < valor2 && valor1 < valor3) {
            menor = valor1;
        } else if (valor2 < valor3) {
            menor = valor2;
        } else {
            menor = valor3;
        }     

        if (valor1 != maior && valor1 != menor) {
            meio = valor1;
        } else if (valor2 != maior && valor2 != menor) {
            meio = valor2;
        } else {
            meio = valor3;
        }

        System.out.println(menor + "\n" + meio + "\n" + maior);
        System.out.println("\n" + valor1 + "\n" + valor2 + "\n" + valor3);

        sc.close();
    }
    public static void main(String[] args) {
        new Exe4Uni4Uri();
    }
}
