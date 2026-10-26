import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe11Uni2 {
    public Exe11Uni2() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Escolha uma opção:");
        System.out.println("a) a área do triângulo retângulo que tem A por base e C por altura.");
        System.out.println("b) a área do círculo de raio C. (pi = 3.14159).");
        System.out.println("c) a área do trapézio que tem A e B por bases e C por altura.");
        System.out.println("d) a área do quadrado que tem lado B.");
        System.out.println("e) a área do retângulo que tem lados A e B.");

        char opcao = sc.nextLine().charAt(0);

        switch (opcao) {
            case 'a': 
            System.out.println("Digite os valores de A e C:");
            float A = sc.nextFloat();
            float C = sc.nextFloat();
            float areaTriangulo = (A * C) / 2;
            System.out.println("Área do triângulo = " + df.format(areaTriangulo));
                break;
            case 'b':
            System.out.println("Digite o valor de C:");
            C = sc.nextFloat();
            double areaCirculo = 3.14159f * Math.pow(C, 2);
            System.out.println("Área do circulo = " + df.format(areaCirculo));
                break;
            case 'c':
            System.out.println("Digite os valores de A, B e C:");
            A = sc.nextFloat();
            float B = sc.nextFloat();
            C = sc.nextFloat();
            float areaTrapezio = ((A + B) * C) / 2;
            System.out.println("Área do trapézio = " + df.format(areaTrapezio));
                break;
            case 'd':
            System.out.println("Digite o valor de B:");
            B = sc.nextFloat();
            float areaQuadrado = B * B;
            System.out.println("Área do quadrado = " + df.format(areaQuadrado));
                break;
            case 'e':
            System.out.println("Digite os valores de A e B:");
            A = sc.nextFloat();
            B = sc.nextFloat();
            float areaRetangulo = A * B;
            System.out.println("Área do retângulo = " + df.format(areaRetangulo));
                break;
            default: System.out.println("Valor inválido");
        }
        
       
        
        
        

        sc.close();
    }
    public static void main(String[] args) {
        new Exe11Uni2();
    }
}
