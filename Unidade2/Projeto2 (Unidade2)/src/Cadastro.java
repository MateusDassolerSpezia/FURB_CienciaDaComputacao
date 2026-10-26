import java.util.Scanner;

public class Cadastro {
    public Cadastro() {

        System.out.println("Bom dia");

        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite o nome: ");
        String nome = entrada.next(); //nextLine()
        //String: Para texto

        System.out.print("Digite o ano de nascimento: ");
        int anoNascimento = entrada.nextInt();
        //int: Números inteiros

        int idade = 2024 - anoNascimento;

        System.out.println("Ola " + nome + ", sua idade é " + idade);


        entrada.close();
    }
        
        public static void main(String[] args) {
            new Cadastro();

    }
}
