import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe11Uni3 {
    public Exe11Uni3() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a temperatura em °C: ");
        float tempC = sc.nextInt();

        float tempF = 1.8f * tempC + 32;
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.print("Temperatura em °F: " + df.format(tempF));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe11Uni3();
    }
    
}
