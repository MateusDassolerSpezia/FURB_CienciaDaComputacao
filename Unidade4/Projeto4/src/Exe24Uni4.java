import java.util.Scanner;

public class Exe24Uni4 {
    public Exe24Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com 3 valores: ");
        float valor1 = sc.nextFloat();
        float valor2 = sc.nextFloat();
        float valor3 = sc.nextFloat();

        float maior = 0;
        float menor = 0;
        float meio = 0;

        System.out.println("Escolha uma opção:");
        System.out.println("1 - escreva os 3 valores em ordem crescente");
        System.out.println("2 - escreva os 3 valores em ordem decrescente");
        System.out.println("3 - escreva os 3 valores de forma que o maior valor fique no meio");
        int opçao = sc.nextInt();

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

        switch (opçao) {
            case 1:
                System.out.println("Ordem crescente:");
                System.out.println(menor + " " + meio + " " + maior);
                    break;
            case 2:
                System.out.println("Ordem decrescente:");
                System.out.println(maior + " " + meio + " " + menor);
                    break;   
            case 3:
                System.out.println("Maior no meio:");
                System.out.println(menor + " " + maior + " " + meio);
                    break;
            default: System.out.println("Opção invalida");
        }

        
        sc.close();
        
    }
    public static void main(String[] args) {
        new Exe24Uni4();
    }
}
