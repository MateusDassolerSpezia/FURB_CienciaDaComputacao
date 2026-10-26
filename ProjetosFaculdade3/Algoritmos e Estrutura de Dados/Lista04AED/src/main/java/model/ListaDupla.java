/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class ListaDupla<T> {

    private NoListaDupla primeiro;
    private NoListaDupla ultimo;

    public ListaDupla() {
        primeiro = null;
        ultimo = null;
    }

    public NoListaDupla getPrimeiro() {
        return primeiro;
    }

    public NoListaDupla getUltimo() {
        NoListaDupla p = primeiro;
        while (p.getProximo() != null) {
            p = p.getProximo();
        }
        return p;
    }

    public void inserir(T valor) {
        NoListaDupla<T> novo = new NoListaDupla<T>();
        novo.setInfo(valor);
        novo.setProximo(primeiro);
        novo.setAnterior(null);
        if (primeiro != null) {
            primeiro.setAnterior(novo);
        }
        primeiro = novo;
    }

    public NoListaDupla buscar(T valor) {
        NoListaDupla no = primeiro;
        while (no != null) {
            if (no.getInfo() == valor) {
                return no;
            }
            no = no.getProximo();
        }
        return null;
    }

    public void retirar(T valor) {
        NoListaDupla no = buscar(valor);
        if (no != null) {
            if (primeiro == no) {
                primeiro = no.getProximo();
            } else {
                no.getAnterior().setProximo(no.getProximo());
            }
        }
        if (no.getProximo() != null) {
            no.getProximo().setAnterior(no.getAnterior());
        }
    }

    public String exibirOrdemInversa() {
        String S = "";
        NoListaDupla p = getUltimo();
        while (p != null) {
            S += p.getInfo();
            if (p.getAnterior() != null) {
                S += (", ");
            }
            p = p.getAnterior();
        }
        return S;
    }

    public void liberar() {
        NoListaDupla no = primeiro;
        while (no != null) {
            no.setProximo(null);
            no = no.getProximo();
        }
        primeiro = null;
    }

    public String toString() {
        String S = "";
        NoListaDupla p = primeiro;
        while (p != null) {
            S += p.getInfo();
            if (p.getProximo() != null) {
                S += (", ");
            }
            //p.setProximo(p.getProximo());
            p = p.getProximo();
        }
        return S;
    }
    
    public ListaDupla clonar() {
        NoListaDupla no = getUltimo(); 
        ListaDupla clone = new ListaDupla();      
        while (no != null) {
            clone.inserir(no.getInfo());
            no = no.getAnterior();
        }
        return clone;
        /*- Primeiramente eu criei um no que começa pelo ultimo elemento da lista original, pois na lista encadeada o primeiro elemento inserido é o ultimo da lista.
        - Então, criei uma nova ListaDupla chamada clone para alocar os valores da lista original.
        - Por sequente, criei um While que funciona enquanto o no for diferente de null para poder percorrer a lista inteira, dentro desse While eu utilizei o método inserir 
        para poder inserir a informação que tinha no nó da lista original para a nova lista. Após isso, fiz o nó pegar a informação anterior a ele, já que começei do último.
        - Finalmente, dei um return na nova lista.*/
    }
}
