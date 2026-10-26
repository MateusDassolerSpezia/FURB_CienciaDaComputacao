/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;


import modelLista.FilaLista;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class FilaListaTeste {
    
    @Test 
    public void Teste01() {
        FilaLista fila = new FilaLista();
        assertTrue(fila.estaVazia());
    }
    
    @Test 
    public void Teste02() {
        FilaLista fila = new FilaLista();
        fila.inserir(10);
        assertFalse(fila.estaVazia());
    }
    
    @Test 
    public void Teste03() {
        FilaLista fila = new FilaLista();
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
        FilaLista fila = new FilaLista();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        assertEquals(fila.peek(), 10);
        assertEquals(fila.retirar(), 10);
    }
    
    @Test 
    public void Teste05() {
        FilaLista fila = new FilaLista();
        fila.inserir(10);
        fila.inserir(20);
        fila.inserir(30);
        fila.liberar();
        assertTrue(fila.estaVazia()); 
    }
}
