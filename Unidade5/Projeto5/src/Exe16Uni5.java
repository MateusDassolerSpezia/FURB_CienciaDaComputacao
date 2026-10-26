import java.text.DecimalFormat;
import java.util.Scanner;

public class Exe16Uni5 {
    public Exe16Uni5() {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Digite a altura:");
        float altura = sc.nextFloat();
        
        char genero = 0;
        
        float mediaAlturaGrupo = 0;
        float quantidadeGrupo = 0;
        float alturaGrupo = 0;
        float mediaAlturaF = 0;
        float quantidadeF = 0;
        float alturaF = 0;

        while (altura != 0) {
            System.out.println("Digite o gênero (masculino, feminino ou outro):");
            genero = sc.next().toUpperCase().charAt(0);
            alturaGrupo += altura;
            quantidadeGrupo ++;
            if (genero == 'F') {
                alturaF += altura;
                quantidadeF ++;
            }
            System.out.println("Digite a altura:");
            altura = sc.nextFloat();
        }

        mediaAlturaGrupo = alturaGrupo / quantidadeGrupo;
        mediaAlturaF = alturaF / quantidadeF;

        System.out.println("Média de altura das mulheres = " + df.format(mediaAlturaF));
        System.out.println("Média de altura do grupo = " + df.format(mediaAlturaGrupo));

        sc.close();
    }
    public static void main(String[] args) {
        new Exe16Uni5();
    }
}
