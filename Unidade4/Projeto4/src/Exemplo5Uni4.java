import java.util.Scanner;

public class Exemplo5Uni4 {
    public Exemplo5Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a palavra 1: ");
        String palavra1 = sc.nextLine();

        System.out.println("Digite a palavra 2: ");
        String palavra2 = sc.nextLine();

        if (palavra1.trim().equals(palavra2)) {
            System.out.println("As palvras são iguais");
        }

        if (palavra1.trim().equalsIgnoreCase(palavra2)) {
            System.out.println("O conteúdo das palavras é igual");
        }

        if (palavra1.trim().toUpperCase().equals(palavra2.toUpperCase())) {
            System.out.println("Conteúdo igual " + palavra1.toUpperCase());
        }

        char letra = palavra1.trim().toUpperCase().charAt(0);
        switch (letra) {
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                System.out.println("É uma vogal");
                break;
            default:
                System.out.println("Não é uma vogal");
        }

        System.out.println("A palavra possui " + palavra1.length() + " caracteres");

        if (palavra1.indexOf("-") > 0) {
            String pedacoAteTraco = palavra1.substring(0, palavra1.indexOf("-"));
            String pedacoAteFinal = palavra1.substring(palavra1.indexOf("-") + 1, palavra1.length());

            System.out.println(pedacoAteTraco);
            System.out.println(pedacoAteFinal);
        }  


        sc.close();
    }
    public static void main(String[] args) {
        new Exemplo5Uni4();
    }
}
