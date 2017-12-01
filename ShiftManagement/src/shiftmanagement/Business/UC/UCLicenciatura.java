/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Turno.Turno;

/**
 *
 * @author Tiago
 */
public class UCLicenciatura extends UC{
    
    
    public UCLicenciatura(){
        super();
    }
    
    public UCLicenciatura(int id, String nome, String cod, Professor p, ArrayList<Professor> listaProfs, ArrayList<Turno> listaTurnos){
        super(id, nome, cod, p, listaTurnos, listaProfs);
    }
   
    
    
    
}
