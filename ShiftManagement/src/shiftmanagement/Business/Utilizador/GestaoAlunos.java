/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.util.ArrayList;
import java.util.Iterator;
import shiftmanagement.Business.Turno.Troca;
import shiftmanagement.database.AlunoDAO;

/**
 *
 * @author Tiago
 */
public class GestaoAlunos {
    
    private AlunoDAO listaAlunos;
    
    /**
     *
     */
    public GestaoAlunos(){
        this.listaAlunos = new AlunoDAO();
    }
    
    /**
     *
     * @param a
     */
    public void addAluno(Aluno a){
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    /**
     *
     * @param username
     * @param pass
     * @return
     * @throws UsernameErradoException
     * @throws PassErradaException
     */
    public Aluno verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(this.listaAlunos.containsKey(username)){
            if(this.listaAlunos.get(username).getPass().equals(pass)){
                return this.listaAlunos.get(username);
            }
            else{
               throw new PassErradaException("Password Incorreta!");
            }
        }
        else{
            throw new UsernameErradoException("Username Errado!");
        }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getAsList(){
        ArrayList<String> res = new ArrayList<>();
        this.listaAlunos.values().forEach((a) -> {
            res.add(a.getNome() + " - " + a.getUsername());
        });
        return res;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Aluno getAluno(String username){
        return this.listaAlunos.get(username);
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getHorario(String username){
        return this.listaAlunos.get(username).getHorario();
        
    }
    
    /**
     *
     * @param nomeUC
     * @param userAluno
     * @return
     */
    public String getTurnoDeUc(String nomeUC, String userAluno){
        Aluno a = this.listaAlunos.get(userAluno);
        String uc;
        String turno = null;
        boolean flag = false;
        Iterator<String> it = a.getHorario().iterator();
        String s;
        while(it.hasNext() && !flag){
            s = it.next();
            uc = s.substring(0, s.indexOf("-")-1);
            if(uc.equals(nomeUC)){
                flag = true;
                turno = s.substring(s.indexOf("-")+2, s.length());
            }
        }
        return turno;
    }
    
    /**
     *
     * @param aluno
     * @param codigouc
     * @param nomeuc
     * @param turnoF
     */
    public void insereTroca(String aluno, String codigouc, String nomeuc, String turnoF){
        Aluno a = this.listaAlunos.get(aluno);
        String turnoI = a.getTurnoUC(nomeuc);
        Troca t = new Troca(turnoI, turnoF, codigouc);
        if(!this.existeTroca(a, t.getCodigoUC(), t.getIdTurnoFinal(), t.getIdTurnoInicial())){
            a.addTroca(t);
            this.listaAlunos.put(a.getUsername(), a);
        }
        
    }
    
    public boolean existeTroca(Aluno a, String uc, String tf, String ti){
        for(Troca t: a.getTrocas()){
            if(t.getCodigoUC().equals(uc) && t.getIdTurnoFinal().equals(tf) && t.getIdTurnoInicial().equals(ti)){
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param aluno
     * @param codigouc
     * @param turnoI
     * @param turnoF
     */
    public void remTroca(String aluno, String codigouc, String turnoF, String turnoI){
        Aluno a = this.listaAlunos.get(aluno);
        Troca t = a.getTroca(codigouc);
        if(t != null) {
            a.removTroca(t);
            this.listaAlunos.put(a.getUsername(), a);
        }
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getTodasTrocas(){
        String uc, turno;
        ArrayList<String> res = new ArrayList<>();
        for(Aluno a: this.listaAlunos.values()){
            for(Troca t: a.getTrocas())
                res.add(a.getUsername() + " - " + t.getCodigoUC() + " - f " + t.getIdTurnoFinal() + " - i " + t.getIdTurnoInicial());
        }
        return res;
    }
    
    /**
     *
     * @param username
     * @param nova
     */
    public void mudaPassAluno(String username, String nova){
        Aluno a = this.listaAlunos.get(username);
        a.setPassword(nova);
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    /**
     *
     * @param username
     * @param nova
     */
    public void mudaEmailAluno(String username, String nova){
        Aluno a = this.listaAlunos.get(username);
        a.setMail(nova);
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    /**
     *
     * @param l
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getFaltas(ArrayList<String> l, String codigoUC){
        int nfaltas=0;
        ArrayList<String> res = new ArrayList<>();
        for(Aluno a: this.listaAlunos.values()){
            for(String s: l){
                if(a.getUsername().equals(s)){
                    nfaltas = this.listaAlunos.get(a.getUsername()).getTotalFaltas(codigoUC);
                    res.add(a.getUsername() + " - " + Integer.toString(nfaltas));
                }
            }
        }
        return res;
    }
    
    /**
     *
     * @param aluno
     * @param uc
     * @param turno
     */
    public void setFalta(String aluno, String uc, String turno){
        Aluno a = this.listaAlunos.get(aluno).setFaltas(uc, turno);
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    /**
     *
     * @param uc
     * @param aluno
     * @param codigoUC
     */
    public void removeAluno_UC(String uc, String aluno, String codigoUC){
        Aluno a = this.listaAlunos.get(aluno).removeDeUC(uc);
        a.removeDeFaltas(codigoUC);
        this.listaAlunos.put(a.getUsername(), a);
    }
}
