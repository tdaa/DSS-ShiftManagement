/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.util.ArrayList;
import shiftmanagement.database.ProfessorDAO;

/**
 *
 * @author Tiago
 */
public class GestaoProfessores {
    
    private ProfessorDAO listaProfessores;
    
    public GestaoProfessores(){
    }
    
    public Professor verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(this.listaProfessores.containsKey(username)){
            if(this.listaProfessores.get(username).getPass().equals(pass)){
                return this.listaProfessores.get(username);
            }
            else{
               throw new PassErradaException("Password Incorreta!");
            }
        }
        else{
            throw new UsernameErradoException("Username Errado!");
        }
    }
    
    public ArrayList<String> getTodos(){
        ArrayList<String> res = new ArrayList<>();
        for(Professor p: this.listaProfessores.values()){
            res.add(p.getUsername() + " - " + p.getNome());
        }
        return res;
    }
    
    public String getEmail(String username){
        return this.listaProfessores.get(username).getMail();
    }
    
    public String getProfNome(String username){
        return this.listaProfessores.get(username).getNome();
    }
    
    public Professor getProf(String username){
        return this.listaProfessores.get(username);
    }
   
    public Professor getProfByUsername(String user){
        for(Professor p: this.listaProfessores.values()){
            if(p.getUsername().equals(user)) return p;
        }
        return null;
    }
}
