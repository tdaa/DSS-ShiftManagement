/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Tiago
 */
public class Troca {
    
    private String idTurnoInicial;
    private String idTurnoFinal;
    private String codigoUC;
    
    public Troca(){
        this.idTurnoFinal = "";
        this.idTurnoInicial = "";
        this.codigoUC = "";
    }
    
    public Troca(String ti, String tf, String uc){
        this.idTurnoFinal = tf;
        this.idTurnoInicial = ti;
        this.codigoUC = uc;
    }

    public String getIdTurnoInicial() {
        return idTurnoInicial;
    }

    public String getIdTurnoFinal() {
        return idTurnoFinal;
    }

    public String getCodigoUC() {
        return codigoUC;
    }


    public void setIdTurnoInicial(String idTurnoInicial) {
        this.idTurnoInicial = idTurnoInicial;
    }

    public void setIdTurnoFinal(String idTurnoFinal) {
        this.idTurnoFinal = idTurnoFinal;
    }

    public void setCodigoUC(String codigoUC) {
        this.codigoUC = codigoUC;
    }
    
    
    
    
    
}
