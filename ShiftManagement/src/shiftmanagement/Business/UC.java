/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class UC {
    
    private String nome;
    private String codigo;
    private Professor responsavel;
    private ArrayList<Professor> equipaDocente;
    private ArrayList<Turno> turnos;
    
    public UC(){
        this.nome = "";
        this.codigo = "";
    }
    
    public UC(String nome, String cod, Professor p, ArrayList<Professor> listaProfs, ArrayList<Turno> listaTurnos){
        this.nome = nome;
        this.codigo = cod;
        this.responsavel = p;
        for(Professor pr: listaProfs)
            this.equipaDocente.add(pr);
        for(Turno t: listaTurnos)
            this.turnos.add(t);
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public Professor getResponsavel(){
        return this.responsavel;
    }
    
    public ArrayList<Professor> getEquipaDocente(){
        ArrayList<Professor> lista = new ArrayList<Professor>();
        for(Professor p : this.equipaDocente)
            lista.add(p);
        return lista;
    }
    
    public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> lista = new ArrayList<Turno>();
       for(Turno t : this.turnos)
           lista.add(t);
       return lista;
    }
    
    
    
}
