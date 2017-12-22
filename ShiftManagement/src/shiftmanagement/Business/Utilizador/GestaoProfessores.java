/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.util.ArrayList;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.database.ProfessorDAO;

/**
 *
 * @author Tiago
 */
public class GestaoProfessores {
    
    private ProfessorDAO listaProfessores;
    
    public GestaoProfessores(){
        this.listaProfessores = new ProfessorDAO();
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
        return this.listaProfessores.get(user);
    }
    
    public ArrayList<String> getAsList(){
        ArrayList<String> res = new ArrayList<>();
        this.listaProfessores.values().forEach((a) -> {
            res.add(a.getNome() + " - " + a.getUsername());
        });
        return res;
    }
    
    public void addProf(Professor p){
        this.listaProfessores.put(p.getUsername(), p);
    }
    
    public ArrayList<String> getUcsTurnos(String username){
        ArrayList<String> res = new ArrayList<>();
        String nomeUC;
        for(Professor p: this.listaProfessores.values()){
            for(Turno t: p.getTurnos()){
                res.add(t.getUc() + " - " + t.getId());
            }
        }
        return res;
    }
}
