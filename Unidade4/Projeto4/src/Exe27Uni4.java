import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe27Uni4 {
    public Exe27Uni4() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite o horário de chegada: ");
        System.out.print("Hora(s): ");
        int horasChegada = sc.nextInt();
        System.out.print("Minuto(s): ");
        int minutosChegada = sc.nextInt();

        System.out.println("Digite o horário de saída: ");
        System.out.print("Hora(s): ");
        int horasSaida = sc.nextInt();
        System.out.print("Minuto(s): ");
        int minutosSaida = sc.nextInt();

        int horasEstacionado = (horasSaida - horasChegada);
        int minutosEstacionado = (minutosSaida - minutosChegada);
        System.out.println("tempo estacionado: " + horasEstacionado + "h" + minutosEstacionado);

        float valorPagar = 0;

        if (horasChegada >= 0 && horasChegada <= 23) {
            if (horasSaida >= 0 && horasSaida <= 23) {
                if (horasSaida > horasChegada ||
                        (horasSaida == horasChegada && minutosSaida > minutosChegada)) {
                    if (minutosChegada >= 0 && minutosChegada <= 59) {
                        if (minutosSaida >= 0 && minutosSaida <= 59) {

        if (minutosEstacionado >= 30) {
            horasEstacionado += 1;
        } 

        /*switch (horasEstacionado) {
            case 0: 
            case 1:
                valorPagar = 5;
                break;
            case 2:
                valorPagar = 10;
                break;
            case 3:
                valorPagar = 17.5f;
                break;
            case 4: 
                valorPagar = 25;
                break;
            default: //>=5
                int horasMais = horasEstacionado - 4;
                valorPagar = 25 + (10 * horasMais);
        }/* */

        switch (horasEstacionado) {
            default: //>=5
                int horasMais = horasEstacionado - 4;
                valorPagar = 25 + (10 * horasMais); 
            case 4:
                valorPagar += 7.5f;
            case 3:
                valorPagar += 7.5f;
            case 2:
                valorPagar += 5;
            case 1: 
            case 0:
                valorPagar += 5;
        }

        System.out.println("Preço a pagar: R$ " + df.format(valorPagar));

    } else {
        System.out.println("Minuto de saída inválido: deve ser entre 0 e 59");
    }
} else {
    System.out.println("Minuto de entrada inválido: deve ser entre 0 e 59");
}
} else {
System.out.println("Hora de saída deve ser depois da hora de entrada");
}
} else {
System.out.println("Hora de saída inválida: deve ser entre 0 e 23");
}
} else {
System.out.println("Hora de entrada inválida: deve ser entre 0 e 23");
}


        sc.close();
    }
    public static void main(String[] args) {
        new Exe27Uni4();
    }
}
