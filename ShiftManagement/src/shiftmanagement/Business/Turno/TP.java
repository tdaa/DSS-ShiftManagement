/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import java.time.LocalTime;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class TP extends Turno{
    
    private int maxAlunos;
    
    public TP(){
        super();
        maxAlunos = 0;
    }
    
    public TP(String id, int max, Sala s, Professor p, LocalTime l){
        super(id, s, p, l);
        this.maxAlunos = max;
    }
    
    public int getMax(){
        return this.maxAlunos;
    }
}
