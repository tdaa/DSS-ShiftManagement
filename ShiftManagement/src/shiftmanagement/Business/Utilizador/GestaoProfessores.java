/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

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
    
    public String getEmail(String nome){
        return this.listaProfessores.getEmailProf(nome);
    }
    
    public String getProf(String username){
        return this.listaProfessores.get(username).getNome();
    }
}
