/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;


import java.time.LocalDate;
import java.time.Month;
import model.Aluno;
import model.MapaDispersao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class MapaDispersaoTeste {
    
    private static MapaDispersao<Aluno> mapa;
    
    @BeforeEach
    public void setUp() {
        mapa = new MapaDispersao(53);
    }

    @Test
    public void Teste01() {
        Aluno ob1 = new Aluno(12000, "Jean", LocalDate.of(2000, Month.JANUARY, 1));
        mapa.inserir(12000, ob1);
        assertTrue(mapa.buscar(12000).equals(ob1));
    }
    
    @Test
    public void Teste02() {
        Aluno ob1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        Aluno ob2 = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno ob3 = new Aluno(12500, "Marta", LocalDate.of(2001, 2, 18));
        Aluno ob4 = new Aluno(13000, "Lucas", LocalDate.of(1998, 11, 25));
        mapa.inserir(12000, ob1);
        mapa.inserir(14000, ob2);
        mapa.inserir(12500, ob3);
        mapa.inserir(13000, ob4);
        assertTrue(mapa.buscar(12000).equals(ob1));
        assertTrue(mapa.buscar(14000).equals(ob2));
        assertTrue(mapa.buscar(12500).equals(ob3));
        assertTrue(mapa.buscar(13000).equals(ob4));
    }
    
    @Test
    public void Teste03() {
        Aluno ob1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        Aluno ob2 = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno ob3 = new Aluno(14226, "Marta", LocalDate.of(2001, 2, 18));
        Aluno ob4 = new Aluno(17180, "Lucas", LocalDate.of(1998, 11, 25));
        mapa.inserir(12000, ob1);
        mapa.inserir(14000, ob2);
        mapa.inserir(14226, ob3);
        mapa.inserir(17180, ob4);
        assertTrue(mapa.buscar(12000).equals(ob1));
        assertTrue(mapa.buscar(14000).equals(ob2));
        assertTrue(mapa.buscar(14226).equals(ob3));
        assertTrue(mapa.buscar(17180).equals(ob4));
    }
    
    @Test
    public void Teste04() {
        Aluno ob1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        Aluno ob2 = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno ob3 = new Aluno(14226, "Marta", LocalDate.of(2001, 2, 18));
        Aluno ob4 = new Aluno(17180, "Lucas", LocalDate.of(1998, 11, 25));
        mapa.inserir(12000, ob1);
        mapa.inserir(14000, ob2);
        mapa.inserir(14226, ob3);
        mapa.inserir(17180, ob4);
        mapa.remover(12000);
        assertNull(mapa.buscar(12000));
    }
}
