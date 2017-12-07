/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Turno.Turno;

/**
 *
 * @author Tiago
 */
public class UCLicenciatura extends UC{
    
    
    public UCLicenciatura(){
        super();
    }
    
    public UCLicenciatura(String n, String cod, String p, HashSet<Professor> equipaDocente){
        super(n, cod, p, equipaDocente);
    }
    
    public UCLicenciatura(String nome, String cod, String p, HashSet<Professor> listaProfs, ArrayList<Turno> listaTurnos){
        super(nome, cod, p, listaTurnos, listaProfs);
    }
    
    public void addProfToDocentes(Professor p){
        if(p!=null){
            this.getEquipaDocente().add(p);
        }
    }
    
    public void removeDeDocentes(Professor p){
        this.getEquipaDocente().remove(p);
    }
    
    public Turno getTurnoById(String idTurno){
        for(Turno t: this.getTurnos()){
            if(t.getId().equals(idTurno)) return t;
        }
        return null;
    }
   
    public int ultimoTurno(String tipoTurno){
        int res=0;
        String id;
        for(Turno t: this.getTurnos()){
            if(t.getClass().equals(tipoTurno)){
                id = Character.toString(t.getId().charAt(t.getId().indexOf(tipoTurno.charAt(tipoTurno.length()) + 1)));
                if(Integer.parseInt(id) > res){
                    res = Integer.parseInt(id);
                }
            } 
        }
        return res;
    }
    
    public boolean existeTeorica(){
        for(Turno t: this.getTurnos()){
            if(t.getClass().equals("T")) return true;
        }
        return false;
    }
    
    public void ripTurno(String turno){
        Turno t = this.getTurno(turno);
        this.getTurnos().remove(t);
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
