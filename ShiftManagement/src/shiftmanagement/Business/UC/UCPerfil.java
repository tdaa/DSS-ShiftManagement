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
    
    /**
     *
     * @param diaSemana
     * @param id
     * @param nome
     * @param cod
     * @param p
     * @param diaSemana
     * @param turnos
     */
    public UCPerfil(String diaS, String nome, String cod, String p, ArrayList<Turno> turnos, HashSet<Professor> docentes){
        super(nome, cod, p, turnos, docentes);
        this.diaSemana = diaS;
    }
}
