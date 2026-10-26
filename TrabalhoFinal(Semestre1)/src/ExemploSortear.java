import java.util.Random;

public class ExemploSortear {
    public ExemploSortear() {
        Random generator = new Random();

        
        char navios[][] = new char[8][8];
        char n[] = new char[]{'N'};
        int quantidade = 0;
        
        while (quantidade < 10) {
        for(int i = 0; i < navios.length ; i++){  
            for(int k = 0; k < navios[i].length ; k++){  
             navios[i][k] = n[generator.nextInt(n.length)];
            }  

           }  
           quantidade++;
        }
 
           for(int i = 0; i < navios.length ; i++){  
           for(int k = 0; k < navios[i].length ; k++){  
            System.out.print(navios[i][k]  + " ");  
           }               
           System.out.println("");
          }  

         }  
    

    private int sortear(int inicio, int fim) {
        return (int) ((Math.random() * fim) - inicio);
    }

    public static void main(String[] args) {
        new ExemploSortear();

    }

}
