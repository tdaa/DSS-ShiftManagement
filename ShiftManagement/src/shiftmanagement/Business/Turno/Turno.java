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
    private String professor;
    private String codigoUC;
    private Time hora;
    private Sala sala;
    private int numeroAulas;
    private String diaSemana;
    
    
    public Turno(){
        this.id = "";
        this.sala = null;
        this.professor = "";
        this.hora = null;
        this.codigoUC = null;
        this.numeroAulas = 0;
        this.diaSemana = null;
    }

    public Turno(String id, String codigoUC, Time hora, int numeroAulas, String diaSemana) {
        this.id = id;
        this.codigoUC = codigoUC;
        this.hora = hora;
        this.numeroAulas = numeroAulas;
        this.diaSemana = diaSemana;
    }   
    
    public Turno(String id, Sala s, String p, Time l, String uc, int n, String diaS){
        this.id = id;
        this.sala = s;
        this.professor = p;
        this.hora = l;
        this.codigoUC = uc;
        this.numeroAulas = n;
        this.diaSemana = diaS;
    }
    
    public Turno(String uc, String id, Time t, String dia){
        this.id = id;
        this.codigoUC = uc;
        this.hora = t;
        this.diaSemana = dia;
        this.professor="";
        this.sala = null;
    }
    
    
    
    public String getDia(){
        return this.diaSemana;
    }

    public int getNumeroAulas() {
        return numeroAulas;
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

    public void setNumeroAulas(int numeroAulas) {
        this.numeroAulas = numeroAulas;
    }
    
    public void setDia(String s){
        this.diaSemana = s;
    }
    
    
}
