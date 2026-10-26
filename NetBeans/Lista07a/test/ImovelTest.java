package test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.text.DecimalFormat;
import model.Bairro;
import model.Finalidade;
import model.Imovel;
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
public class ImovelTest {
    
    private static Imovel i;
    private static Bairro b;
    private static DecimalFormat df;
    //IllegalArgumentException e;
    
    @BeforeAll
    public static void setUpClass() {
        b = new Bairro("Bairro", 2);
        i = new Imovel(b, Finalidade.RESIDENCIAL, "Rua", 1);
        df = new DecimalFormat("0.00");
        
    }
    
    @Test //(expected = IllegalArgumentException.class)
    public void caso1() throws Exception {
        i.setArea(-5);
    }
    @Test
    public void caso2() throws Exception {
        i.setFinalidade(Finalidade.RESIDENCIAL);
        b.setNome("Centro");
        b.setCoeficienteIptu(1);
        i.calcularIptu();
    }
    @Test
    public void caso3() throws Exception {
        b.setNome("Centro");
        b.setCoeficienteIptu(1);
        i.setArea(250);
        //i.setFinalidade(null);
        i.calcularIptu();
    }
    @Test
    public void caso4() throws Exception {
        //i.setBairro(null);
        i.setArea(400);
        i.calcularIptu();
    }
    
    @Test
    public void caso5() {
        b.setCoeficienteIptu(1);
        i.setArea(400);
        i.setFinalidade(Finalidade.RESIDENCIAL);
        assertEquals("400,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso6() {
        b.setCoeficienteIptu(1);
        i.setArea(80);
        i.setFinalidade(Finalidade.COMERCIAL);
        assertEquals("500,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso7() {
        b.setCoeficienteIptu(1);
        i.setArea(250);
        i.setFinalidade(Finalidade.COMERCIAL);
        assertEquals("1000,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso8() {
        b.setCoeficienteIptu(1);
        i.setArea(500);
        i.setFinalidade(Finalidade.COMERCIAL);
        assertEquals("1275,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso9() {
        b.setCoeficienteIptu(1);
        i.setArea(2000);
        i.setFinalidade(Finalidade.INDUSTRIAL);
        assertEquals("1000,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso10() {
        b.setCoeficienteIptu(1);
        i.setArea(3000);
        i.setFinalidade(Finalidade.INDUSTRIAL);
        assertEquals("1650,00", df.format(i.calcularIptu()));
    }
    @Test
    public void caso11() {
        b.setCoeficienteIptu(2.5);
        i.setArea(500000);
        i.setFinalidade(Finalidade.RESIDENCIAL);
        assertEquals("1250000,00", df.format(i.calcularIptu()));
    }
}
