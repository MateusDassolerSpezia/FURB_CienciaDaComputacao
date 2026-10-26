import java.util.InputMismatchException;
import java.util.Scanner;

public class zExe9 {
    public zExe9() {
    Scanner s = new Scanner(System.in);
        int notasHomens[] = new int[30];
        int notasMulheres[] = new int[30];
        int idadesMulheres[] = new int[30];

        inicializarVetores(notasMulheres, notasHomens, idadesMulheres);
        popularVetores(notasMulheres, notasHomens, idadesMulheres);
        int qtdHomens = quantidadeValores(notasHomens);
        int qtdMulheres = quantidadeValores(notasMulheres);  

        System.out.println(qtdMulheres); // só para sumir o amarelo

        // Saídas:
        // a) média
        int media = notaMedia(notasHomens, notasMulheres);
        System.out.println("Média: " + media);
        // b) nota média homens
        System.out.println("Nota média dos homens: " + notaMediaHomens(notasHomens, qtdHomens));
        // c) qual a nota atribuída pela mulher mais jovem;
        System.out.println("Nota da mulher mais jovem: " +
                notaMulherMaisJovem(notasMulheres, idadesMulheres));
        // d) quantas das mulheres com mais de 50 anos deram nota superior a
        // média recebida pelo cinema.
        System.out.println("Mulheres com mais de 50 anos que a nota está acima da média: " +
                quantidadeMulheresMaiorMedia(media, idadesMulheres, notasMulheres));
        s.close();

    }

    private int quantidadeMulheresMaiorMedia(int media, int[] idades, int[] notas) {
        int quantidade = 0;

        for (int i = 0; i < idades.length; i++) {
            if (idades[i] > 50 && notas[i] > media) {
                quantidade++;
            }
        }

        return quantidade;
    }

    private int notaMediaHomens(int[] vetHomens, int qtdHomens) {
        int notas = 0;
        for (int i = 0; i < vetHomens.length; i++) {
            notas += vetHomens[i];
        }
        return notas / qtdHomens;
    }

    private int notaMedia(int[] vet1, int[] vet2) {
        int notas = 0;
        for (int i = 0; i < vet1.length; i++) {
            notas += vet1[i] + vet2[i];
        }

        return notas / vet1.length;
    }

    private int quantidadeValores(int[] vetor) {
        int quantidade = 0;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != -1) {
                quantidade++;
            } else {
                return quantidade;
            }
        }
        return quantidade;
    }

    private void inicializarVetores(int[] mulheres, int[] homens,
            int[] idadeMulheres) {
        for (int i = 0; i < mulheres.length; i++) {
            mulheres[i] = -1;
            homens[i] = -1;
            idadeMulheres[i] = -1;
        }
    }

    private void popularVetores(int[] mulheres, int[] homens,
            int[] idadeMulheres) {
        int qtdHomens = 0;
        int qtdMulheres = 0;
        Scanner leitor = new Scanner(System.in);
        System.out.println("Iniciando preenchimento das 30 pessoas: ");
        for (int i = 0; i < mulheres.length; i++) {

            int sexo = 0;
            do {
                try {
                    System.out.print("Sexo (1-F ou 2-M): ");
                    sexo = leitor.nextInt();
                } catch (InputMismatchException e) {
                    leitor = new Scanner(System.in);
                    System.out.println("Informação inválida, deve ser 1 ou 2");
                }
            } while (sexo != 1 && sexo != 2);

            int nota = -1;
            do {
                System.out.print("Nota (0 a 10): ");
                nota = leitor.nextInt();
            } while (nota < 0 || nota > 10);

            int idade = 0;
            do {
                try {
                    System.out.print("Idade: ");
                    idade = leitor.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Erro ao informar idade");
                    leitor = new Scanner(System.in);
                }
            } while (idade <= 0);

            if (sexo == 1) {
                mulheres[qtdMulheres] = nota;
                idadeMulheres[qtdMulheres] = idade;
                qtdMulheres++;
            } else {
                homens[qtdHomens] = nota;
                qtdHomens++;
            }

        }
    }

    // qual a nota atribuída pela mulher mais jovem;
    private int notaMulherMaisJovem(int[] notas, int[] idades) {
        int idadeMenor = Integer.MAX_VALUE;
        int notaMenor = 0;

        int quantidadeMulheres = quantidadeValores(notas);
        for (int i = 0; i < quantidadeMulheres; i++) {
            if (idadeMenor > idades[i]) {
                idadeMenor = idades[i];
                notaMenor = notas[i];
            }
        }
        return notaMenor;
        
      }


    public static void main(String[] args) {
        new zExe9();
    }
}
