import java.util.Scanner;

public class Prova2Questao {
    public Prova2Questao() {
        Scanner sc = new Scanner(System.in);

        int pesos[] = new int[5];
        String tamanho[] = new String[5];
        
        int quantidade = 0;
        int pesoTotal = 0;
        int pesoExcluido = 0;
        
        char opcao = 0;

        do {
            System.out.println("Escolha uma opção:\nA - Inserir(item, peso)\nB - Imprimir Mochila (ordem inversa)\nC - Valor do item mais pesado\nD - Ordenar mochila (com base no peso)\nE - Excluir item da mochila\nF - Sair");
            System.out.print("Opção escolhida: ");
            opcao = sc.next().toUpperCase().charAt(0);

            switch (opcao) {
                case 'A':
                    System.out.print("Item: ");
                    String item = sc.next();
                    System.out.print("Peso (KG): ");
                    int peso = sc.nextInt();
                    pesoTotal += peso;
                    quantidade = inserir(tamanho, pesos, item, peso, quantidade, pesoTotal);
                    if (pesoTotal > 15) {
                        pesoTotal -= peso;
                    }
                    break;
                case 'B':   
                    imprimir(tamanho, pesos, quantidade);
                        break;
                case 'C':
                    itemMaisPesado(pesos, quantidade, tamanho);
                        break;
                case 'D':
                    System.out.println("Itens ordenados:");
                    ordenar(tamanho, pesos, quantidade);
                        break;
                case 'E':
                    System.out.print("Digite um item para excluir: ");
                    item = sc.next();
                    int indice = pesquisar(tamanho, pesos, item, quantidade);
                    if (indice != -1) {
                    for (int i = indice; i < indice + 1; i++) {
                        pesoExcluido = pesos[i];
                    }
                    pesoTotal -= pesoExcluido;
                    }   
                    int posicao = excluir(tamanho, pesos, quantidade, item, indice);
                    if (posicao != -1) {
                        quantidade = posicao;
                        System.out.println("Item excluído com sucesso\n");
                    } else {
                        System.out.println("Item inexistente, não foi excluído\n");
                    }
                        break;
                case 'F':
                    System.out.println("Saiu");
                        break;
            }
        } while (opcao != 'F');

        sc.close();
    }

    private int inserir(String tamanho[], int pesos[], String item, int peso, int quantidade, int pesoTotal) { // voltar aqui
        if (quantidade < tamanho.length && pesoTotal < 16) {
            tamanho[quantidade] = item;
            pesos[quantidade] = peso;
            quantidade++;
            System.out.println("Item inserido na mochila\n");
        } else {
            System.out.println("Item não inserido na mochila, pois a mochila está cheia ou seu peso máximo foi excedido\n");
        }
        return quantidade;
    }

    private void imprimir(String tamanho[], int pesos[], int quantidade) {
        String temp = null;
        for (int i = 0; i < quantidade; i++) {
          for (int x = 0; x < i; x++) {
            temp = tamanho[i];
            tamanho[i] = tamanho[x];
            tamanho[x] = temp;
          }
        }
        int tempi = 0;
        for (int i = 0; i < quantidade; i++) {
          for (int x = 0; x < i; x++) {
            tempi = pesos[i];
            pesos[i] = pesos[x];
            pesos[x] = tempi;
          }
        }
        for (int i = 0; i < pesos.length; i++) {
            System.out.println(tamanho[i] + " | " + pesos[i] + " Kg");
        }
        System.out.println();
    }
    
    private void itemMaisPesado(int pesos[], int quantidade, String tamanho[]) {
        String itemMaisPesado = null;
        int maisPesado = Integer.MIN_VALUE;
        for (int i = 0; i < quantidade; i++) {
            if (pesos[i] > maisPesado) { 
                maisPesado = pesos[i];
                itemMaisPesado = tamanho[i];
        }
    }
        System.out.println("O item mais pesado é o(a) " + itemMaisPesado);
        
    }

    private void ordenar(String tamanho[], int pesos[], int quantidade) {
        String boulha = null;
        int bolha = 0;
        for (int i = 0; i < quantidade - 1;) {
            if (pesos[i] > pesos[i+1]) {
                bolha = pesos[i];
                pesos[i] = pesos[i+1];
                pesos[i+1] = bolha;

                boulha = tamanho[i];
                tamanho[i] = tamanho[i+1];
                tamanho[i+1] = boulha;
                i = 0;
            } else {
                i++;
            }
            
        }
        for (int i = 0; i < pesos.length; i++) {
            System.out.println(tamanho[i] + " | " + pesos[i] + " Kg");
        }
        System.out.println();
    }

    private int pesquisar(String tamanho[], int pesos[], String item, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (tamanho[i].trim().equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private int excluir(String tamanho[], int pesos[], int quantidade, String item, int indice) {
        //int indice = pesquisar(tamanho, pesos, item, quantidade);
        if (indice != -1) {
            for (int i = indice; i < pesos.length - 1; i++) {
                tamanho[i] = tamanho[i + 1];
                pesos[i] = pesos[i + 1];
            }
            quantidade--;
            return quantidade;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        new Prova2Questao();
    }
}
