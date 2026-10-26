import java.util.Scanner;

public class Exemplo1Uni4 {
    public Exemplo1Uni4() {
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();

        //O operador resto da divisão (%) só pode ser utilizado com 
        //o tipo de dado Inteiro (int)

        int resto = numero % 2;

        //se não tem resto é par
        if (resto == 0) {
        //if (CONDIÇÃO) {
            //comandos que funcionarão SOMENTE se for verdadeiro
            System.out.println("Número é PAR");
        }

        //se tem resto é ímpar
        if (resto != 0) { //<> => !=
            System.out.println("Número é IMPAR");
        }

        sc.close();
    
    }
    public static void main(String[] args) {
        new Exemplo1Uni4();
    }
}