import java.util.Scanner;

public class Exe33Uni5 {
    public Exe33Uni5() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Códigos disponíveis: 1, 2, 3, 4 = voto para o respectivo candidato / 5 = voto nulo / 6 = voto em branco");
        System.out.println();

        int totalVotos = 0;
        int totalVotosCandidato1 = 0;
        int totalVotosCandidato2 = 0;
        int totalVotosCandidato3 = 0;
        int totalVotosCandidato4 = 0;
        int totalVotosNulos = 0;
        int totalVotosBranco = 0;

        int codigo = 0;
        do {
            System.out.println("Digite o código do seu voto:");
            codigo = sc.nextInt();
            if (codigo < 0 || codigo > 6) {
                System.out.println("Opção incorreta");  
            }
            totalVotos ++;
            if (codigo == 1) {
                totalVotosCandidato1 ++;
            } else if (codigo == 2) {
                totalVotosCandidato2 ++;
            } else if (codigo == 3) {
                totalVotosCandidato3 ++;
            } else if (codigo == 4) {
                totalVotosCandidato4 ++;
            } else if (codigo == 5) {
                totalVotosNulos ++;
            } else if (codigo == 6) {
                totalVotosBranco ++;
            }
        } while (codigo != 0);

        totalVotos --;
        float percentualBrancoNulos = ((totalVotosNulos + totalVotosBranco) * 100) / totalVotos;

        System.out.println(totalVotos);
        System.out.println("Total de votos do candidato 1: " + totalVotosCandidato1);
        System.out.println("Total de votos do candidato 2: " + totalVotosCandidato2);
        System.out.println("Total de votos do candidato 3: " + totalVotosCandidato3);
        System.out.println("Total de votos do candidato 4: " + totalVotosCandidato4);
        System.out.println("Total de votos nulos: " + totalVotosNulos);
        System.out.println("Total de votos em branco: " + totalVotosBranco);
        System.out.println("percentual dos votos em branco e nulos sobre o total: " + percentualBrancoNulos + "%");

        sc.close();
    }
    public static void main(String[] args) {
        new Exe33Uni5();
    }
}
