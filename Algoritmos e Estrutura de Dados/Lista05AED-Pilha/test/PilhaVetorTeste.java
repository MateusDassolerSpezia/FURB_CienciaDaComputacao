/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import modelVetor.PilhaCheiaException;
import modelVetor.PilhaVaziaException;
import modelVetor.PilhaVetor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class PilhaVetorTeste {
    
    /*private static PilhaVetor pilha;
    
    @BeforeEach
    public void setUp() {
        pilha = new PilhaVetor(10);
    }*/
    
    @Test
    public void Teste1() {
        PilhaVetor pilha = new PilhaVetor(5);
        assertTrue(pilha.estaVazia());
    }
    
    @Test
    public void Teste2() {
        PilhaVetor pilha = new PilhaVetor(5);
        pilha.push(10);
        assertFalse(pilha.estaVazia());
    }
    
    @Test
    public void Teste3() {
        PilhaVetor pilha = new PilhaVetor(10);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        assertEquals(pilha.pop(), 30);
        assertEquals(pilha.pop(), 20);
        assertEquals(pilha.pop(), 10);
        assertTrue(pilha.estaVazia());
    }
    
    @Test
    public void Teste4() {
        PilhaVetor pilha = new PilhaVetor(3);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        assertThrows(PilhaCheiaException.class, ()->{pilha.push(40);});
    }
    
    @Test
    public void Teste5() {
        PilhaVetor pilha = new PilhaVetor(3);
        assertThrows(PilhaVaziaException.class, ()->{pilha.pop();});
    }
    
    @Test
    public void Teste6() {
        PilhaVetor pilha = new PilhaVetor(5);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        assertEquals(pilha.peek(), 30);
        assertEquals(pilha.pop(), 30);
    }
    
    @Test
    public void Teste7() {
        PilhaVetor pilha = new PilhaVetor(5);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        pilha.liberar();
        assertTrue(pilha.estaVazia());
    }
    
    @Test
    public void Teste8() {
        PilhaVetor pilha = new PilhaVetor(5);
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        PilhaVetor pilha2 = new PilhaVetor(5);
        pilha2.push(40);
        pilha2.push(50);
        pilha.concatenar(pilha2);
        assertEquals(pilha.toString(), "50, 40, 30, 20, 10");
    }
}