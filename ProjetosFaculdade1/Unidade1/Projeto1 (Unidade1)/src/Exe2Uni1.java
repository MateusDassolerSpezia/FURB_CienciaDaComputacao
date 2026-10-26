import java.util.Scanner;

public class Exe2Uni1 {
    
  public Exe2Uni1(){
  //Construtor da classe: possui o MESMO NOME da classe

    Scanner teclado = new Scanner(System.in);

    System.out.print("Digite o comprimento em metros: ");
    float comprimento = teclado.nextFloat();

    System.out.print("Digite a largura em metros: ");
    float largura = teclado.nextFloat();

    float area = (comprimento * largura);
    System.out.print("Área =" + area + "m2");

    teclado.close();    
  }
    public static void main(String[] args) {
        new Exe2Uni1(); //Chama o construtor da classe
    }
}
