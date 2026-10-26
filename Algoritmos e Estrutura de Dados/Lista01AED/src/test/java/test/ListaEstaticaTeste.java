/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ListaEstatica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class ListaEstaticaTeste {
    
    @Test
    public void Teste1() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("5, 10, 15, 20", lista.toString());
    }
    
    @Test
    public void Teste2() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(4, lista.getTamanho());
    }
    
    @Test
    public void Teste3e4() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(2, lista.buscar(15));
        assertEquals(-1, lista.buscar(30));
    }
    
    @Test
    public void Teste5() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(10);
        assertEquals("5, 15, 20", lista.toString());
        assertEquals(3, lista.getTamanho());
    }
    
    @Test
    public void Teste6() {
        ListaEstatica lista = new ListaEstatica();
        for (int i = 1; i <= 15; i++) {
            lista.inserir(i);
        }
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15", lista.toString()); // mais certo nessa sequencia de igualdade
        assertEquals(lista.getTamanho(), 15);
    }
    @Test
    public void Teste7() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(20, lista.obterElemento(3));
    }
    
    public void Teste8() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertThrows(IndexOutOfBoundsException.class, ()->{lista.obterElemento(5);});
    }

    @Test
    public void Teste9() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(10);
        lista.liberar();
        //assertEquals(true, lista.estaVazia());
        assertTrue(lista.estaVazia());
    }
    
}

   