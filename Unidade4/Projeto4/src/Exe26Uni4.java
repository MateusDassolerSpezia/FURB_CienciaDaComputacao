import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe26Uni4 {
    public Exe26Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.000");

        System.out.println("Escolha uma opção");
        System.out.println("T: calcular a área de um triângulo de base b e altura h");
        System.out.println("Q: calcular a área de um quadrado de lado l");
        System.out.println("R: calcular a área de um retângulo de base b e altura h");
        System.out.println("C: calcular a área de um circulo de raio r");

        char opçao = sc.next().toUpperCase().charAt(0);

        switch (opçao) {
            case 'T': 
            System.out.println("Digite a base e a altura do triângulo: ");
            float base = sc.nextFloat();
            float altura = sc.nextFloat();
            System.out.println("Área do triangulo =" + df.format(base * altura/2));
                break;
            case 'Q':
            System.out.println("Digite o lado: ");
            float lado = sc.nextFloat();
            System.out.println("Área do quadrado =" + df.format(lado * lado));
                break;
            case 'R': 
            System.out.println("Digite a base a a altura do retângulo: ");
            float b = sc.nextFloat();
            float h = sc.nextFloat();
            System.out.println("Área do retângulo =" + df.format(b * h));
                break;
            case 'C':
            System.out.println("Digite o raio: ");
            float r = sc.nextFloat();
            System.out.println("Área do circulo =" + df.format(Math.PI * Math.pow(r, 2)));
                break;
            default: System.out.println("Valor inválido");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe26Uni4();
    }
}
