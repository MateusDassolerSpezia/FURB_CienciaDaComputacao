/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao1;

import java.util.Date;

/**
 *
 * @author mdspezia
 */
public class ContaLuz {
    private Date dataLeitura;
    private int nrLeitura;
    private int kwGasto;
    private float valorPagar;
    private Date dataPagto;
    private float mediaConsumo;

    public ContaLuz(Date dataLeitura, int nrLeitura, int kwGasto, float valorPagar, Date dataPagto, float mediaConsumo) {
        setDataLeitura(dataLeitura);
        setNrLeitura(nrLeitura);
        setKwGasto(kwGasto);        
        setValorPagar(valorPagar);
        setDataPagto(dataPagto); 
        setMediaConsumo(mediaConsumo);
    }

    public Date getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(Date dataLeitura) {
        if (dataLeitura == null) {
            throw new IllegalArgumentException("Data deve ser menor que data atual");
        }
        this.dataLeitura = dataLeitura;
    }

    public int getNrLeitura() {
        return nrLeitura;
    }

    public void setNrLeitura(int numLeitura) {
        if (numLeitura <= 0) {
            throw new IllegalArgumentException("Numero de leitura deve ser maior que zero");
        }
        this.nrLeitura = numLeitura;
    }

    public int getKwGasto() {
        return kwGasto;
    }

    public void setKwGasto(int kwGasto) {
        if (kwGasto < 0) {
            throw new IllegalArgumentException("A quantidade de Kw gasto nao pode ser menor que zero");
        }
        this.kwGasto = kwGasto;
    }

    public float getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(float valorPagar) {
        this.valorPagar = valorPagar;
    }

    public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
        this.dataPagto = dataPagto;
    }

    public float getMediaConsumo() {
        return mediaConsumo;
    }

    public void setMediaConsumo(float mediaConsumo) {
        this.mediaConsumo = mediaConsumo;
    }
    
}
