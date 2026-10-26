import java.util.Scanner;

public class Exe10Uni6 {
    public Exe10Uni6() {
        Scanner sc = new Scanner(System.in);

        int numeros[] = new int[50];
        
        int opcao = 0;
        int tamanho = 0;
        //int novoValor = 0;
    
        do {
            System.out.println("Escolha uma opção:\n1 - Incluir valor\n2 - Pesquisar valor\n3 - Alterar valor\n4 - Excluir valor\n5 - Mostrar valores\n6 - Ordenar valores (ordem crescente)\n7 - Inverter valores\n8 - Sair do sistema");
            System.out.print("Opção escolhida: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                tamanho = incluirValor(numeros, sc, tamanho);
                    break;
                case 2:
                System.out.println("Digite um número para busca: ");
                int numeroBusca = sc.nextInt();
                if (pesquisarValor(numeroBusca, numeros)) {
                    System.out.println("Está no vetor\n");
                } else { 
                    System.out.println("Não está no vetor\n");
                }   break;
                case 3:
                alterarValor(numeros, sc); 
                    break;
                case 4:
                excluirValor(numeros, sc);
                    break;
                case 5:
                mostrarValor(numeros);
                    break;
                case 6:
                ordenarValor(numeros);
                    break;
                case 7:
                inverterValor(numeros);
                    break;
                default: System.out.println("Opção inválida");
            }
        } while (opcao != 8);

        sc.close();
    }

    private int incluirValor(int vetor[], Scanner sc, int tamanho) {
        if (tamanho < vetor.length) {
            System.out.println("\nInclua um valor no fim do vetor:");
            vetor[tamanho] = sc.nextInt();
            tamanho++;
            System.out.println("Valor incluído no vetor\n");
        } else {
            System.out.println("\nVetor cheio, número não pode ser incluído no vetor\n");
        }
        return tamanho;
    }

    private boolean pesquisarValor(int numero, int vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == numero) {
                return true;
            }
        }
        return false;
    }

    private void alterarValor(int vetor[], Scanner sc) {
        System.out.println("\nDigite um valor para ser alterado:");
        int valor = sc.nextInt();
        for (int i = 0; i < vetor.length;) {
            if (valor == vetor[i]) {
                System.out.println("Digite um novo valor:");
                vetor[i] = sc.nextInt();
            } else {
                i++;
            }  
        } 
    }

    private void excluirValor(int vetor[], Scanner sc) {
        System.out.println("\nDigite um valor para ser excluído:");
        int valor = sc.nextInt();
        for (int i = 0; i < vetor.length;) {
            if (valor == vetor[i]) {
                vetor[i] = 0;
                System.out.println("Valor excluído do vetor\n");
            } else {
                i++;
            }
        }
    }

    private void mostrarValor(int vetor[]) {
        System.out.println("\nValores no vetor:");
        for (int i = 0; i < vetor.length; i++) {
                System.out.println(vetor[i]);
        }
        System.out.println();
    }

    private int ordenarValor(int vetor[]) {
        int bolha = 0;
        for (int i = 0; i < vetor.length - 1;) {
            if (vetor[i] > vetor[i+1]) {
                bolha = vetor[i];
                vetor[i] = vetor[i+1];
                vetor[i+1] = bolha;
                i = 0;
            } else {
                i++;
            }
        }
        System.out.println("\nValores ordenados:");
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != 0) {
                System.out.println(vetor[i]);
            }
        }
        System.out.println();
        return bolha;

    }

    private void inverterValor(int vetor[]) {
        int temp = 0;
        for (int i = 0; i < vetor.length; i++) {
            for (int x = 0; x < i; x++) {
                temp = vetor[i];
                vetor[i] = vetor[x];
                vetor[x] = temp;
            }
        }
        System.out.println("\nValores invertidos");
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != 0) {
            System.out.println(vetor[i]);
        }
    }
        System.out.println();
    }

    public static void main(String[] args) {
        new Exe10Uni6();
    }
}
