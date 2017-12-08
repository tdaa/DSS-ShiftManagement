/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class GestaoPerfis {
    
    private ArrayList<Perfil> listaPerfis;
    
    public GestaoPerfis(){
        this.listaPerfis = new ArrayList<>();
    }
    
    public ArrayList<String> getNomePerfis(){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.forEach((p) -> {
            res.add(p.getNome());
    });
        return res;
    }
}
