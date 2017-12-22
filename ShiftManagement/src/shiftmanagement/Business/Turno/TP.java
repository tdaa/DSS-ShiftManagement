/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import java.sql.Time;

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
    
    public TP(String id, int max, Sala s, String p, Time l, String uc, int n, String dia){
        super(id, s, p, l, uc, n, dia);
        this.maxAlunos = max;
    }
    
    public int getMax(){
        return this.maxAlunos;
    }
    
    public void setMax(int m){
        this.maxAlunos = m;
    }
}
