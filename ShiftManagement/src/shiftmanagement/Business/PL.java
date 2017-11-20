/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import java.time.LocalTime;

/**
 *
 * @author Tiago
 */
public class PL extends Turno{
    
    private int maxAlunos;
    
    public PL(){
        super();
    }
    
    public PL(String id, int max, Sala s, Professor p, LocalTime l){
        super(id, s, p, l);
        this.maxAlunos = max;
    }
    
    
}
