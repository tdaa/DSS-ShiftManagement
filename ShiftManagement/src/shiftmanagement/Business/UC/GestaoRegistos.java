/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import shiftmanagement.database.RegistoDAO;

/**
 *
 * @author Tiago
 */
public class GestaoRegistos {
    
    private RegistoDAO registos;
    
    public GestaoRegistos(){
        this.registos = new RegistoDAO();
    }
    
}
