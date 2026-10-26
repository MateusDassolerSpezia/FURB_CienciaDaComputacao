import java.util.Scanner;

public class Exe32Uni5 {
    public Exe32Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Em qual dia da semana começa o mês?(considere domingo = 1 e sábado = 7)");
        int dia = sc.nextInt();
        System.out.println("Quantos dias tem o mês?");
        int dias = sc.nextInt();
        System.out.println("D S T Q Q S S");
        System.out.println("--------------");

        int i = 1;
        while(i < dia){
            System.out.print("  ");
            i++;
        }
        i = 1;
        while(i <= dias){
            if (i < 10) {
                System.out.print(" " + i);
              } else {
                System.out.print(" " + i);
              }
              if (dia == 7) {
                dia = 1;
                System.out.print("\n");
              } else {
                dia++;
            }
            i++;
        }
    

          sc.close();

    }
    
    public static void main(String[] args) {
        new Exe32Uni5();
    }
}
