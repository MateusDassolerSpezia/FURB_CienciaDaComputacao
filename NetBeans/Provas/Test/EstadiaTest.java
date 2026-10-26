/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Test;

import java.text.DecimalFormat;
import model.Estadia;
import model.Periodo;
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
// Mateus Dassoler Spezia
public class EstadiaTest {

    private static Estadia e;
    private static DecimalFormat df;

    @BeforeAll
    public static void setUpClass() {
        e = new Estadia(Periodo.BAIXA_TEMPORADA, 10);
        df = new DecimalFormat("0.00");
    }

    @Test
    public void teste1() {
        e.setPeriodo(Periodo.ALTA_TEMPORADA);
        e.setQuantidadeDias(8);
        assertEquals("1411,20", df.format(e.precoPagar()));
    }

    @Test
    public void teste2() {
        e.setPeriodo(Periodo.MEDIA_TEMPORADA);
        e.setQuantidadeDias(1);
        assertEquals("157,50", df.format(e.precoPagar()));
    }

    @Test
    public void teste3() {
        e.setPeriodo(Periodo.BAIXA_TEMPORADA);
        e.setQuantidadeDias(5);
        assertEquals("525,00", df.format(e.precoPagar()));
    }

    @Test
    public void teste4() {
        e.setPeriodo(Periodo.BAIXA_TEMPORADA);
        e.setQuantidadeDias(1);
        assertEquals("110,25", df.format(e.precoPagar()));
    }

    @Test
    public void teste5() {
        e.setPeriodo(Periodo.MEDIA_TEMPORADA);
        e.setQuantidadeDias(3);
        assertEquals("450,00", df.format(e.precoPagar()));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
