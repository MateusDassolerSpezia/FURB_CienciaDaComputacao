import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe9Uni3 {
    public Exe9Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o raio da lata (em metros): ");
        float raioLata = sc.nextFloat();

        System.out.print("Digite a altura da lata (em metros): ");
        float alturaLata = sc.nextFloat();

        float PI = 3.14f;

        float volumeLata = PI * (raioLata * raioLata) * alturaLata;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Volume da lata: " + df.format(volumeLata));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe9Uni3();
    }
    
}
