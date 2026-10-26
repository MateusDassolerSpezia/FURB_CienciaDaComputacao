
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ListaEstaticaGenerica;
import model.Pessoa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class ListaEstaticaTeste {
    
    @Test
    public void Teste1() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals("5, 10, 15, 20", lista.toString());
    }
    
    @Test
    public void Teste2() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(4, lista.getTamanho());
    }
    
    @Test
    public void Teste3e4() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(2, lista.buscar(15));
        assertEquals(-1, lista.buscar(30));
    }
    
    @Test
    public void Teste5() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
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
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        for (int i = 1; i <= 15; i++) {
            lista.inserir(i);
        }
        assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15", lista.toString()); // mais certo nessa sequencia de igualdade
        assertEquals(lista.getTamanho(), 15);
    }
    @Test
    public void Teste7() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertEquals(20, lista.obterElemento(3));
    }
    
    public void Teste8() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        assertThrows(IndexOutOfBoundsException.class, ()->{lista.obterElemento(5);});
    }

    @Test
    public void Teste9() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.retirar(10);
        lista.liberar();
        //assertEquals(true, lista.estaVazia());
        assertTrue(lista.estaVazia());
    }
    
    @Test
    public void Teste10() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.inverter();
        assertEquals(lista.toString(), "20, 15, 10, 5");
    }
    
    @Test
    public void Teste11() {
        ListaEstaticaGenerica lista = new ListaEstaticaGenerica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.inserir(25);
        lista.inverter();
        assertEquals(lista.toString(), "25, 20, 15, 10, 5");
    }
    
    @Test
    public void Teste12() {
        ListaEstaticaGenerica<Pessoa> pessoas = new ListaEstaticaGenerica<Pessoa>();
        pessoas.inserir(new Pessoa("P1", 10));
        Pessoa p = new Pessoa("P2", 20);
        pessoas.inserir(p);
        pessoas.inserir(new Pessoa("P3", 30));
        assertEquals(pessoas.getTamanho(), 3);
        
        String dados = "Pessoa{nome=P1, idade=10}, " + "Pessoa{nome=P2, idade=20}, " + "Pessoa{nome=P3, idade=30}";
        assertEquals(pessoas.toString(), dados);
        
        int pBusca = pessoas.buscar(p);
        assertEquals(1, pBusca);
        
        pessoas.retirar(p);
        dados = "Pessoa{nome=P1, idade=10}, " + "Pessoa{nome=P3, idade=30}";
        assertEquals(pessoas.toString(), dados);
    }
    
}

   
