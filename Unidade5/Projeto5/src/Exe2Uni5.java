public class Exe2Uni5 {
    public Exe2Uni5() {
      
        int somaPar = 0;
        int somaImpar = 0;

        for (int num = 1; num <= 100; num ++) {
           if (num % 2 == 0) {
                somaPar += num;
            } else {
                somaImpar += num;   
            }
        }

        System.out.println("Soma dos pares: " + somaPar);
        System.out.println("Soma dos ímpares: " + somaImpar);

        
    }
    public static void main(String[] args) {
        new Exe2Uni5();
    }
}
