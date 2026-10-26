/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Mateus
 */
public class ArvoreBinaria<T> {

    private NoArvoreBinaria raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void setRaiz(NoArvoreBinaria raiz) {
        this.raiz = raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public boolean pertence(T info) {
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvoreBinaria no, T info) {
        if (no == null) {
            return false;
        } else {
            return (no.getInfo() == info) || pertence(no.getEsquerda(), info) || pertence(no.getDireita(), info);
        }
    }

    @Override
    public String toString() {
        return arvorePre(raiz);
    }

    private String arvorePre(NoArvoreBinaria no) { //Prova, fazer a arvorePos e a ordemSistematica
        if (no == null) {
            return "<>";
        } else {           
            String esquerdaStr = arvorePre(no.getEsquerda()); //pega toda a esquerda até chegar no null
            String direitaStr = arvorePre(no.getDireita()); //pega toda a direita até chegar no null
            return "<" + esquerdaStr + no.getInfo() + direitaStr + ">"; //imprimi do ultimo elemento até a raiz
        }
    }
//------------------------------------------------------------------------------------------------------------------------

    public String toString(T valor) { //toString com parâmetro para escolher o valor em que deseja começar a imprimir a árvore
        NoArvoreBinaria no = buscar(valor); //Salva no nó a posição da info encontrada em buscar.
        return arvorePre(no); //Imprimi a partir do no encontrado em buscar
    }

    public NoArvoreBinaria buscar(T info) {
        return buscar(raiz, info);
    }

    private NoArvoreBinaria buscar(NoArvoreBinaria no, T info) { 
        if (no == null) { //Confere se o no é nulo.
            return null; //Retorna nulo.
        } else if (no.getInfo() == info) { //Confere se o no em que se quer começar a imprimir é a raiz.
            return no; //Retorna o no da raiz.
        }
        NoArvoreBinaria esquerda = buscar(no.getEsquerda(), info); //Como no método "pertence", compara todos os nós da esquerda com nossa info.
        if (esquerda != null) { //Se não for encontrada a info na esquerda, pula o return esquerda para poder passar pelo return que confere a direita.
            return esquerda; //Retorna a posição do no se algum no da esquerda tiver a mesma info que foi passada.
        }
        return buscar(no.getDireita(), info); /*Como no método "pertence", compara os nós da direita com nossa info e retorna a posição do no se algum 
                                              no da direita tiver a mesma info que foi passada.*/
    }
//-------------------------------------------------------------------------------------------------------------------------

    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoArvoreBinaria no) {
        if (no == null) {
            return 0;
        } else {
            return 1 + contarNos(no.getEsquerda()) + contarNos(no.getDireita());
        }
    }
    
    public int contarFolhas(NoArvoreBinaria<T> sa) {
        int contaFolhas = 0; // Variável para armazenar a quantidade de folhas.
        if (sa == null || raiz.getEsquerda() == null && raiz.getDireita() == null) { // Se a árvore for nula ou se a raiz não tiver nenhum filho, vai retornar que a árvore possui 0 folhas.
            return 0; // Retorna 0
        } 
        if (sa.getEsquerda() == null && sa.getDireita() == null) { // Se, ao percorrer a árvore, encontrar um nó que que apresenta nulo na esquerda e na direita, esse é um no folha. Assim, será incrementada a variavel contaFolhas.
            contaFolhas++; // incrementa a quantidade de folhas.
        }
        return contaFolhas + contarFolhas(sa.getEsquerda()) + contarFolhas(sa.getDireita()); // Finalmente, retorna o valor armazenado na variável contaFolhas. Para isso ocorrer de maneira correta, utilizo a recursividade, chamando
        //o método novamente enquanto pego as informações da esquerda e da direita da árvore até percorre-la por inteiro.
    }
    
}