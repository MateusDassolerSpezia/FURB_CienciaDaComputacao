/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ListaOrdenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class ListaTeste {

    static ListaOrdenada lista;
    
    @BeforeEach
    public void setUp() {
        lista = new ListaOrdenada();
    }

    @Test 
    public void Teste01() {
        lista.inserir(100);
        lista.inserir(20);
        lista.inserir(70);
        lista.inserir(1);
        assertEquals(lista.toString(), "1, 20, 70, 100");
    }
    
    @Test
    public void Teste02() {
        lista.inserir(0);
        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.inserir(40);
        lista.inserir(50);
        lista.inserir(60);
        lista.inserir(70);
        lista.inserir(80);
        lista.inserir(90);
        lista.inserir(100);
        assertEquals(lista.buscar(20), 2);
    }
    
    @Test
    public void Teste03() {
        lista.inserir(0);
        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.inserir(40);
        lista.inserir(50);
        lista.inserir(60);
        lista.inserir(70);
        lista.inserir(80);
        lista.inserir(90);
        lista.inserir(100);
        assertEquals(lista.buscar(40), 4);
    }
    
    @Test
    public void Teste04() {
        lista.inserir(0);
        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.inserir(40);
        lista.inserir(50);
        lista.inserir(60);
        lista.inserir(70);
        lista.inserir(80);
        lista.inserir(90);
        lista.inserir(100);
        assertEquals(lista.buscar(70), 7);
    }
    
    @Test
    public void Teste05() {
        lista.inserir(0);
        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.inserir(40);
        lista.inserir(50);
        lista.inserir(60);
        lista.inserir(70);
        lista.inserir(80);
        lista.inserir(90);
        lista.inserir(100);
        assertEquals(lista.buscar(100), 10);
    }
    
    @Test
    public void Teste06() {
        lista.inserir(0);
        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);
        lista.inserir(40);
        lista.inserir(50);
        lista.inserir(60);
        lista.inserir(70);
        lista.inserir(80);
        lista.inserir(90);
        lista.inserir(100);
        assertEquals(lista.buscar(85), -1);
    }
}
