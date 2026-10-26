import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe10Uni3 {
    public Exe10Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Diigte o cateto1: ");
        float cateto1 = sc.nextFloat();

        System.out.print("Digite o cateto2: ");
        float cateto2 = sc.nextFloat();

        double hipotenusa = Math.sqrt ((cateto1 * cateto1) + (cateto2 * cateto2));
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Hipotenusa = " + df.format(hipotenusa));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe10Uni3();
    }
    
}
