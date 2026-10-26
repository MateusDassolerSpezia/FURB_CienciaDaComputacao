import java.util.Scanner;

public class Exe12Uri {
    public Exe12Uri() {
        Scanner sc = new Scanner(System.in);

        int valor = sc.nextInt();

        int notas100 = valor / 100;
        valor = valor % 100;
        int notas50 = valor / 50;
        valor = valor % 50;
        int notas20 = valor / 20;
        valor = valor % 20;
        int notas10 = valor / 10;
        valor = valor % 10;
        int notas5 = valor / 5;
        valor = valor % 5;
        int notas2 = valor / 2;
        valor = valor % 2;
        int notas1 = valor / 1;

        System.out.println(valor);
        System.out.println(notas100 + " nota(s) de R$ 100,00");
        System.out.println(notas50 + " nota(s) de R$ 50,00");
        System.out.println(notas20 + " nota(s) de R$ 20,00");
        System.out.println(notas10 + " nota(s) de R$ 10,00");
        System.out.println(notas5 + " nota(s) de R$ 5,00");
        System.out.println(notas2 + " nota(s) de R$ 2,00");
        System.out.println(notas1 + " nota(s) de R$ 1,00");



        sc.close();
    }
    public static void main(String[] args) {
        new Exe12Uri();
    }
}
