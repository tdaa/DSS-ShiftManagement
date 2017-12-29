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
    
    /**
     *
     */
    public GestaoProfessores(){
        this.listaProfessores = new ProfessorDAO();
    }
    
    /**
     *
     * @param username
     * @param pass
     * @return
     * @throws UsernameErradoException
     * @throws PassErradaException
     */
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
    
    /**
     *
     * @return
     */
    public ArrayList<String> getTodos(){
        ArrayList<String> res = new ArrayList<>();
        for(Professor p: this.listaProfessores.values()){
            res.add(p.getUsername() + " - " + p.getNome());
        }
        return res;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public String getEmail(String username){
        return this.listaProfessores.get(username).getMail();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public String getProfNome(String username){
        return this.listaProfessores.get(username).getNome();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Professor getProf(String username){
        return this.listaProfessores.get(username);
    }
   
    /**
     *
     * @param user
     * @return
     */
    public Professor getProfByUsername(String user){
        return this.listaProfessores.get(user);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getAsList(){
        ArrayList<String> res = new ArrayList<>();
        this.listaProfessores.values().forEach((a) -> {
            res.add(a.getNome() + " - " + a.getUsername());
        });
        return res;
    }
    
    /**
     *
     * @param p
     */
    public void addProf(Professor p){
        this.listaProfessores.put(p.getUsername(), p);
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getUcsTurnos(String username){
        String uc, turno;
        ArrayList<String> res = new ArrayList<>();
        for(String s: this.listaProfessores.get(username).getTurnos()){
            uc = s.substring(0, s.indexOf("-")-1);
            turno = s.substring(s.indexOf("-")+2, s.length());
            res.add(uc + " - " + turno);
        }
        
        return res;
    }
    
    public void setRegente(String user){
        Professor p = this.listaProfessores.get(user);
        p.setRegente(true);
        this.listaProfessores.put(p.getUsername(), p);
    }
}
