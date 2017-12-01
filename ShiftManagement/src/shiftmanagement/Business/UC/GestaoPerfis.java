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
    
    private PerfilDAO listaPerfis;
    
    public GestaoPerfis(){
        this.listaPerfis = new PerfilDAO();
    }
    
    public ArrayList<String> getNomePerfis(){
        return this.listaPerfis.getPerfisPorNome();
    }
}
