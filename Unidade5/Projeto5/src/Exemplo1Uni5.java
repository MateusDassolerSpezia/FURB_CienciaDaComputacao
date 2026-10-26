import java.util.Scanner;

public class Exemplo1Uni5 {
    public Exemplo1Uni5() {

        //FOR
        //for (inicialização; condição; finalização)
        for (int contador = 0; contador < 5; contador++) {
            System.out.println(contador);
        }

        //WHILE
        int contador = 0;
        while (contador < 5) {
            System.out.println("While " + contador);
            contador++;
        }

        //DO-WHILE
        int cont2 = 0;
        do {
            System.out.println("Do-While " + cont2);
            cont2++;
        } while (cont2 < 5);

        //

        Scanner sc = new Scanner(System.in);

        String nome = "";
        do {
            System.out.println("Digite o nome: ");
            nome = sc.nextLine();
        } while (nome.trim().equals(""));
        System.out.println("Nome = " + nome);

        //

        System.out.print("Digite o nome: ");
        String nome2 = sc.nextLine();
        while (nome2.trim().equals("")) {
            System.out.print("Digite o nome: ");
            nome2 = sc.nextLine();
        }
        System.out.println("Nome = " + nome2);

       



        sc.close();
    }
    public static void main(String[] args) {
        new Exemplo1Uni5();
    }
}
