public class Exe4Uni5 {
    public Exe4Uni5() {

        float S = 0;
        float numerador = 3;
        float denominador = 2;
        float progressaoDenominador = 4;

        for (int contador = 0; contador < 20; contador++) {
            S += (numerador / denominador);
            numerador += 2;
            denominador += progressaoDenominador;
            progressaoDenominador += 2;
        }

        System.out.println("Valor de S: " + S);
    }
    public static void main(String[] args) {
        new Exe4Uni5();
    }
}
