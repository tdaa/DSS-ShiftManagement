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
   
   private boolean regente;
   private ArrayList<Turno> turnos;
    
   public Professor(){
       super();
       this.regente = false;
       this.turnos = new ArrayList<>();
   }
   
   public Professor(String user, String nome, String mail, String pass){
       super(user, nome, mail, pass);
       this.regente = false;
       this.turnos = new ArrayList<>();
   }
   
   public boolean getRegente(){
       return this.regente;
   }
   
   public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> res = new ArrayList<>();
       for(Turno t: this.turnos)
           res.add(t);
       return res;
   }
   
   public void setRegente(boolean r){
       this.regente = r;
   }
   
   public void setTurnos(ArrayList<Turno> t){
       this.turnos = t;
   }
   
   public void adicionarTurno(Turno t){
       this.turnos.add(t);
   }
    
}
