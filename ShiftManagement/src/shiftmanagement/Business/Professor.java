/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class Professor extends Pessoa{
    
   private ArrayList<Turno> turnos;
    
   public Professor(){
       super();
   }
   
   public Professor(String user, String nome, String numero, String mail, String pass){
       super(user, nome, numero, mail, pass);
   }
   
   public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> res = new ArrayList<Turno>();
       for(Turno t: this.turnos)
           res.add(t);
       return res;
   }
   
   public Professor verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(this.getUser().equals(username)){
            if(this.getPass().equals(pass)){
                return this;
            }
            else{
               throw new PassErradaException("Password Incorreta!");
            }
        }
        else{
            throw new UsernameErradoException("Username Errado!");
        }
    }
   
   public void adicionarTurno(Turno t){
       this.turnos.add(t);
   }
    
}
