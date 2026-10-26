/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;



import modelVetor.FilaCheiaException;
import modelVetor.FilaVaziaException;
import modelVetor.FilaVetor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class FilaVetorTeste {
    
    @Test
    public void Teste01() {
        FilaVetor fila = new FilaVetor(5);
        assertTrue(fila.estaVazia());
    }
    
    @Test
    public void Teste02() {
        FilaVetor fila = new FilaVetor(5);
        fila.inserir(10);
        assertFalse(fila.estaVazia());
    }
    
    @Test
    public void Teste03() {
        FilaVetor fila = new FilaVetor(10);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(fila.retirar(), 10);
        assertEquals(fila.retirar(), 20);
        assertEquals(fila.retirar(), 30);
        assertTrue(fila.estaVazia());
    }
    
    @Test
    public void Teste04() {
        FilaVetor fila = new FilaVetor(3);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertThrows(FilaCheiaException.class, ()->{fila.inserir(40);});
    }
    
    @Test
    public void Teste05() {
        FilaVetor fila = new FilaVetor(3);
        assertThrows(FilaVaziaException.class, ()->{fila.retirar();});
    }
    
    @Test
    public void Teste06() {
        FilaVetor fila = new FilaVetor(5);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(fila.peek(), 10);
        assertEquals(fila.retirar(), 10);
    }
    
    @Test
    public void Teste07() {
        FilaVetor fila = new FilaVetor(5);
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        fila.liberar();
        assertTrue(fila.estaVazia());
    }
    
    @Test
    public void Teste08() {
        FilaVetor f1 = new FilaVetor(5);
        f1.inserir(10);
        f1.inserir(20);
        f1.inserir(30);
        FilaVetor f2 = new FilaVetor(3);
        f2.inserir(40);
        f2.inserir(50);
        FilaVetor f3 = f1.criarFilaConcatenada(f2);      
        assertEquals(f3.toString(), "10, 20, 30, 40, 50");
        assertEquals(f1.toString(), "10, 20, 30");
        assertEquals(f2.toString(), "40, 50");
        assertEquals(f3.getLimite(), 8);
    }
    
    @Test
    public void Teste09() {
        FilaVetor f1 = new FilaVetor(8);
        f1.inserir(10);
        f1.inserir(20);
        f1.inserir(30);
        f1.inserir(40);
        f1.inserir(50);
        f1.retirar();
        f1.retirar();
        FilaVetor f2 = new FilaVetor(10);
        f2.inserir(60);
        f2.inserir(70);
        f2.inserir(80);
        f2.inserir(90);
        f2.inserir(100);
        f2.inserir(110);
        f2.inserir(120);
        f2.inserir(130);
        f2.inserir(140);
        f2.inserir(150);
        f2.retirar();
        f2.retirar();
        f2.retirar();
        f2.retirar();
        f2.retirar();
        f2.retirar();
        f2.retirar();
        f2.inserir(160);
        f2.inserir(170);
        FilaVetor f3 = f1.criarFilaConcatenada(f2);      
        assertEquals(f3.toString(), "30, 40, 50, 130, 140, 150, 160, 170");
        assertEquals(f1.toString(), "30, 40, 50");
        assertEquals(f2.toString(), "130, 140, 150, 160, 170");
        assertEquals(f3.getLimite(), 18);
    }
}
