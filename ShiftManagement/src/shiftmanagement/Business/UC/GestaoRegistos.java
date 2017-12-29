/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.database.RegistoDAO;

/**
 *
 * @author Tiago
 */
public class GestaoRegistos {
    
    private int id;
    private RegistoDAO registos;
    
    /**
     *
     */
    public GestaoRegistos(){
        this.id=0;
        this.registos = new RegistoDAO();
    }
    
    /**
     *
     * @param codigoUC
     * @param userAluno
     */
    public void registaAlunoEmUc(String codigoUC, String userAluno){
        this.id = this.id + 1;
        Registo r = new Registo(this.id, userAluno, codigoUC, "TP0");      
        this.registos.put(this.id, r);
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getUcsDeAluno(String username){
        ArrayList<String> res = new ArrayList<>();
        this.registos.values().stream().filter((r) -> (r.getIdAluno().equals(username))).forEachOrdered((r) -> {
            res.add(r.getIdUC());
        });
        return res;
    }
    
    /**
     *
     * @param userAluno
     * @param codigos
     * @return
     */
    public ArrayList<String> getUcsNIAluno(String userAluno, ArrayList<String> codigos){
        ArrayList<String> fim = new ArrayList<>();
        ArrayList<String> ucsAluno = getUcsDeAluno(userAluno);
        boolean flag=false;
        if(ucsAluno.isEmpty()){
            return codigos;
        }
        for(int i=0; i<codigos.size(); i++){
            flag=false;
            for(int j=0; j<ucsAluno.size() && !flag; j++){
                if(ucsAluno.get(j).equals(codigos.get(i))){
                    flag = true;
                }
            }
            if(!flag){
                fim.add(codigos.get(i));
            }
        }
            
        return fim;
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
        this.removeRegisto(aluno, "TP0", uc);
        this.id = this.id + 1;
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
