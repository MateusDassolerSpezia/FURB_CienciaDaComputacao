import java.util.Scanner;

public class Exe7Uni4 {
    public Exe7Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com o peso da carta: ");
        int peso = sc.nextInt();
        
        float valorPagar = 0;

        if (peso <= 50) {
            valorPagar = 0.45f;
        }
        if (peso > 50) {
            int pesoExcedido = peso - 50;
            float qtAdicional = (pesoExcedido / 20) + 1;
            valorPagar = 0.45f + 0.45f * qtAdicional;
        }

        System.out.println("Custo do selo: " + valorPagar);


        sc.close();

    }
    public static void main(String[] args) {
        new Exe7Uni4();
    }
    
}
