/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.time.LocalDate;

/**
 *
 * @author Tiago
 */
public class Falta {
    
    private String idTurno;
    private String codigoUC;
    
    public Falta(){
        this.idTurno = null;
        this.codigoUC = null;
    }
    
    public Falta(String idTurno, String codigoUC) {
        this.idTurno = idTurno;
        this.codigoUC = codigoUC;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public String getCodigoUC() {
        return codigoUC;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public void setCodigoUC(String codigoUC) {
        this.codigoUC = codigoUC;
    }
    
}
