import java.util.Scanner;

public class ExemploVetor {
    public ExemploVetor() {

        // int numeros[] = new int[5]; \\

        /*numeros[0] = 5;
        numeros[1] = 7;
        numeros[2] = 2;      //EX1
        numeros[3] = 10;
        numeros[4] = 5; 
        */

        Scanner sc = new Scanner(System.in);

        // System.out.println("Digite o número 1: ");   //EX2
        // numeros[0] = sc.nextInt();

        /*
         * for (int i = 0; i < 5; i++) {
         * System.out.print("Digite o número " + (i+1) + ": ");   //Ex3
         * numeros[i] = sc.nextInt();
         * }
         */

         System.out.print("Digite a quantidade de elementos: ");
         int tamanho = sc.nextInt();
 
         int numeros[] = new int[tamanho];
         for (int i = 0; i < numeros.length; ) {
            // if (i % 2 == 0) {
            //     System.out.print("Digite o número " + (i + 1) + ": ");  //EX4
            //     numeros[i] = sc.nextInt();
            // }
 
            System.out.print("Digite o número " + (i + 1) + ": ");
            int informado = sc.nextInt();
            if (informado % 2 == 0) {
                 numeros[i] = informado;  
                 i++; 
            }
 
         }
 
         sc.close();
         System.out.println(numeros[0]);
         System.out.println(numeros[1] + " " + numeros[2]);
 
         System.out.println("Impressão do VETOR:");
         for (int i = 0; i < numeros.length; i++) {
             System.out.println(numeros[i]);
         }
 

        sc.close();
    }
    public static void main(String[] args) {
        new ExemploVetor();
    }
}
