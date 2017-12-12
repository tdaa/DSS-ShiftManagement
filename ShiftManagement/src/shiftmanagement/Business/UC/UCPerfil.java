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
public class UCPerfil extends UC{
    
    private String diaSemana;
    
    public UCPerfil(){
        super();
        this.diaSemana = "";
    }
    
    public UCPerfil(String nome, String cod, String diaS){
        super(nome, cod);
        this.diaSemana = diaS;
    }
    
    /**
     *
     * @param diaS
     * @param nome
     * @param cod
     * @param docentes
     * @param p
     * @param turnos
     */
    public UCPerfil(String diaS, String nome, String cod, String p, HashSet<Turno> turnos, HashSet<Professor> docentes){
        super(nome, cod, p, turnos, docentes);
        this.diaSemana = diaS;
    }
    
    public UCPerfil(String diaS, String nome, String cod, String p, HashSet<Professor> docentes){
        super(nome, cod, p, docentes);
        this.diaSemana = diaS;
    }
    
    public String getDiaS(){
        return this.diaSemana;
    }
    
    public void setDiaS(String s){
        this.diaSemana = s;
    }
}
