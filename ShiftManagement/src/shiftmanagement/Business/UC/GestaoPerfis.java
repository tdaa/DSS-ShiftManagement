/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.database.PerfilDAO;

/**
 *
 * @author Tiago
 */
public class GestaoPerfis {
    
    private ArrayList<Perfil> listaPerfis;
    
    public GestaoPerfis(){
        this.listaPerfis = new PerfilDAO();
    }
    
    public ArrayList<String> getNomePerfis(){
        ArrayList<String> res = new ArrayList<>();
            for(Perfil p: this.listaPerfis){
                res.add(p.getNome());
            }
        return res;
    }
}
