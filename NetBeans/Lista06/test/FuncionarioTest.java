/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import java.text.DecimalFormat;
import model.FaixaIrpf;
import model.Funcionario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdspezia
 */
public class FuncionarioTest {

    //DecimalFormat df = new DecimalFormat("0.00");
    private static Funcionario f;
    private static DecimalFormat df;

    @BeforeAll
    public static void setUpClass() {
        f = new Funcionario("a", 10);
        df = new DecimalFormat("0.00");
    }

    @Test
    public void faixa1() {
        f.setSalario(850);
        /*if (f.identificarFaixaIrpf() == FaixaIrpf.PRIMEIRA) {
            System.out.println("SIM");
        }*/
        assertEquals(FaixaIrpf.PRIMEIRA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa1() {
        f.setSalario(850);
        assertEquals(0, f.calcularIrpf());
    }

    @Test
    public void faixa1_2() {
        f.setSalario(1903.98);
        assertEquals(FaixaIrpf.PRIMEIRA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa1_2() {
        f.setSalario(1903.98);
        assertEquals(0, f.calcularIrpf());
    }

    @Test
    public void faixa2() {
        f.setSalario(1903.99);
        assertEquals(FaixaIrpf.SEGUNDA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa2() {
        f.setSalario(1903.99);
        assertEquals("0,00", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa2_2() {
        f.setSalario(2000);
        assertEquals(FaixaIrpf.SEGUNDA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa2_2() {
        f.setSalario(2000);
        assertEquals("7,20", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa2_3() {
        f.setSalario(2826.65);
        assertEquals(FaixaIrpf.SEGUNDA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa2_3() {
        f.setSalario(2826.65);
        assertEquals("69,20", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa3() {
        f.setSalario(2826.66);
        assertEquals(FaixaIrpf.TERCEIRA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa3() {
        f.setSalario(2826.66);
        assertEquals("69,20", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa3_2() {
        f.setSalario(3000);
        assertEquals(FaixaIrpf.TERCEIRA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa3_2() {
        f.setSalario(3000);
        assertEquals("95,20", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa3_3() {
        f.setSalario(3751.05);
        assertEquals(FaixaIrpf.TERCEIRA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa3_3() {
        f.setSalario(3751.05);
        assertEquals("207,86", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa4() {
        f.setSalario(3751.06);
        assertEquals(FaixaIrpf.QUARTA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa4() {
        f.setSalario(3751.06);
        assertEquals("207,86", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa4_2() {
        f.setSalario(4000);
        assertEquals(FaixaIrpf.QUARTA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa4_2() {
        f.setSalario(4000);
        assertEquals("263,87", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa4_3() {
        f.setSalario(4664.68);
        assertEquals(FaixaIrpf.QUARTA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa4_3() {
        f.setSalario(4664.68);
        assertEquals("413,42", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa5() {
        f.setSalario(4664.69);
        assertEquals(FaixaIrpf.QUINTA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa5() {
        f.setSalario(4664.69);
        assertEquals("413,42", df.format(f.calcularIrpf()));
    }

    @Test
    public void faixa5_2() {
        f.setSalario(5000);
        assertEquals(FaixaIrpf.QUINTA, f.identificarFaixaIrpf());
    }

    @Test
    public void impostoFaixa5_2() {
        f.setSalario(5000);
        assertEquals("505,64", df.format(f.calcularIrpf()));
    }

    /*@Test
    public void testeNegativo() {
        f.setSalario(-100);
    }*/
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
