import java.util.Scanner;

public class Exe1Uni4 {
    public Exe1Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com as horas trabalhadas no mês: ");
        int horasMes = sc.nextInt();

        System.out.println("Entre com o valor pago por hora: ");
        float horasValor = sc.nextFloat();

        float salarioTotal = horasMes * horasValor;

        if (horasMes > 160) {
            float salarioExtra = (horasMes - 160) * (horasValor / 2);
            salarioTotal += salarioExtra;
            //salarioTotal = salarioTotal + salarioExtra;
            
        }

        System.out.println("O salário total é: " + salarioTotal);

        
           
         sc.close();
    }
    public static void main(String[] args) {
        new Exe1Uni4();
    }
}
