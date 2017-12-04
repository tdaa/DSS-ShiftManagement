/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import shiftmanagement.Business.Utilizador.Professor;
import java.time.LocalTime;

/**
 *
 * @author Tiago
 */
public class Turno {
    
    private String id;
    private Sala sala;
    private Professor professor;
    private LocalTime hora;
    
    
    public Turno(){
        this.id = "";
    }
    
    public Turno(String id, Sala s, Professor p, LocalTime l){
        this.id = id;
        this.sala = s;
        this.professor = p;
        this.hora = l;
    }
    
    public String getId(){
        return this.id;
    }
    
    public Sala getSala(){
        return this.sala;
    }
    
    public Professor getProf(){
        return this.professor;
    }
    
    public LocalTime getHora(){
        return this.hora;
    }
    
    public void setId(String id){
        this.id = id;
    }
}
