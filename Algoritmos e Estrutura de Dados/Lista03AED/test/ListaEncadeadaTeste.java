package test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import model.ListaEncadeada;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Mateus
 */
public class ListaEncadeadaTeste {
    
    private static ListaEncadeada lista;
    
    @BeforeEach
    public void setUp() {
        lista = new ListaEncadeada();
    }

    @Test
    public void Teste1() {
        //ListaEncadeada lista = new ListaEncadeada();
        assertTrue(lista.estaVazia());
    }

    @Test
    public void Teste2() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        assertFalse(lista.estaVazia());
    }

    @Test
    public void Teste3() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        assertEquals("5", lista.toString());
        assertEquals(lista.obterComprimento(), 1);
    }

    @Test
    public void Teste4() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        assertEquals("15, 10, 5", lista.toString());
        assertEquals(lista.obterComprimento(), 3);
    }

    @Test
    public void Teste5() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.buscar(20).getInfo().toString(), "20");
    }

    @Test
    public void Teste6() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("15", lista.buscar(15).getInfo().toString());
    }

    @Test
    public void Teste7() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.buscar(50), null);
    }

    @Test
    public void Teste8() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(20);
        assertEquals("15, 10, 5", lista.toString());
    }

    @Test
    public void Teste9() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(15);
        assertEquals("20, 10, 5", lista.toString());
    }

    @Test
    public void Teste10() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.obterNo(0).getInfo().toString(), "20");
    }

    @Test
    public void Teste11() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.obterNo(3).getInfo().toString(), "5");
    }

    @Test
    public void Teste12() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertThrows(IndexOutOfBoundsException.class, () -> {lista.obterNo(10);});

    }

    @Test
    public void Teste13() {
        //ListaEncadeada lista = new ListaEncadeada();
        assertEquals(lista.obterComprimento(), 0);
    }

    @Test
    public void Teste14() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.obterComprimento(), 4);
    }
    
    @Test
    public void Teste15() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(lista.obterComprimento(), 4);
    }
    
    @Test
    public void Teste16() {
        //ListaEncadeada lista = new ListaEncadeada();
        lista.inserir(70);
        lista.inserir(60);
        lista.inserir(50);
        lista.inserir(40);
        lista.inserir(30);
        lista.inserir(20);
        lista.inserir(10);
        ListaEncadeada novaLista = lista.criarSublista(2, 7);
        assertEquals("60, 50, 40, 30", novaLista.toString());
    }
}
