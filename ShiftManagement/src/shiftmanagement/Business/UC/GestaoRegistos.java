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
    
    /**
     *
     * @param codigoUC
     * @param userAluno
     */
    public void removeAluno_UC(String codigoUC, String userAluno){
        for(Registo r: this.registos.values()){
            if(r.getIdAluno().equals(userAluno) && r.getIdUC().equals(codigoUC)){
                this.registos.remove(r.getId());
            }
        }
    }
    
    /**
     *
     * @param aluno
     * @param turno
     * @param uc
     */
    public void removeRegisto(String aluno, String turno, String uc){
        for(Registo r: this.registos.values()){
            if(r.getIdAluno().equals(aluno) && r.getIdTurno().equals(turno) && r.getIdUC().equals(uc)){
                this.registos.remove(r.getId());
                break;
            }
        }
    }
    
    /**
     *
     * @param aluno
     * @param turno
     * @param uc
     */
    public void novoRegisto(String aluno, String turno, String uc){
        Registo r = new Registo(id, aluno, uc, turno);
        this.registos.put(id, r);
    }
    
    /**
     *
     * @param codigoUC
     * @param turno
     * @return
     */
    public ArrayList<String> getAlunosEmTurnoUc(String codigoUC, String turno){
        ArrayList<String> res = new ArrayList<>();
        for(Registo r: this.registos.values()){
            if(r.getIdUC().equals(codigoUC) && r.getIdTurno().equals(turno)){
                res.add(r.getIdAluno());
            }
        }
        return res;
    }
}
