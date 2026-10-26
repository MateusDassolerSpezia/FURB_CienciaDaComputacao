import java.util.Scanner;

public class Exe14Uri {
    public Exe14Uri() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor do troco:");
        float valor = sc.nextFloat();
        int valorI = (int) (valor * 100);
        
        int valorN = valorI / 100;
        int valorM = valorI % 100;

        int notas100 = valorN / 100;
        valorN = valorN % 100;
        int notas50 = valorN / 50;
        valorN = valorN % 50;
        int notas20 =  valorN / 20;
        valorN = valorN % 20;
        int notas10 = valorN / 10;
        valorN = valorN % 10;
        int notas5 = valorN / 5;
        valorN = valorN % 5;
        int notas2 = valorN / 2;
        valorN = valorN % 2;
        int moedas1 = valorN;

        int moedas050 = valorM / 50;
        valorM = valorM % 50;
        int moedas025 = valorM / 25;
        valorM = valorM % 25;
        int moedas010 = valorM / 10;
        valorM = valorM % 10;
        int moedas005 = valorM / 5;
        valorM = valorM % 5;
        int moedas001 = valorM / 1;

        System.out.println("NOTAS:");
        System.out.println(notas100 + " nota(s) de R$ 100,00");
        System.out.println(notas50 + " nota(s) de R$ 50,00");
        System.out.println(notas20 + " nota(s) de R$ 20,00");
        System.out.println(notas10 + " nota(s) de R$ 10,00");
        System.out.println(notas5 + " nota(s) de R$ 5,00");
        System.out.println(notas2 + " nota(s) de R$ 2,00");

        System.out.println("MOEDAS:");
        System.out.println(moedas1 + " moeda(s) de R$ 1,00");
        System.out.println(moedas050 + " moeda(s) de R$ 0,50");
        System.out.println(moedas025 + " moeda(s) de R$ 0,25");
        System.out.println(moedas010 + " moeda(s) de R$ 0,10");
        System.out.println(moedas005 + " moeda(s) de R$ 0,05");
        System.out.println(moedas001 + " moeda(s) de R$ 0,01");
        
        
        sc.close();

    }
    public static void main(String[] args) {
        new Exe14Uri();
    }
}
