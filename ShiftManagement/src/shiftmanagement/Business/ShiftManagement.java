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
    
    
   
    
    public ShiftManagement(){
        aluno = null;
        professor = null;
        admin = null;
    }
    
    public ShiftManagement(ShiftManagement s){
        aluno = s.getAluno();
        professor = s.getProfessor();
        admin = s.getAdmin();
    }
    
    public ShiftManagement(Aluno a, Professor p, Admin ad){
        aluno = a;
        professor = p;
        admin = ad;
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
    
    public String getNomeProf(String username){
        return this.listaProfs.getProf(username);
    }
    
    public String getEmailProf(String nome){
        String email = this.listaProfs.getEmail(nome);
        return email;
    }
    
    public String getUsernameProfessor(String prof){
        String username = this.listaProfs.getProf(prof);
        return username;
    }
  

}
