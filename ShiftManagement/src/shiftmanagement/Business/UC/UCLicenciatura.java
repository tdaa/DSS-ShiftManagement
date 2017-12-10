/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;


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
    
    public UCLicenciatura(String nome, String cod, String p, HashSet<Professor> listaProfs, HashSet<Turno> listaTurnos){
        super(nome, cod, p, listaTurnos, listaProfs);
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
   
    
    
}
