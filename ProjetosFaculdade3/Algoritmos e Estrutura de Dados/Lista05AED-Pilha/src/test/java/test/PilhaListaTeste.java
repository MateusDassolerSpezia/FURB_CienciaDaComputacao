/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import modelLista.PilhaLista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class PilhaListaTeste {
    
    private static PilhaLista pilha;
    
    @BeforeEach
    public void setUp() {
        pilha = new PilhaLista();
    }
    
    @Test
    public void Test01() {
        assertTrue(pilha.estaVazia());
    }
    
    @Test
    public void Test02() {
        pilha.push(10);
        assertFalse(pilha.estaVazia());
    }
    
    @Test
    public void Test03() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        assertEquals(pilha.pop(), 30);
        assertEquals(pilha.pop(), 20);
        assertEquals(pilha.pop(), 10);
        assertTrue(pilha.estaVazia());
    }
    
    @Test
    public void Test04() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        assertEquals(pilha.peek(), 30);
        assertEquals(pilha.pop(), 30);
    }
    
    @Test
    public void Test05() {
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        pilha.liberar();
        assertTrue(pilha.estaVazia());
    }
}
    