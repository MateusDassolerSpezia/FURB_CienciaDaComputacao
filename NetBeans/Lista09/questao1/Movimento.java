/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author mdspezia
 */
public class Movimento {
    
    private double valor;
    private TipoMovimento tipoMovimento;
    private LocalDate data;
    private LocalTime hora;
    
    
    public Movimento(double valor, TipoMovimento tipo) {
    }

    public double getValor() {
        return valor;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public LocalDate getData() {
        return LocalDate.now();
    }

    public LocalTime getHora() {
        return LocalTime.now();
    }
    
}
