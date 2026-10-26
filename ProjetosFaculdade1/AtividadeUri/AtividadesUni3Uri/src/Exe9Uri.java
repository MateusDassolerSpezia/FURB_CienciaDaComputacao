import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe9Uri {
    public Exe9Uri() {
        Scanner sc = new Scanner(System.in);

        Double A = sc.nextDouble();
        Double B = sc.nextDouble();
        Double C = sc.nextDouble();

        final Double PI = 3.14159;

        double areaTrianguloRet = (A * C) / 2;
        double areaCirculo = PI * (C * C);
        double areaTrapezio = (A + B) * C / 2;
        double areaQuadrado = B * B;
        double areaRetangulo = A * B;

        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println("TRIANGULO: " + df.format(areaTrianguloRet));
        System.out.println("CIRCULO: " + df.format(areaCirculo));
        System.out.println("TRAPEZIO: " + df.format(areaTrapezio));
        System.out.println("QUADRADO: " + df.format(areaQuadrado));
        System.out.println("RETANGULO: " + df.format(areaRetangulo));

        sc.close();
    }
    public static void main(String[] args) {
        new Exe9Uri();
    }
    
}
