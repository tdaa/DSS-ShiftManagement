/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

/**
 *
 * @author Tiago
 */
public class Troca {
    
    private int id;
    private String idTurnoInicial;
    private String idTurnoFinal;
    private String codigoUC;
    
    public Troca(){
        this.id=0;
        this.idTurnoFinal = "";
        this.idTurnoInicial = "";
        this.codigoUC = "";
    }
    
    public Troca(int id, String ti, String tf, String uc){
        this.id = id;
        this.idTurnoFinal = tf;
        this.idTurnoInicial = ti;
        this.codigoUC = uc;
    }
    
    public Troca(String ti, String tf, String uc){
        this.id = id+1;
        this.setId(id+1);
        this.idTurnoInicial = ti;
        this.idTurnoFinal = tf;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
