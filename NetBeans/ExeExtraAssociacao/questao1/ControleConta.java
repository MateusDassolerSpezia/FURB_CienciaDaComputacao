/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author mdspezia
 */
public class ControleConta {
//    private ContaLuz[] contas = new ContaLuz[100];
    private ArrayList<ContaLuz> contas;
    
    public ControleConta() {
//        contas = new ArrayList<ContaLuz>();
        contas = new ArrayList();
    }
    
    //substitui o método set de um atributo unitário
    public void inserirConta(ContaLuz conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta invalida");
        }
        //Sempre adiciona o dado no final da lista
        contas.add(conta);
    }
    
    public ContaLuz excluirConta(int numeroConta) {
/*      ContaLuz[] listaVetor = new ContaLuz[100];
        for (int i = 0; i < listaVetor.length; i++) {
            if (listaVetor[i].getNumLeitura() == numeroConta) {
                //....
                break;
            }
        } 
*/        
        ContaLuz contaExcluir = null;
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNrLeitura() == numeroConta) {
                contaExcluir = contas.get(i);
                break;
            }
        }
        if (contaExcluir != null) {
            contas.remove(contaExcluir);
        }
        return contaExcluir;
    }
    
    public ContaLuz maiorConsumo() {
        float valorMaior = 0;
        ContaLuz contaMaior = null;
        
        for (int i = 0; i < contas.size(); i++) {
            if (valorMaior < contas.get(i).getMediaConsumo()) {
                valorMaior = contas.get(i).getMediaConsumo();
                contaMaior = contas.get(i);
            }
        }
        return contaMaior;
    }
    
    public String listarDados() {
        String dados = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (int i = 0; i < contas.size(); i++) {
            ContaLuz c = contas.get(i);
            dados += sdf.format(c.getDataLeitura()) + "\n" + c.getNrLeitura() + "\n" + c.getKwGasto() + "\n" + c.getValorPagar() + "\n" + sdf.format(c.getDataPagto()) + "\n" + c.getMediaConsumo();
            dados += "\n\n";
        }
        return dados;
    }
}
