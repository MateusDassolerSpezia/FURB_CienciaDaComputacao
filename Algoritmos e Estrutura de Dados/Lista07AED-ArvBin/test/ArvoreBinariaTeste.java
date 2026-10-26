/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ArvoreBinaria;
import model.NoArvoreBinaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class ArvoreBinariaTeste {
   
    private static ArvoreBinaria arv;
    
    @BeforeEach
    public void setUp() {
        arv = new ArvoreBinaria();
    }
    
    @Test
    public void Teste01() {
        assertTrue(arv.estaVazia());
    }
    
    @Test
    public void Teste02() {
        NoArvoreBinaria no1 = new NoArvoreBinaria(5);
        arv.setRaiz(no1);
        assertFalse(arv.estaVazia());
    }
    
    @Test
    public void Teste03() {
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);
        assertEquals(arv.toString(), "<1<2<><4<><>>><3<5<><>><6<><>>>>");
    }
    
    @Test
    public void Teste04() {
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);
        assertTrue(arv.pertence(1));
    }
    
    @Test
    public void Teste05() {
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);
        assertTrue(arv.pertence(3));
    }
    
    @Test
    public void Teste06() {
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);
        assertTrue(arv.pertence(6));
    }
    
    @Test
    public void Teste07() {
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);
        assertFalse(arv.pertence(10));
    }
    
    @Test
    public void Teste08() {
        NoArvoreBinaria raiz = new NoArvoreBinaria(1);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3);
        NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        raiz.setEsquerda(no2);
        no2.setDireita(no4);
        raiz.setDireita(no3);
        no3.setEsquerda(no5);
        no3.setDireita(no6);
        arv.setRaiz(raiz);
        /*NoArvoreBinaria no4 = new NoArvoreBinaria(4);
        NoArvoreBinaria no2 = new NoArvoreBinaria(2, null, no4);
        NoArvoreBinaria no5 = new NoArvoreBinaria(5);
        NoArvoreBinaria no6 = new NoArvoreBinaria(6);
        NoArvoreBinaria no3 = new NoArvoreBinaria(3, no5, no6);
        NoArvoreBinaria no1 = new NoArvoreBinaria(1, no2, no3);
        arv.setRaiz(no1);*/
        assertEquals(arv.contarNos(), 6);
    }
}
