/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ListaDupla;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class ListaDuplaTeste {
    
    private static ListaDupla lista;
    
    @BeforeEach
    public void setUp() {
        lista = new ListaDupla();
    }

    @Test
    public void Teste01() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("20, 15, 10, 5", lista.toString());
    }
    
    @Test
    public void Teste02() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("20", lista.buscar(20).getInfo().toString());
    }
    
    @Test
    public void Teste03() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("15", lista.buscar(15).getInfo().toString());
    }
    
    @Test
    public void Teste04() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(20);
        assertEquals("15, 10, 5", lista.toString());
    }
    
    @Test
    public void Teste05() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(10);
        assertEquals("20, 15, 5", lista.toString());
    }
    
    @Test
    public void Teste06() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(5);
        assertEquals("20, 15, 10", lista.toString());
    }
    
    @Test
    public void Teste07() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("20, 15, 10, 5", lista.toString());
        lista.liberar();
        assertEquals("", lista.toString());
    }


    @Test
    public void Teste08() {
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("20, 15, 10, 5", lista.toString());
        assertEquals("5, 10, 15, 20", lista.exibirOrdemInversa());
    }
    
    @Test
    public void Teste09() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(70);
        lista.inserir(60);
        lista.inserir(50);
        lista.inserir(40);
        lista.inserir(30);
        lista.inserir(20);
        lista.inserir(10);
        ListaDupla clone = lista.clonar();
        assertEquals("10, 20, 30, 40, 50, 60, 70", clone.toString());
    }
}