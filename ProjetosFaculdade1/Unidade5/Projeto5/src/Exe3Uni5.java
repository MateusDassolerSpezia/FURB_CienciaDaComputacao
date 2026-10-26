public class Exe3Uni5 {
    public Exe3Uni5() {
        
        float soma = 0;

        for (float denominador = 1; denominador <= 100; denominador++) {
            soma += (1 / denominador);
        }

        System.out.println("Soma: " + soma);

    }
    public static void main(String[] args) {
        new Exe3Uni5();
    }
}
