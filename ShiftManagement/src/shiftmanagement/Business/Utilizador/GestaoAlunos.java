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
}
