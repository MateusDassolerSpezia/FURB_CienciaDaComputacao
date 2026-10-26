import java.util.Scanner;

public class Exe24Uni5 {
    public Exe24Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o peso limite diário (em Kg):");
        float pesoLimite = sc.nextFloat();

        System.out.println("Digite o peso do peixe (em g):");
        float pesoPeixe = sc.nextFloat();

        System.out.println("Deseja informar o peso de mais um peixe: s (SIM) / n (NÃO)?");
        char opcao = sc.next().toUpperCase().charAt(0);

        float pesoTotal = pesoPeixe / 1000;

        while (pesoTotal < pesoLimite && opcao == 'S') {
            System.out.println("Digite o peso do peixe (em g):");
            pesoPeixe = sc.nextFloat();
            pesoTotal += pesoPeixe / 1000;
            System.out.println("Peso total obtido até o momento: " + pesoTotal + "Kg");
            System.out.println("Deseja informar o peso de mais um peixe: s (SIM) / n (NÃO)?");
            opcao = sc.next().toUpperCase().charAt(0);
        }

        System.out.println("peso obtido: " + pesoTotal + "Kg");
        if (pesoTotal > pesoLimite) {
        System.out.println("Limite excedido");
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Exe24Uni5();
    }
}
