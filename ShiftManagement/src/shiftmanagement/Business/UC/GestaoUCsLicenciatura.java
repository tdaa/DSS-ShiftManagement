/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.database.UcLicDAO;

/**
 *
 * @author Tiago
 */
public class GestaoUCsLicenciatura {
       
    private UcLicDAO listaUCs;
    
    public GestaoUCsLicenciatura(){
        listaUCs = new UcLicDAO();
    }
    
    public ArrayList<String> getNomesUCs(){
        return this.listaUCs.getNomesUcs();
    }
    
    public String getCodigoUC(String nome){
        return this.listaUCs.getCodigo(nome);
    }
    
    public ArrayList<String> getListaProfs(String codigoUC){
        return this.listaUCs.getProfs(codigoUC);
    }
    
    public ArrayList<String> getListaTurnos(String codigoUC){
        return this.listaUCs.getTurnos(codigoUC);
    }
    
    
}
