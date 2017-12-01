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
public class UCComplementar extends UC{
    
    private String diaSemana;
    private String per;
    
    public UCComplementar(){
        super();
        this.diaSemana = "";
        this.per = "";
    }
    
    public UCComplementar(String nome, int id, String cod, Professor p, String diaSemana, String per, ArrayList<Turno> turnos, ArrayList<Professor> profs){
        super(id, nome, cod, p, turnos, profs);
        this.diaSemana = diaSemana;
        this.per = per;
    }
    
    
}
