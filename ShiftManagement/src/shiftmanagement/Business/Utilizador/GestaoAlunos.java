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
    
    public GestaoAlunos(){
        this.listaAlunos = new AlunoDAO();
    }
    
    public void addAluno(Aluno a){
        this.listaAlunos.put(a.getUsername(), a);
    }
    
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
    
    public ArrayList<String> getAsList(){
        ArrayList<String> res = new ArrayList<>();
        this.listaAlunos.values().forEach((a) -> {
            res.add(a.getNome() + " - " + a.getUsername());
        });
        return res;
    }
    
    public Aluno getAluno(String username){
        return this.listaAlunos.get(username);
    }
    
    public ArrayList<String> getHorario(String username){
        return this.listaAlunos.get(username).getHorario();
        
    }
    
    public String getTurnoDeUc(String nomeUC, String userAluno){
        Aluno a = this.listaAlunos.get(userAluno);
        String uc;
        String turno = null;
        boolean flag = false;
        Iterator<String> it = a.getHorario().iterator();
        String s;
        while(it.hasNext() && !flag){
            s = it.next();
            uc = s.substring(0, s.indexOf(" "));
            if(uc.equals(nomeUC)){
                flag = true;
                turno = s.substring(s.indexOf("-")+2, s.length()-1);
            }
        }
        return turno;
    }
    
    public void insereTroca(String aluno, String codigouc, String nomeuc, String turnoF){
        Aluno a = this.listaAlunos.get(aluno);
        String turnoI = a.getTurnoUC(nomeuc);
        Troca t = new Troca(turnoI, turnoF, codigouc);
        a.addTroca(t);
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    public ArrayList<String> getTodasTrocas(){
        String uc, turno;
        ArrayList<String> res = new ArrayList<>();
        for(Aluno a: this.listaAlunos.values()){
            for(Troca t: a.getTrocas())
                res.add(a.getUsername() + " - " + t.getCodigoUC() + " - f " + t.getIdTurnoFinal() + " - i " + t.getIdTurnoInicial());
        }
        return res;
    }
    
    public void mudaPassAluno(String username, String nova){
        Aluno a = this.listaAlunos.get(username);
        a.setPassword(nova);
        this.listaAlunos.put(a.getUsername(), a);
    }
    
    public void mudaEmailAluno(String username, String nova){
        Aluno a = this.listaAlunos.get(username);
        a.setMail(nova);
        this.listaAlunos.put(a.getUsername(), a);
    }
}
