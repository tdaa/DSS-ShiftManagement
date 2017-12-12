/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.HashSet;
import java.util.Iterator;
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
    
    public UC(String n, String cod){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = null;
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
    
    public void ripTurno(String turno){
        Turno t = getTurno(turno);
        this.getTurnos().remove(t);
    }
    
    public Turno getTurno(String turno){
        for(Turno t: this.getTurnos()){
            if(t.getId().equals(turno)) return t;
        }
        return null;
    }
    
    public void addProfToDocentes(Professor p){
        if(p!=null){
            this.getEquipaDocente().add(p);
        }
    }
    
    public void removeDeDocentes(Professor p){
        this.getEquipaDocente().remove(p);
    }
    
    public void corrigeIdTurnos(String turno){
        int idT;
        int id = Integer.parseInt(Character.toString(turno.charAt(turno.length())));
        Iterator<Turno> it = this.getTurnos().iterator();
        Turno t;
        while(it.hasNext()){
            t = it.next();
            if(t.getClass().equals("PL") || t.getClass().equals("TP")){
                idT = Integer.parseInt(Character.toString(t.getId().charAt(t.getId().length())));
                if(idT > id){
                    String newId = t.getId().substring(0, 1) + Integer.toString(id + (idT-id));
                    t.setId(newId);
                }
            }
        }
    }
}
