/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import model.ArvoreBinariaBusca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mateus
 */
public class ArvBinBuscaTeste {
    
    static ArvoreBinariaBusca arvBinBus;
    
    @BeforeEach
    public void setUp() {
        arvBinBus = new ArvoreBinariaBusca();
    }
    
    @Test
    public void Teste01() {
        //ArvoreBinariaBusca arvBinBus = new ArvoreBinariaBusca();
        arvBinBus.inserir(50);
        arvBinBus.inserir(30);
        arvBinBus.inserir(70);
        arvBinBus.inserir(40);
        arvBinBus.inserir(25);
        arvBinBus.inserir(75);
        arvBinBus.inserir(65);
        arvBinBus.inserir(35);
        arvBinBus.inserir(60);
        assertEquals(arvBinBus.toString(), "<50<30<25<><>><40<35<><>><>>><70<65<60<><>><>><75<><>>>>");
    }
    
    @Test
    public void Teste02() {
        arvBinBus.inserir(50);
        arvBinBus.inserir(30);
        arvBinBus.inserir(25);
        arvBinBus.inserir(40);
        arvBinBus.retirar(40);
        assertEquals(arvBinBus.toString(), "<50<30<25<><>><>><>>");
    }
    
    @Test
    public void Teste03() {
        arvBinBus.inserir(80);
        arvBinBus.inserir(52);
        arvBinBus.inserir(90);
        arvBinBus.inserir(48);
        arvBinBus.inserir(71);
        arvBinBus.inserir(63);
        arvBinBus.inserir(67);
        arvBinBus.retirar(71);
        assertEquals(arvBinBus.toString(), "<80<52<48<><>><63<><67<><>>>><90<><>>>");
    }
    
    @Test
    public void Teste04() {
        arvBinBus.inserir(250);
        arvBinBus.inserir(38);
        arvBinBus.inserir(26);
        arvBinBus.inserir(72);
        arvBinBus.inserir(55);
        arvBinBus.inserir(90);
        arvBinBus.inserir(41);
        arvBinBus.inserir(60);
        arvBinBus.inserir(43);
        arvBinBus.inserir(78);
        arvBinBus.inserir(92);
        arvBinBus.inserir(74);
        arvBinBus.retirar(38);
        assertEquals(arvBinBus.toString(), "<250<41<26<><>><72<55<43<><>><60<><>>><90<78<74<><>><>><92<><>>>>><>>");
    }
}
