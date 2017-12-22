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
    
    private int id=0;
    private LocalDate data;
    private String idTurno;
    private String codigoUC;
    
    public Falta(){
        this. id = 0;
        this.data = null;
        this.idTurno = null;
        this.codigoUC = null;
    }
    
    public Falta(int id, LocalDate data, String idTurno, String codigoUC) {
        this.id = id;
        this.data = data;
        this.idTurno = idTurno;
        this.codigoUC = codigoUC;
    }
    
    public Falta(LocalDate data, String idTurno, String codigoUC) {
        this.setId(id + 1);
        id++;
        this.data = data;
        this.idTurno = idTurno;
        this.codigoUC = codigoUC;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public String getCodigoUC() {
        return codigoUC;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public void setCodigoUC(String codigoUC) {
        this.codigoUC = codigoUC;
    }
    
}
