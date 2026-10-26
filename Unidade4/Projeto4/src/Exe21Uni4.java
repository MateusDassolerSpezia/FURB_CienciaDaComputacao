import java.util.Scanner;

public class Exe21Uni4 {
    public Exe21Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite sua massa (em Kg): ");
        float massa = sc.nextFloat();

        System.out.println("Digite sua altura (em m): ");
        float altura = sc.nextFloat();

        float imc = massa / (altura * altura);
        System.out.println("IMC = " + imc);

        if (imc < 18.5) {
            System.out.println("Magreza");
        } else if (imc >= 18.5 && imc < 25) {
            System.out.println("Saudável");
        } else if (imc >= 25 && imc < 30) {
            System.out.println("Sobrepeso");
        } else if (imc >= 30 && imc < 35) {
            System.out.println("Obesidade Grau I");
        } else if (imc >= 35 && imc < 40) {
            System.out.println("Obesidade Grau II (severa)");
        } else if (imc >= 40) {
            System.out.println("Obesidade Grau III (mórbida)");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exe21Uni4();
    }
}
