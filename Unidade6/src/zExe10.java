import java.util.Scanner;

public class zExe10 {
    public zExe10() {
        Scanner sc = new Scanner(System.in);
        int vetor[] = new int[50];
        int quantidade = 0;
        int menu = 0;

        do {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Inserir\n2 - Pesquisar\n3 - Alterar");
            System.out.println("4 - Excluir\n5 - Mostrar\n6 - Ordenar");
            System.out.println("7 - Inverter\n8 - Sair");

            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("Digite um número para inserir: ");
                    int numero = sc.nextInt();
                    quantidade = inserir(vetor, numero, quantidade);
                    break;
                case 2:
                    System.out.print("Digite um número para pesquisar: ");
                    numero = sc.nextInt();
                    if (pesquisar(vetor, numero, quantidade) != -1) {
                        System.out.println("Número encontrado");
                    } else {
                        System.out.println("Número não existe no vetor");
                    }
                    break;
                case 3:
                    System.out.print("Digite um número para alterar: ");
                    numero = sc.nextInt();
                    System.out.print("Digite o novo número: ");
                    int novoNumero = sc.nextInt();
                    alterar(vetor, numero, novoNumero, quantidade);
                    break;
                case 4:
                    System.out.print("Digite um número para excluir: ");
                    numero = sc.nextInt();
                    int tamanho = excluir(vetor, numero, quantidade);
                    if (tamanho != -1) {
                        quantidade = tamanho;
                        System.out.println("Número excluído com sucesso");
                    } else {
                        System.out.println("Número inexistente, não foi excluído");
                    }
                    break;
                case 5:
                    mostrar(vetor, quantidade);
                    break;
                case 6: // Bolha
                    break;
                case 7: // Inverter
                    inverter(vetor, quantidade);
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (menu != 8);

        sc.close();
    }
    private void inverter(int vet[], int tamanho) {
        int temp = 0;
        for (int i = 0; i < tamanho; i++) {
          for (int x = 0; x < i; x++) {
            temp = vet[i];
            vet[i] = vet[x];
            vet[x] = temp;
          }
        }
      }

    private void mostrar(int[] vetor, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(vetor[i]);
        }
    }

    private int excluir(int[] vetor, int numero, int tamanho) {
        int indice = pesquisar(vetor, numero, tamanho);
        if (indice != -1) {
            for (int i = indice; i < tamanho - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            tamanho--;
            return tamanho;
        } else {
            return -1;
        }
    }

    private void alterar(int[] vetor, int numero, int novoNumero, int tamanho) {
        int indice = pesquisar(vetor, numero, tamanho);
        if (indice != -1) {
            vetor[indice] = novoNumero;
            System.out.println("Número foi alterado");
        } else {
            System.out.println("Não foi possível alterar o número, pois não existe");
        }
    }

    private int pesquisar(int[] vetor, int numero, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            if (vetor[i] == numero) {
                return i;
            }
        }
        return -1;
    }

    private int inserir(int[] vetor, int numero, int tamanho) {
        if (tamanho < vetor.length) {
            vetor[tamanho] = numero;
            tamanho++;
            System.out.println("Dados inseridos com sucesso");
        } else {
            System.out.println("Vetor está cheio. Não é possível inserir");
        }
        return tamanho;
    }
    public static void main(String[] args) {
        new zExe10();
    }
}
