/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;
import java.time.LocalTime;
import shiftmanagement.Business.Turno.Sala;

/**
 *
 * @author Tiago
 */
public class TP extends Turno{
    
    private int maxAlunos;
    
    public TP(){
        super();
    }
    
    public TP(String id, int max, Sala s, Professor p, LocalTime l){
        super(id, s, p, l);
        this.maxAlunos = max;
    }
}
