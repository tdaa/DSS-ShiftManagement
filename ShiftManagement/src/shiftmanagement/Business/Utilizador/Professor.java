/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.util.ArrayList;
import shiftmanagement.Business.Turno.Turno;

/**
 *
 * @author Tiago
 */
public class Professor extends Utilizador{
    
   private ArrayList<Turno> turnos;
    
   public Professor(){
       super();
       this.turnos = new ArrayList<>();
   }
   
   public Professor(String user, String nome, String mail, String pass){
       super(user, nome, mail, pass);
       this.turnos = new ArrayList<>();
   }
  
   
   public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> res = new ArrayList<Turno>();
       for(Turno t: this.turnos)
           res.add(t);
       return res;
   }
   
   public void adicionarTurno(Turno t){
       this.turnos.add(t);
   }
    
}
