/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class OrdenacaoBolhaOtimizada<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordernar() {
        int i, j;
        int n = getInfo().length;
        boolean trocou;

        for (i = n - 1; i >= 1; i--) {
            trocou = false;
            for (j = 0; j < i; j++) {
                if (getInfo()[j].compareTo(getInfo()[j + 1]) > 0) {
                    trocar(j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou/*trocou == false*/) {
                return;
            }
        }
    }
}
