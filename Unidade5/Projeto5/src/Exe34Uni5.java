import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe34Uni5 {
    public Exe34Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        float precoDiaria = 50;
        float valorMais15Dias = 5;
        float valor15Dias = 6.50f;
        float valorMenos15Dias = 7.50f;

        float totalPago = 0;

        int hospedes = 0;

        int opcao = 0;
        do {
            System.out.println("Escolha uma opção: \n" +
            "(1) encerrar a conta de um hóspede;\n" + 
            "(2) verificar número de contas encerradas;\n" + 
            "(3) sair.");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                System.out.println("Digite o nome do hóspede");
                String nome = sc.next();
                System.out.println("Digite o número de diárias: ");
                int diarias = sc.nextInt();
                if(diarias > 15){
                    totalPago = precoDiaria + (diarias * valorMais15Dias);
                    }	
                    else if(diarias == 15){
                    totalPago = precoDiaria + (diarias * valor15Dias);
                    }
                    else{
                    totalPago = precoDiaria + (diarias * valorMenos15Dias);
                    }
                System.out.println("O total a ser pago por " + nome + " é de R$ " + df.format(totalPago));
                hospedes ++;
                    break;
                case 2:
                System.out.println(hospedes + " hóspedes deixaram o hotel");
                break;
            }
        } while (opcao != 3);

        
        sc.close();
    }
    public static void main(String[] args) {
        new Exe34Uni5();
    }
}
