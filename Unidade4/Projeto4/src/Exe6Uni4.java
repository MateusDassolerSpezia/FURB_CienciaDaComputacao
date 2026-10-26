import java.util.Scanner;

public class Exe6Uni4 {
    public Exe6Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite M para masculino, F para feminino ou I para não informado: ");
        String caractere = sc.nextLine();

        caractere = caractere.toUpperCase();
        //toUpperCase é um método da String que converte TODAS as letras para maiúsculo

        char letra = caractere.charAt(0);
        //charAt é um método da String que pega somente 1 caractere conforme o índice e converte para char
        
        //char exemplo = sc.nextLine().toUpperCase().charAt(0);

        if (letra == 'M') {
            System.out.println("Masculino");
        }
        else if (letra == 'F') {
            System.out.println("Feminimo");
        }
        else if (letra == 'I') {
            System.out.println("Não informado");
        }
        else {
            System.out.println("Entrada inválida");
        }

        //Execução com operadores lógicos:
        if (letra == 'M' || letra == 'F' || letra == 'I') {
            System.out.println("Valor válido");
        }
        else {
            System.out.println("Valor inválido");
        }




        sc.close();
    }
    public static void main(String[] args) {
        new Exe6Uni4();
    }
}
