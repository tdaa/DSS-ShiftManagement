/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import static java.sql.Types.NULL;
import java.util.ArrayList;
import shiftmanagement.database.RegistoDAO;

/**
 *
 * @author Tiago
 */
public class GestaoRegistos {
    
    private int id=0;
    private RegistoDAO registos;
    
    /**
     *
     */
    public GestaoRegistos(){
        this.registos = new RegistoDAO();
    }
    
    /**
     *
     * @param codigoUC
     * @param userAluno
     */
    public void registaAlunoEmUc(String codigoUC, String userAluno){
        Registo r = new Registo(this.id, userAluno, codigoUC, null);
        this.registos.put(this.id, r);
        this.id++;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getUcsDeAluno(String username){
        ArrayList<String> res = new ArrayList<>();
        for(Registo r: this.registos.values()){
            if(r.getIdAluno().equals(username)){
                res.add(r.getIdUC());
            }
        }
        return res;
    }
    
    /**
     *
     * @param userAluno
     * @param codigos
     * @return
     */
    public ArrayList<String> getUcsNIAluno(String userAluno, ArrayList<String> codigos){
        ArrayList<String> ucsAluno = getUcsDeAluno(userAluno);
        codigos.forEach((s) -> {
            ucsAluno.stream().filter((uc) -> (uc.equals(s))).forEachOrdered((_item) -> {
                codigos.remove(s);
            });
        });
        return codigos;
    }
}
