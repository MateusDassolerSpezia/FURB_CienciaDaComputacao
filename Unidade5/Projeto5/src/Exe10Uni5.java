public class Exe10Uni5 {
    public Exe10Uni5() {

        
        for (int i = 1; i < 9999; i++) {
            for (int j = 1; j <= 9999; j++) {
                int soma = i + j;
                int somaQuadrado = soma * soma;
                String concatenacao = String.valueOf(i) + String.valueOf(j); //i + " " + j;
                if (String.valueOf(somaQuadrado).equals(concatenacao)) {
                    System.out.println(i + " + " + j + " = " + soma + " -> " + soma + "²" + " = " + somaQuadrado);
                }
            }
        }
    
    }
    public static void main(String[] args) {
        new Exe10Uni5();
    }
}
