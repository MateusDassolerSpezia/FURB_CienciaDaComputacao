import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe25Uni4 {
    public Exe25Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Escolha uma opção:");
        System.out.println("1 - Soma de dois números");
        System.out.println("2 - Diferença entre dois números");
        System.out.println("3 - Produto entre dois números");
        System.out.println("4 - Divisão entre dois números (o denominador não pode ser zero)");

        int opçao = sc.nextInt();

        switch (opçao) {
            case 1: 
            System.out.println("Digite dois números");
            float num1 = sc.nextFloat();
            float num2 = sc.nextFloat();
            System.out.println("A soma desses números é " + df.format(num1 + num2));
                break;
            case 2: 
            System.out.println("Digite dois numeros");
            float num3 = sc.nextFloat();
            float num4 = sc.nextFloat();
            System.out.println("A diferença desses números é " + df.format(num3 - num4));
                break;
            case 3: 
            System.out.println("Digite dois numeros");
            float num5 = sc.nextFloat();
            float num6 = sc.nextFloat();
            System.out.println("O produto desses números é " + df.format(num5 * num6));
                break;
            case 4: 
            System.out.println("Digite dois numeros:");
            float num7 = sc.nextFloat();
            float num8 = sc.nextFloat();
            System.out.println("A divisão desses números é " + df.format(num7 / num8));
                break;
            default: System.out.println("Valor inválido");
       }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe25Uni4();
    }
}
