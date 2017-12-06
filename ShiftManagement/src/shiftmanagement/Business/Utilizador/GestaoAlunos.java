/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import shiftmanagement.database.AlunoDAO;

/**
 *
 * @author Tiago
 */
public class GestaoAlunos {
    
    private AlunoDAO listaAlunos;
    
    public GestaoAlunos(){
        this.listaAlunos = new AlunoDAO();
    }
    
    public Aluno verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(this.listaAlunos.containsKey(username)){
            if(this.listaAlunos.get(username).getPass().equals(pass)){
                return this.listaAlunos.get(username);
            }
            else{
               throw new PassErradaException("Password Incorreta!");
            }
        }
        else{
            throw new UsernameErradoException("Username Errado!");
        }
    }
}
