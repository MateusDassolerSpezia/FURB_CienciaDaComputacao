/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelVetor;

import modelLista.*;


/**
 *
 * @author Mateus
 */
public class FilaVaziaException extends RuntimeException {

    public FilaVaziaException() {
        super("fila está vazia");
    }
    
}
