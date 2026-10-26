import java.util.Scanner;

public class Exe5Uni5 {
    public Exe5Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a quantidade de termos:");
        float n = sc.nextFloat();
        
        if (n > 2) {
        float numeros = 8;
        float progressao1 = 2;
        float progressao2 = 6;
        

        for (int cont = 0; cont < n; cont++) {
            if (cont % 2 == 0) {
                System.out.println(numeros);
                numeros += progressao1;
            } else {
                System.out.println(numeros);
                numeros += progressao2;
                progressao2 = (progressao2 + 1) * 2;
        }
        }
        }

        sc.close();

    }
    public static void main(String[] args) {
        new Exe5Uni5();
    }
}
