import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe10Uri {
    public Exe10Uri() {
        Scanner sc = new Scanner(System.in);

        double x1 = sc.nextDouble(); double y1 = sc.nextDouble();

        double x2 = sc.nextDouble(); double y2 = sc.nextDouble();

        double distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y1 - y2) * (y1 - y2));

        DecimalFormat df = new DecimalFormat("0.0000");
        System.out.println(df.format(distancia));

        sc.close();
    
    }
    public static void main(String[] args) {
        new Exe10Uri();
    }
    
}
