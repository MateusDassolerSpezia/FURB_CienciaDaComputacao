import java.util.Scanner;

public class Exemplo2Uni4 {
    public Exemplo2Uni4() { 
        Scanner sc = new Scanner(System.in);

        boolean testeDengue = sc.nextBoolean();

        //Se o teste for verdadeiro é dengue
        if (testeDengue) { //if (testeDengue == true)
            System.out.println("É dengue");
        }

        if (!testeDengue) { //testeDengue == false
            System.out.println("Não é dengue");
        }


        sc.close();
    }
    public static void main(String[] args) {
        new Exemplo2Uni4();
    }
}
