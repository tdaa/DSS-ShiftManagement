/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import java.sql.Time;


/**
 *
 * @author Tiago
 */
public class Turno {
    
    private String id;
    private Sala sala;
    private String professor;
    private Time hora;
    private String codigoUC;
    
    
    public Turno(){
        this.id = "";
        this.sala = null;
        this.professor = "";
        this.hora = null;
        this.codigoUC = null;
    }
    
    public Turno(String id, Sala s, String p, Time l, String uc){
        this.id = id;
        this.sala = s;
        this.professor = p;
        this.hora = l;
        this.codigoUC = uc;
    }
    
    public String getUc(){
        return this.codigoUC;
    }
    
    public String getId(){
        return this.id;
    }
    
    public Sala getSala(){
        return this.sala;
    }
    
    public String getProf(){
        return this.professor;
    }
    
    public Time getHora(){
        return this.hora;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setHora(Time h){
        this.hora = h;
    }
    
    public void setSala(Sala s){
        this.sala = s;
    }
    
    public void setProf(String p){
        this.professor = p;
    }
    
    public void setUc(String uc){
        this.codigoUC = uc;
    }
}
