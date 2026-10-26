/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mdspezia
 */
public class OrdenacaoQuickSort<T extends Comparable<T>> extends OrdenacaoAbstract<T> {

    @Override
    public void ordernar() {
        int n = getInfo().length - 1;
        quickSort(0, n);
    }

    private void quickSort(int inicio, int fim) {
        if (inicio < fim) {
            int idxPivo = particionar(inicio, fim);
            quickSort(inicio, idxPivo - 1);
            quickSort(idxPivo + 1, fim);
        }
    }

    private int particionar(int inicio, int fim) {
        int a = inicio;
        int b = fim + 1;
        T pivo = getInfo()[inicio];

        while (true) {
            do {
                a++;
            } while (a <= fim && getInfo()[a].compareTo(pivo) < 0);

            do {
                b--;
            } while (b >= inicio && getInfo()[b].compareTo(pivo) > 0);

            if (a >= b) {
                break;
            }
            trocar(a, b);
        }
        trocar(b, inicio);
        return b;
    }
}
