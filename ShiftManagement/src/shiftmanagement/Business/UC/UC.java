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
    private Professor responsavel;
    private ArrayList<Turno> turnos;
    private HashSet<Professor> equipaDocente;
    
    public UC(){
        this.nome = "";
        this.codigo = "";
        this.responsavel = null;
        this.turnos = new ArrayList<>();
        this.equipaDocente = new HashSet<>();
    }
    
    public UC(String n, String cod, Professor p, HashSet<Professor> equipaDocente){
        this.nome = n;
        this.codigo = cod;
        this.responsavel = p;
        this.equipaDocente = equipaDocente;
    }
    
    public UC(String n, String cod, Professor p, ArrayList<Turno> turnos, HashSet<Professor> docentes){
        this.nome = n;
        this.codigo = cod;
        this.responsavel = p;
        this.turnos = turnos;
        this.equipaDocente = docentes;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public Professor getResponsavel(){
        return this.responsavel;
    }
    
    public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> lista = new ArrayList<>();
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
