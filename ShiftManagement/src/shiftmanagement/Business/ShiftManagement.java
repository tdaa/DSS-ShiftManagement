/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Utilizador.Admin;
import java.util.ArrayList;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.UC.GestaoUCsComplementares;
import shiftmanagement.Business.UC.GestaoUCsLicenciatura;
import shiftmanagement.Business.Utilizador.GestaoAlunos;
import shiftmanagement.Business.UC.GestaoPerfis;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.Utilizador.GestaoProfessores;
import shiftmanagement.Business.Utilizador.Utilizador;
import shiftmanagement.Business.Utilizador.*;

/**
 *
 * @author Tiago
 */
public class ShiftManagement {
    
    private Admin admin;
    private Utilizador utilizador;
    private GestaoUCsLicenciatura listaUCsLic;
    private GestaoUCsComplementares listaUCsComp;
    private GestaoPerfis listaPerfis;
    private GestaoAlunos listaAlunos;
    private GestaoProfessores listaProfs;
    private Curso curso;
    
    /**
     *
     */
    public ShiftManagement(){
        admin = null;
        utilizador = null;
        curso = null;
        this.listaUCsLic = new GestaoUCsLicenciatura();
        this.listaUCsComp = new GestaoUCsComplementares();
        this.listaAlunos = new GestaoAlunos();
        this.listaProfs = new GestaoProfessores();
        this.listaPerfis = new GestaoPerfis();
    }
    
    /**
     *
     * @param s
     */
    public ShiftManagement(ShiftManagement s){
        utilizador = s.getUtilizador();
        curso = s.getCurso();
    }
    
    /**
     *
     * @param u
     * @param c
     */
    public ShiftManagement(Utilizador u, Curso c){
        this.utilizador = u;
        curso = c;
    }
    
    /**
     *
     * @return
     */
    public Utilizador getUtilizador(){
        return this.utilizador;
    }
    
    /**
     *
     * @return
     */
    public Curso getCurso(){
        return this.curso;
    }
    
    /**
     *
     * @param username
     * @param pass
     * @param id
     * @throws shiftmanagement.Business.UsernameErradoException
     */
    public void iniciaSessao(String username, String pass, int id) throws UsernameErradoException{
        try{
            if(id == 1){
                this.utilizador = this.admin.verificaDados(username, pass);
            }
            if(id == 2){
                this.utilizador = this.listaAlunos.verificaDados(username, pass);
            }
            if(id == 3){
                this.utilizador = this.listaProfs.verificaDados(username, pass);
            }
        }
        catch(Exception e){
            throw new UsernameErradoException(e.getMessage());
        }
    }
    
    /**
     *
     * @param nomeUc
     * @return
     */
    public String getCodigoUc(String nomeUc){
        String codigo = this.listaUCsLic.getCodigoUC(nomeUc);
        return codigo;
    }
    
    /**
     *
     * @param codigoUc
     * @return
     */
    public String getProfUc(String codigoUc){
        String userprof = this.listaUCsLic.getStor(codigoUc);
        String res = this.listaProfs.getProfNome(userprof);
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getListaProfs(String codigoUC){
        ArrayList<String> profs = new ArrayList<>();
        this.listaUCsLic.getListaProfs(codigoUC).forEach((s) -> {
            profs.add(s);
        });
        return profs;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getListaTurnos(String codigoUC){
        ArrayList<String> turnos = new ArrayList<>();
        this.listaUCsLic.getListaTurnos(codigoUC).forEach((s) -> {
            turnos.add(s);
        });
        return turnos;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getUcsLicPorNome(){
        ArrayList<String> ucs = new ArrayList<>();
        this.listaUCsLic.get_Nomes_E_Cod_UCs().forEach((s) -> {
            ucs.add(s);
        });
        return ucs;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getPerfisPorNome(){
        ArrayList<String> perfis = new ArrayList<>();
        this.listaPerfis.getNomePerfis().forEach((s) -> {
            perfis.add(s);
        });
        return perfis;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getUcsComplementaresPorNome(){
        ArrayList<String> compl = new ArrayList<>();
        this.listaUCsComp.getNomeUcs().forEach((s) -> {
            compl.add(s);
        });
        return compl;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getListaNomeProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCsLic.getNomesProfs(codigoUC).forEach((s) -> {
            res.add(s);
        });
        return res;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getListaTodosProfs(){
        ArrayList<String> res = new ArrayList<>();
        this.listaProfs.getTodos().forEach((s) -> {
            res.add(s);
        });
        return res;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public String getNomeProf(String username){
        return this.listaProfs.getProfNome(username);
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Professor getProfPorUsername(String username){
        return this.listaProfs.getProfByUsername(username);
    }
    
    /**
     *
     * @param usernameProf
     * @return
     */
    public String getEmailProf(String usernameProf){
        String email = this.listaProfs.getEmail(usernameProf);
        return email;
    }
    
    /**
     *
     * @param t
     */
    public void setTipoCurso(String t){
        this.curso.setTipo(t);
    }
  
    /**
     *
     * @param prof
     * @param codigoUC
     */
    public void addProfToUCLic(String prof, String codigoUC){
        Professor p = this.listaProfs.getProf(prof);
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
    
    public void addUc(UCLicenciatura uc){
        this.listaUCsLic.addNovaUc(uc);
    }
    
    public void removeUcLic(String codigo){
        this.listaUCsLic.removeUc(codigo);
    }
}
