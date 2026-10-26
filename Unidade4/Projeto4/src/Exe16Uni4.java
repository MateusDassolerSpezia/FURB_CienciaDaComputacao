import java.util.Scanner;

public class Exe16Uni4 {
    public Exe16Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a idade dos 2 homens e das 2 mulheres respectivamente: ");
        int homem1 = sc.nextInt();
        int homem2 = sc.nextInt();
        int mulher1 = sc.nextInt();
        int mulher2 = sc.nextInt();

        if (homem1 > homem2 && mulher1 < mulher2) {
            int soma = homem1 + mulher1;
            System.out.println("Soma das idades do homem mais velho com a mulher mais nova = " + soma);
            int produto = homem2 * mulher2;
            System.out.println("Produto das idades do homem mais novo com a mulher mais velha = " + produto);
        } else if (homem1 > homem2 && mulher2 < mulher1) {
            int soma = homem1 + mulher2;
            System.out.println("Soma das idades do homem mais velho com a mulher mais nova = " + soma);
            int produto = homem2 * mulher1;
            System.out.println("Produto das idades do homem mais novo com a mulher mais velha = " + produto);
        } else if (homem2 > homem1 && mulher2 < mulher1) {
            int soma = homem2 + mulher2;
            System.out.println("Soma das idades do homem mais velho com a mulher mais nova = " + soma);
            int produto = homem1 * mulher1;
            System.out.println("Produto das idades do homem mais novo com a mulher mais velha = " + produto);
        } else if (homem2 > homem1 && mulher1 < mulher2) {
            int soma = homem2 + mulher1;
            System.out.println("Soma das idades do homem mais velho com a mulher mais nova = " + soma);
            int produto = homem1 + mulher2;
            System.out.println("Produto das idades do homem mais novo com a mulher mais velha = " + produto);
        }

        
        sc.close();
    }
    public static void main(String[] args) {
        new Exe16Uni4();
    }
}
