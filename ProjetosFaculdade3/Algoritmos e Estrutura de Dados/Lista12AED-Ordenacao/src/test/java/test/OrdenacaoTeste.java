/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.OrdenacaoBolha;
import model.OrdenacaoBolhaOtimizada;
import model.OrdenacaoMergeSort;
import model.OrdenacaoQuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class OrdenacaoTeste {

    @Test
    public void Teste01() {
        Comparable vetor[] = {70, 2, 88, 15, 90, 30};
        Comparable vetorOrdenado[] = {2, 15, 30, 70, 88, 90};
        OrdenacaoBolha ordBol = new OrdenacaoBolha();
        ordBol.setInfo(vetor);
        ordBol.ordernar();
        assertArrayEquals(ordBol.getInfo(), vetorOrdenado);
    }

    @Test
    public void Teste02() {
        Comparable vetor[] = {70, 2, 88, 15, 90, 30};
        Comparable vetorOrdenado[] = {2, 15, 30, 70, 88, 90};
        OrdenacaoBolhaOtimizada ordBolOtm = new OrdenacaoBolhaOtimizada();
        ordBolOtm.setInfo(vetor);
        ordBolOtm.ordernar();
        assertArrayEquals(ordBolOtm.getInfo(), vetorOrdenado);
    }

    @Test
    public void Teste03() {
        Comparable vetor[] = {70, 2, 88, 15, 90, 30};
        Comparable vetorOrdenado[] = {2, 15, 30, 70, 88, 90};
        OrdenacaoQuickSort ordQuiSor = new OrdenacaoQuickSort();
        ordQuiSor.setInfo(vetor);
        ordQuiSor.ordernar();
        assertArrayEquals(ordQuiSor.getInfo(), vetorOrdenado);
    }

    @Test
    public void Teste04() {
        Comparable vetor[] = {70, 2, 88, 15, 90, 30};
        Comparable vetorOrdenado[] = {2, 15, 30, 70, 88, 90};
        OrdenacaoMergeSort ordMerSor = new OrdenacaoMergeSort();
        ordMerSor.setInfo(vetor);
        ordMerSor.ordernar();
        assertArrayEquals(ordMerSor.getInfo(), vetorOrdenado);
    }
}
