import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe3Uni3 {
    public Exe3Uni3(){

        Scanner sn = new Scanner(System.in);

        System.out.print("Digite o valor do litro: ");
        float valorLitro = sn.nextFloat();

        System.out.print("Digite o vaolor do pagamento: ");
        float valorPagamento = sn.nextFloat();

        float quantidadeLitros = valorPagamento/valorLitro;
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.print("Litros no tanque: " + df.format(quantidadeLitros));


        sn.close();

    }

    public static void main(String[] args) {
        new Exe3Uni3();
    }
    
}
