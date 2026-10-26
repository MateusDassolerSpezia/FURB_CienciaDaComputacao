/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class ArvoreBinariaBusca<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

    public void inserir(T info) {
        NoArvoreBinaria<T> novo = new NoArvoreBinaria(info);

        if (getRaiz() == null) {
            setRaiz(novo);
        } else {
            NoArvoreBinaria<T> p = getRaiz();

            while (true) {
                NoArvoreBinaria<T> pai = p;

                if (info.compareTo(p.getInfo()) < 0) {
                    p = pai.getEsquerda();
                    if (p == null) {
                        pai.setEsquerda(novo);
                        return;
                    }
                } else {
                    p = pai.getDireita();
                    if (p == null) {
                        pai.setDireita(novo);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public NoArvoreBinaria buscar(T info) {
        NoArvoreBinaria<T> p = getRaiz();
        while(p != null && p.getInfo() != info) {
            if(info.compareTo(p.getInfo()) < 0) {
                p = p.getEsquerda();
            } else {
                p = p.getDireita();
            }
        }
        return p;
    }

    private NoArvoreBinaria extrairSucessor(NoArvoreBinaria excluir) {
        NoArvoreBinaria<T> atual = excluir.getDireita();
        NoArvoreBinaria<T> paiSucessor = excluir;
        NoArvoreBinaria<T> sucessor = excluir;
        
        while (atual != null) {
            paiSucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsquerda();
        }
        
        if (sucessor != excluir.getDireita()) {
            paiSucessor.setEsquerda(sucessor.getDireita());
            sucessor.setDireita(excluir.getDireita());
        }
        
        return sucessor;
    }

    public void retirar(T info) {
        NoArvoreBinaria<T> p = getRaiz();
        NoArvoreBinaria<T> pai = null;
        boolean filhoEsquerda = false;
        
        while(p != null && p.getInfo() != info) {
            pai = p;
            if(info.compareTo(p.getInfo()) < 0) {
                filhoEsquerda = true;
                p = p.getEsquerda();
            } else {
                filhoEsquerda = false;
                p = p.getDireita();
            }
        }
        
        if(p != null) {
            if(p.getEsquerda() == null && p.getDireita() == null) {
                if(p == getRaiz()) {
                    setRaiz(null);
                } else {
                    if(filhoEsquerda == true) {
                        pai.setEsquerda(null);
                    } else {
                        pai.setDireita(null);
                    }
                }
            } else {
                if(p.getDireita() == null) {
                    if(p == getRaiz()) {
                        setRaiz(p.getEsquerda());
                    } else {
                        if(filhoEsquerda == true) {
                            pai.setEsquerda(p.getEsquerda());
                        } else {
                            pai.setDireita(p.getEsquerda());
                        }
                    }
                } else {
                    if(p.getEsquerda() == null) {
                        if(p == getRaiz()) {
                            setRaiz(p.getDireita());
                        } else {
                            if(filhoEsquerda == true) {
                                pai.setEsquerda(p.getDireita());
                            } else {
                                pai.setDireita(p.getDireita());
                            }
                        }
                    } else {
                        NoArvoreBinaria sucessor = extrairSucessor(p);
                        
                        if(p == getRaiz()) {
                            setRaiz(sucessor);
                        } else {
                            if(filhoEsquerda == true) {
                                pai.setEsquerda(sucessor);
                            } else {
                                pai.setDireita(sucessor);
                            }
                        }
                        sucessor.setEsquerda(p.getEsquerda());
                    }
                }
            }
        }
    }
}
