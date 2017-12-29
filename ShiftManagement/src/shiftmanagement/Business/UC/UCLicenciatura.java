/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;


import java.util.HashSet;
import java.util.Iterator;
import shiftmanagement.Business.Turno.Teorica;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Turno.Turno;

/**
 *
 * @author Tiago
 */
public class UCLicenciatura extends UC{
    
    private String abreviatura;
    
    /**
     *
     */
    public UCLicenciatura(){
        super();
        this.abreviatura = "";

    }
    
    /**
     *
     * @param n
     * @param code
     * @param abr
     */
    public UCLicenciatura(String n, String code, String abr){
        super(n, code);
        this.abreviatura = abr;
    }
    
    /**
     *
     * @param n
     * @param cod
     * @param p
     * @param equipaDocente
     */
    public UCLicenciatura(String n, String cod, String p, HashSet<Professor> equipaDocente){
        super(n, cod, p, equipaDocente);
    }
    
    /**
     *
     * @param nome
     * @param cod
     * @param p
     * @param listaProfs
     * @param listaTurnos
     */
    public UCLicenciatura(String nome, String cod, String p, HashSet<Professor> listaProfs, HashSet<Turno> listaTurnos){
        super(nome, cod, p, listaTurnos, listaProfs);
    }
    
    /**
     *
     * @param idTurno
     * @return
     */
    public Turno getTurnoById(String idTurno){
        try{
            for(Turno t: this.getTurnos()){
                if(t.getId().equals(idTurno)) return t;
            }   
        }catch(Exception e){
        }
         return null;
    }
   
    /**
     *
     * @param tipoTurno
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public boolean existeTeorica(){
        for(Turno t: this.getTurnos()){
            if(t.getClass().equals("Teorica")) return true;
        }
        return false;
    }
    
    
}
