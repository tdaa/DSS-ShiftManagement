/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.HashSet;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class UC {
    
    private String nome;
    private String codigo;
    private String usernameResponsavel;
    private HashSet<Turno> turnos;
    private HashSet<Professor> equipaDocente;
    
    public UC(){
        this.nome = "";
        this.codigo = "";
        this.usernameResponsavel = null;
        this.turnos = new HashSet<>();
        this.equipaDocente = new HashSet<>();
    }
    
    public UC(String n, String cod, String p, HashSet<Professor> equipaDocente){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = p;
        this.equipaDocente = equipaDocente;
    }
    
    public UC(String n, String cod, String p, HashSet<Turno> turnos, HashSet<Professor> docentes){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = p;
        this.turnos = turnos;
        this.equipaDocente = docentes;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getResponsavel(){
        return this.usernameResponsavel;
    }
    
    public HashSet<Turno> getTurnos(){
       HashSet<Turno> lista = new HashSet<>();
       for(Turno t : this.turnos)
           lista.add(t);
       return lista;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
     public HashSet<Professor> getEquipaDocente(){
        HashSet<Professor> lista = new HashSet<>();
        for(Professor p : this.equipaDocente)
            lista.add(p);
        return lista;
    }
     
    public void setNome(String n){
        this.nome = n;
    }
    
    public void setCodigo(String c){
        this.codigo = c;
    }
    
    public void setResponsavel(String p){
        this.usernameResponsavel = p;
    }
    
    public void setTurnos(HashSet<Turno> t){
        this.turnos = t;
    }
    
    public void setDocentes(HashSet<Professor> profs){
        this.equipaDocente = profs;
    }
     
    public void addTurno(Turno t){
        this.turnos.add(t);
    }
    
    public Turno getTurno(String turno){
        for(Turno t: this.getTurnos()){
            if(t.getId().equals(turno)) return t;
        }
        return null;
    }
}
