/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

import java.sql.Time;
import java.time.LocalTime;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class Teorica extends Turno{
    
    public Teorica(){
        super();
    }
    
    /**
     *
     * @param n
     * @param s
     * @param p
     * @param l
     * @param uc
     * @param na
     * @param dia
     * @param n
     */
    public Teorica(String n, Sala s, String p, Time l, String uc, int na, String dia){
        super(n, s, p, l, uc, na, dia);
    }
    
}
