/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.Arvore;
import model.NoArvore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class ArvoreTeste {
    
    private static Arvore arv;
    
    @BeforeEach
    public void setUp() {
        arv = new Arvore();
    }

    @Test
    public void Teste01() {
        NoArvore no5 = new NoArvore(5);
        NoArvore no6 = new NoArvore(6);
        NoArvore no7 = new NoArvore(7);
        NoArvore no2 = new NoArvore(2);
        no2.inserirFilho(no7);
        no2.inserirFilho(no6);
        no2.inserirFilho(no5);
        
        NoArvore no8 = new NoArvore(8);
        NoArvore no3 = new NoArvore(3);
        no3.inserirFilho(no8);
        
        NoArvore no9 = new NoArvore(9);
        NoArvore no10 = new NoArvore(10);
        NoArvore no4 = new NoArvore(4);
        no4.inserirFilho(no10);
        no4.inserirFilho(no9);
        
        NoArvore no1 = new NoArvore(1);
        no1.inserirFilho(no4);
        no1.inserirFilho(no3);
        no1.inserirFilho(no2);
        arv.setRaiz(no1);
        
        assertEquals(arv.toString(), "<1<2<5><6><7>><3<8>><4<9><10>>>");
    }
    
    @Test
    public void Teste02() {
        NoArvore no5 = new NoArvore(5);
        NoArvore no6 = new NoArvore(6);
        NoArvore no7 = new NoArvore(7);
        NoArvore no2 = new NoArvore(2);
        no2.inserirFilho(no7);
        no2.inserirFilho(no6);
        no2.inserirFilho(no5);
        
        NoArvore no8 = new NoArvore(8);
        NoArvore no3 = new NoArvore(3);
        no3.inserirFilho(no8);
        
        NoArvore no9 = new NoArvore(9);
        NoArvore no10 = new NoArvore(10);
        NoArvore no4 = new NoArvore(4);
        no4.inserirFilho(no10);
        no4.inserirFilho(no9);
        
        NoArvore no1 = new NoArvore(1);
        no1.inserirFilho(no4);
        no1.inserirFilho(no3);
        no1.inserirFilho(no2);
        arv.setRaiz(no1);
        
        assertTrue(arv.pertence(7));
    }
    
    @Test
    public void Teste03() {
        NoArvore no5 = new NoArvore(5);
        NoArvore no6 = new NoArvore(6);
        NoArvore no7 = new NoArvore(7);
        NoArvore no2 = new NoArvore(2);
        no2.inserirFilho(no7);
        no2.inserirFilho(no6);
        no2.inserirFilho(no5);
        
        NoArvore no8 = new NoArvore(8);
        NoArvore no3 = new NoArvore(3);
        no3.inserirFilho(no8);
        
        NoArvore no9 = new NoArvore(9);
        NoArvore no10 = new NoArvore(10);
        NoArvore no4 = new NoArvore(4);
        no4.inserirFilho(no10);
        no4.inserirFilho(no9);
        
        NoArvore no1 = new NoArvore(1);
        no1.inserirFilho(no4);
        no1.inserirFilho(no3);
        no1.inserirFilho(no2);
        arv.setRaiz(no1);
        
        assertFalse(arv.pertence(55));
    }
    
    @Test
    public void Teste04() {
        NoArvore no5 = new NoArvore(5);
        NoArvore no6 = new NoArvore(6);
        NoArvore no7 = new NoArvore(7);
        NoArvore no2 = new NoArvore(2);
        no2.inserirFilho(no7);
        no2.inserirFilho(no6);
        no2.inserirFilho(no5);
        
        NoArvore no8 = new NoArvore(8);
        NoArvore no3 = new NoArvore(3);
        no3.inserirFilho(no8);
        
        NoArvore no9 = new NoArvore(9);
        NoArvore no10 = new NoArvore(10);
        NoArvore no4 = new NoArvore(4);
        no4.inserirFilho(no10);
        no4.inserirFilho(no9);
        
        NoArvore no1 = new NoArvore(1);
        no1.inserirFilho(no4);
        no1.inserirFilho(no3);
        no1.inserirFilho(no2);
        arv.setRaiz(no1);
        
        assertEquals(arv.contarNos(), 10);
    }
}