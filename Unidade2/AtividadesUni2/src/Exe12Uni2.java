import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe12Uni2 {
    public Exe12Uni2() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.0000");

        System.out.println("Digite os valores dos eixos 'x1' e 'y1' do ponto p1");
        float x1 = sc.nextFloat();
        float y1 = sc.nextFloat();

        System.out.println("Digite os valores dos eixos 'x2' e 'y2' do ponto p2");
        float x2 = sc.nextFloat();
        float y2 = sc.nextFloat();

        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        System.out.println("A distancia entre os porntos p1 e p2 é de: " + df.format(distancia));


        sc.close();
    }
    public static void main(String[] args) {
        new Exe12Uni2();
    }
}
