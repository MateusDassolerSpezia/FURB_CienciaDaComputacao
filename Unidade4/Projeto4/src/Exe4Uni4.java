import java.util.Scanner;

public class Exe4Uni4 {
    public Exe4Uni4() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com um número maior do que 0: ");
        float numero = sc.nextFloat();

        //Forma1:
        if(numero % 1 == 0) {
            System.out.println("Não tem casas decimais");
        } 
        else {
            System.out.println("Tem casas decimais");
        }

        //Forma2:
        int numeroInteiro = (int)(numero);
        if (numero == numeroInteiro) {
            System.out.println("Não tem casas decimais");
        } 
        else {
            System.out.println("tem casas decimais");
        } 

        //Forma3: converter um número para String
        String numeroString = String.valueOf(numero); //converte um número para texto
        System.out.println("Número convertido: " + numeroString);
        int indiceInicial = numeroString.indexOf("."); //retorna o indice do caractere
        int indiceFinal = numeroString.length(); //tamanho da string
        String decimal = numeroString.substring(indiceInicial + 1, indiceFinal);
        System.out.println("Parte decimal: " + decimal);

        int parteDecimal = Integer.parseInt(decimal);
        if (parteDecimal == 0) {
            System.out.println("Não tem casas decimais");
        }
        else{
            System.out.println("Tem casas decimais");
        }

        if (decimal.trim().equals("0")) {
            System.out.println("Não tem casas decimais");
        }
        else{
            System.out.println("Tem casas decimais");
        }

        sc.close();

        }
        
        

       
    
    public static void main(String[] args) {
        new Exe4Uni4();
    }
}
