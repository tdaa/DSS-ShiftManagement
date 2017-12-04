/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import shiftmanagement.Business.Utilizador.Aluno;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Utilizador.Admin;
import java.util.ArrayList;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.UC.GestaoUCsComplementares;
import shiftmanagement.Business.UC.GestaoUCsLicenciatura;
import shiftmanagement.Business.Utilizador.GestaoAlunos;
import shiftmanagement.Business.UC.GestaoPerfis;
import shiftmanagement.Business.Utilizador.GestaoProfessores;

/**
 *
 * @author Tiago
 */
public class ShiftManagement {

    private Aluno aluno;
    private Professor professor;
    private Admin admin;
    private GestaoUCsLicenciatura listaUCsLic;
    private GestaoUCsComplementares listaUCsComp;
    private GestaoPerfis listaPerfis;
    private GestaoAlunos listaAlunos;
    private GestaoProfessores listaProfs;
    private Curso curso;
    
    
    public ShiftManagement(){
        aluno = null;
        professor = null;
        admin = null;
        curso = null;
    }
    
    public ShiftManagement(ShiftManagement s){
        aluno = s.getAluno();
        professor = s.getProfessor();
        admin = s.getAdmin();
        curso = s.getCurso();
    }
    
    public ShiftManagement(Aluno a, Professor p, Admin ad, Curso c){
        aluno = a;
        professor = p;
        admin = ad;
        curso = c;
    }
    
    public Curso getCurso(){
        return this.curso;
    }
    
    public Aluno getAluno(){
        return aluno;
    }
    
    public Professor getProfessor(){
        return professor;
    }
    
    public Admin getAdmin(){
        return admin;
    }
    
    public void iniciaSessao(String username, String pass, int id) throws UsernameErradoException{
        try{
            if(id == 1){
                this.admin.verificaDados(username, pass);
            }
            if(id == 2){
                this.aluno.verificaDados(username, pass);
            }
            if(id == 3){
                this.professor.verificaDados(username, pass);
            }
        }
        catch(Exception e){
            throw new UsernameErradoException(e.getMessage());
        }
    }
    
    public String getCodigoUc(String nomeUc){
        String codigo = this.listaUCsLic.getCodigoUC(nomeUc);
        return codigo;
    }
    
    public String getProfUc(String codigoUc){
        String prof = this.listaUCsLic.get(codigoUc).getResponsavel().getNome();
        return prof;
    }
    
    public ArrayList<String> getListaProfs(String codigoUC){
        ArrayList<String> profs = new ArrayList<>();
        for(String s: this.listaUCsLic.getListaProfs(codigoUC)){
            profs.add(s);
        }
        return profs;
    }
    
    public ArrayList<String> getListaTurnos(String codigoUC){
        ArrayList<String> turnos = new ArrayList<>();
        for(String s: this.listaUCsLic.getListaTurnos(codigoUC)){
            turnos.add(s);
        }
        return turnos;
    }
    
    public ArrayList<String> getUcsLicPorNome(){
        ArrayList<String> ucs = new ArrayList<>();
        for(String s: this.listaUCsLic.getNomesUCs()){
            ucs.add(s);
        }
        return ucs;
    }
    
    public ArrayList<String> getPerfisPorNome(){
        ArrayList<String> perfis = new ArrayList<>();
        for(String s: this.listaPerfis.getNomePerfis()){
            perfis.add(s);
        }
        return perfis;
    }
    
    public ArrayList<String> getUcsComplementaresPorNome(){
        ArrayList<String> compl = new ArrayList<>();
        for(String s: this.listaUCsComp.getNomeUcs()){
            compl.add(s);
        }
        return compl;
    }
    
    public ArrayList<String> getListaNomeProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        for(String s: this.listaUCsLic.getNomesProfs(codigoUC)){
            res.add(s);
        }
        return res;
    }
    
    public String getNomeProf(String username){
        return this.listaProfs.getProfNome(username);
    }
    
    public Professor getProfNome(String nome){
        return this.listaProfs.getProfPorNome(nome);
    }
    
    public String getEmailProf(String usernameProf){
        String email = this.listaProfs.getEmail(usernameProf);
        return email;
    }
    
    public void setTipoCurso(String t){
        this.curso.setTipo(t);
    }
  
    public void addProfToUCLic(String prof, String codigoUC){
        Professor p = this.listaProfs.getProfPorNome(prof);
        this.listaUCsLic.addProf(p, codigoUC);
    }
    
    public void removeProfDeUc(String usernameProf, String codigoUC){
        Professor p = this.listaProfs.getProf(usernameProf);
        this.listaUCsLic.removeProf(p, codigoUC);
    }
    
    public Turno getTurno(String idTurno, String codigoUC){
        return this.listaUCsLic.getTurnoPorId(idTurno, codigoUC);
    }
    
    public int getUltimoTurno(String codigoUC, String tipoTurno){
        return this.listaUCsLic.getIdUltimoTurno(codigoUC, tipoTurno);
    }
    
    public boolean getTeorica(String codigoUC){
        return this.listaUCsLic.checkTeorica(codigoUC);
    }
    
    public void addTurno(Turno t, String codigoUC){
        this.listaUCsLic.addNovoTurno(t, codigoUC);
    }
    
    public void removeTurnoDeUc(String turno, String codigoUC){
        this.listaUCsLic.removeTurno(turno, codigoUC);
    }
    
    public void atualizaIdTurnos(String idTurno, String codigoUC){
        this.listaUCsLic.atualizaTurnos(idTurno, codigoUC);
    }
}
