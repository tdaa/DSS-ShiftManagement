/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.database.UcCompDAO;

/**
 *
 * @author Tiago
 */
public class GestaoUCsComplementares {
    
    private UcCompDAO listaUCs;
    
    public GestaoUCsComplementares(){
        this.listaUCs = new UcCompDAO();
    }
    
    public ArrayList<String> getNomeUcs(){
        return this.listaUCs.getNomesUcs();
    }
    
}
