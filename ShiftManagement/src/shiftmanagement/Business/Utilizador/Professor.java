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
    
   private String username; 
   private ArrayList<Turno> turnos;
    
   public Professor(){
       super();
       this.username = "";
   }
   
   public Professor(String user, String nome, String mail, String pass){
       super(nome, mail, pass);
       this.username = user;
   }
   
   public String getUserame(){
       return this.username;
   }
   
   public ArrayList<Turno> getTurnos(){
       ArrayList<Turno> res = new ArrayList<Turno>();
       for(Turno t: this.turnos)
           res.add(t);
       return res;
   }
   
   public Professor verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(this.username.equals(username)){
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
