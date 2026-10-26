import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe1Uni3 {
    public Exe1Uni3() {

        Scanner medida = new Scanner(System.in);

        System.out.print("Digite a largura (m): ");
        float largura = medida.nextFloat();

        System.out.print("Digite o comprimento (m) ");
        float comprimento = medida.nextFloat();

        float area = largura * comprimento;
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.print("Área: " + df.format(area) + "m²");

    
    
    
    medida.close();
    }


    public static void main(String[] args) {
        new Exe1Uni3();
    }
}
