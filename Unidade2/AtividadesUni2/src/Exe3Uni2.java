import java.util.Scanner;

public class Exe3Uni2 {
    public Exe3Uni2() {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Raio (em metros): ");
        float raio = leitor.nextFloat();
        float PI = 3.14159f;
        //double PI = Math.PI;

        float area = (raio * raio) * PI;
        //Math.pow(base, expoente) = raio²
        //double area = Math.pow(raio, 2) * PI;

        System.out.print("Área = " + area + "m²");

        leitor.close();
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        new Exe3Uni2();
    }
}
