/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
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
    private ArrayList<Professor> equipaDocente;
    
    public UC(){
        this.id = 0;
        this.nome = "";
        this.codigo = "";
        this.responsavel = null;
        this.turnos = new ArrayList<>();
        this.equipaDocente = new ArrayList<>();
    }
    
    public UC(int id, String n, String cod, Professor p, ArrayList<Turno> turnos, ArrayList<Professor> docentes){
        this.id = id;
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
       ArrayList<Turno> lista = new ArrayList<Turno>();
       for(Turno t : this.turnos)
           lista.add(t);
       return lista;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
     public ArrayList<Professor> getEquipaDocente(){
        ArrayList<Professor> lista = new ArrayList<Professor>();
        for(Professor p : this.equipaDocente)
            lista.add(p);
        return lista;
    }
}
