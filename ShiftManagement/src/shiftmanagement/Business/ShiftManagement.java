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
import shiftmanagement.Business.UC.GestaoRegistos;
import shiftmanagement.Business.UC.Perfil;
import shiftmanagement.Business.UC.UCComplementar;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.UC.UCPerfil;
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
    private GestaoRegistos listaRegistos;
    private Curso curso;
    
    /**
     *
     */
    public ShiftManagement(){
        admin = new Admin();
        utilizador = new Utilizador();
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
                this.admin = this.admin.verificaDados(username, pass);
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
     * @param tipoUC
     * @param nomePerfil
     * @return
     */
    public String getProfUc(String codigoUc, int tipoUC, String nomePerfil){
        String userprof;
        if(tipoUC == 1) {
            userprof = this.listaUCsLic.getStor(codigoUc);
        }
        else{
            if(tipoUC == 2) userprof = this.listaUCsComp.getStor(codigoUc);
            else userprof = this.listaPerfis.getStor(codigoUc, nomePerfil);
        }
        String res = this.listaProfs.getProfNome(userprof);
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getListaProfs(String codigoUC, int tipoUC, String nomePerfil){
        ArrayList<String> profs = new ArrayList<>();
        if(tipoUC == 1) {
            this.listaUCsLic.getListaProfs(codigoUC).forEach((s) -> {
                profs.add(s);
            });
        }
        if(tipoUC == 2){
            this.listaUCsComp.getListaProfs(codigoUC).forEach((s) -> {
                profs.add(s);
            });
        }
        else{
            this.listaPerfis.getListaProfs(codigoUC, nomePerfil).forEach((s) -> {
                profs.add(s);
            });
        }
        return profs;
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getListaTurnos(String codigoUC, int tipoUC, String nomePerfil){
        ArrayList<String> turnos = new ArrayList<>();
        if(tipoUC == 1){
            this.listaUCsLic.getListaTurnos(codigoUC).forEach((s) -> {
                turnos.add(s);
            });
        }
        if(tipoUC == 2){
            this.listaUCsComp.getListaTurnos(codigoUC).forEach((s) -> {
                turnos.add(s);
            });
        }
        else{
            this.listaPerfis.getListaTurnos(codigoUC, nomePerfil).forEach((s) -> {
                turnos.add(s);
            });
        }
        return turnos;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getUcsLicPorCodENome(){
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
    public ArrayList<String> getUcsCompPorCodENome(){
        ArrayList<String> compl = new ArrayList<>();
        this.listaUCsComp.getNome_E_Cod_Ucs().forEach((s) -> {
            compl.add(s);
        });
        return compl;
    }
    
    public ArrayList<String> getUcsPerfil(String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.getUcs(nomePerfil).forEach((s) -> {
            res.add(s);
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoUC
     * @return
     */
    public ArrayList<String> getListaNomeProfs(String codigoUC, int tipoUC, String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        if(tipoUC == 1){
            this.listaUCsLic.getNomesProfs(codigoUC).forEach((s) -> {
                res.add(s);
            });
        }
        if(tipoUC == 2){
            this.listaUCsComp.getNomesProfs(codigoUC).forEach((s) -> {
                res.add(s);
            });
        }
        else{
            this.listaPerfis.getProfs(codigoUC, nomePerfil).forEach((s) -> {
                res.add(s);
            });
        }
        return res;
    }
    
    /**
     *
     * @return Lista de todos os professores.
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
     * @param a
     */
    public void addNovoAluno(Aluno a){
        this.listaAlunos.addAluno(a);
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
     * @return Professor dado um username.
     */
    public Professor getProfPorUsername(String username){
        return this.listaProfs.getProfByUsername(username);
    }
    
    /**
     *
     * @param usernameProf
     * @return Email de um professor.
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
        this.curso = new Curso(t);
    }
  
    /**
     *
     * @param prof
     * @param codigoUC
     * @param tipoUC
     */
    public void addProfToUC(String prof, String codigoUC, int tipoUC, String nomePerfil){
        Professor p = this.listaProfs.getProf(prof);
        if(tipoUC == 1) this.listaUCsLic.addProf(p, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.addProf(p, codigoUC);
        else this.listaPerfis.addProfToPerfil(p, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param usernameProf
     * @param codigoUC
     * @param tipoUC
     */
    public void removeProfDeUc(String usernameProf, String codigoUC, int tipoUC, String nomePerfil){
        Professor p = this.listaProfs.getProf(usernameProf);
        if(tipoUC == 1) this.listaUCsLic.removeProf(p, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.removeProf(p, codigoUC);
        else this.listaPerfis.removeProf(p, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param tipoUC
     * @return Turno com determinado id, de determinada uc.
     */
    public Turno getTurno(String idTurno, String codigoUC, int tipoUC, String nomePerfil){
        if(tipoUC == 1) return this.listaUCsLic.getTurnoPorId(idTurno, codigoUC);
        if(tipoUC == 2) return this.listaUCsComp.getTurnoPorId(idTurno, codigoUC);
        else return this.listaPerfis.getTurnoPorId(idTurno, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoTurno
     * @return Numero do turno com id mais alto.
     */
    public int getUltimoTurno(String codigoUC, String tipoTurno){
        return this.listaUCsLic.getIdUltimoTurno(codigoUC, tipoTurno);
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoUC
     * @return verifica se existe teorica numa dada uc.
     */
    public boolean getTeorica(String codigoUC, int tipoUC){
        if(tipoUC == 1) return this.listaUCsLic.checkTeorica(codigoUC);
        else return this.listaUCsComp.checkTeorica(codigoUC);
    }
    
    /**
     *
     * @param t
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     */
    public void addTurno(Turno t, String codigoUC, int tipoUC, String nomePerfil){
        if(tipoUC == 1) this.listaUCsLic.addNovoTurno(t, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.addNovoTurno(t, codigoUC);
        else this.listaPerfis.addNovoTurno(t, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     */
    public void removeTurnoDeUc(String turno, String codigoUC, int tipoUC, String nomePerfil){
        if(tipoUC == 1) this.listaUCsLic.removeTurno(turno, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.removeTurno(turno, codigoUC);
        else this.listaPerfis.removeTurno(turno, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     */
    public void atualizaIdTurnos(String idTurno, String codigoUC, int tipoUC, String nomePerfil){
        if(tipoUC == 1) this.listaUCsLic.atualizaTurnos(idTurno, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.atualizaTurnos(idTurno, codigoUC);
        else this.listaPerfis.atualizaTurnos(idTurno, codigoUC, nomePerfil);
    }
    
    /**
     *
     * @param uc
     */
    public void addUcLic(UCLicenciatura uc){
        this.listaUCsLic.addNovaUc(uc);
    }
    
    /**
     *
     * @param uc
     */
    public void addUcComp(UCComplementar uc){
        this.listaUCsComp.addNovaUc(uc);
    }
    
    /**
     *
     * @param uc
     * @param nomePerfil
     */
    public void addUcPerfil(UCPerfil uc, String nomePerfil){
        this.listaPerfis.addNovaUc(uc, nomePerfil);
    }
    
    /**
     *
     * @param codigo
     * @param tipoUC
     */
    public void removeUc(String codigo, int tipoUC, String nomePerfil){
        if(tipoUC == 1) this.listaUCsLic.removeUc(codigo);
        if(tipoUC == 2) this.listaUCsComp.removeUc(codigo);
        else this.listaPerfis.removeUc(codigo, nomePerfil);
    }
    
    /**
     *
     * @return lista de alunos registados
     */
    public ArrayList<String> getAlunosRegistados(){
        return this.listaAlunos.getAsList();
    }
    
    public ArrayList<String> getProfessoresRegistados(){
        return this.listaProfs.getAsList();
    }
    
    /**
     *
     * @param username
     * @return aluno com determinado username
     */
    public Aluno getAlunoPorUsername(String username){
        return this.listaAlunos.getAluno(username);
    }
    
    /**
     *
     * @param uc
     */
    public void insereUCLic(UCLicenciatura uc){
        this.listaUCsLic.addNovaUc(uc);
    }
    
    /**
     *
     * @param uc
     * @param nomePerfil
     */
    public void insereUCPerfil(UCPerfil uc, String nomePerfil){
        this.listaPerfis.addNovaUc(uc, nomePerfil);
    }
    
    /**
     *
     * @param nomePerfil
     */
    public void inserePerfil(String nomePerfil){
        Perfil p = new Perfil(nomePerfil);
        this.listaPerfis.addPerfil(p);
    }
    
    /**
     *
     * @param codigoUC
     * @param userAluno
     */
    public void insereAlunoUC(String codigoUC, String userAluno){
        this.listaRegistos.registaAlunoEmUc(codigoUC, userAluno);
    }
    
    public ArrayList<String> getUCsAluno(String username){
       ArrayList<String> res = new ArrayList<>();
       ArrayList<String> codigos = this.listaRegistos.getUcsDeAluno(username);
       for(String uc: codigos){
           String nome = this.listaUCsLic.getNomeUc(uc);
           if(nome==null){
               nome = this.listaUCsComp.getNomeUc(uc);
               if(nome==null){
                   nome = this.listaPerfis.getNomeUc(uc);
               }
           }
           res.add(uc + "-" + nome);
       }
       return res;
    }
    
    public ArrayList<String> getUCsNIAluno(String username){
        ArrayList<String> ucs = new ArrayList<>();
        for(UCLicenciatura ucl: this.listaUCsLic.getAll()) 
            ucs.add(ucl.getCodigo());
        
        for(UCComplementar ucc: this.listaUCsComp.getAll()) 
            ucs.add(ucc.getCodigo());
        
        for(UCPerfil ucp: this.listaPerfis.getAllUcs()) 
            ucs.add(ucp.getCodigo());
        
        return this.listaRegistos.getUcsNIAluno(username, ucs);
    }
    
    public int addNovoPerfil(Perfil p){
        return this.listaPerfis.addPerfil(p);
    }
    
    public int removePerfil(String perfil){
        return this.listaPerfis.eliminaPerfil(perfil);
    }
    
    public void addNovoProfessor(Professor p){
        this.listaProfs.addProf(p);
    }
    
    public ArrayList<String> getAlunoHorario(String username){
        return this.listaAlunos.getHorario(username);
    }
    
    public int getTipoUC(String codigoUC){
        if(this.listaUCsLic.existeUc(codigoUC)) return 1;
        if(this.listaUCsComp.existeUc(codigoUC)) return 2;
        if(this.listaPerfis.existeUc(codigoUC)) return 3;
        return 0;
    }
    
    public String getCodigoUC(String nomeUC){
        if(this.listaUCsLic.getCodigoUC(nomeUC) != null) {
            return this.listaUCsLic.getCodigoUC(nomeUC);
        }
        if(this.listaUCsComp.getCodigoUC(nomeUC) != null){
            return this.listaUCsComp.getCodigoUC(nomeUC);
        }
        if(this.listaPerfis.getCodigoUC(nomeUC) != null){
            return this.listaPerfis.getCodigoUC(nomeUC);
        }
        
        return null;
    }
    
    public String getPerfil(String codigoUC){
        return this.listaPerfis.getPerfilDeUC(codigoUC);
    }
    
    public void removeAlunoDeUC(String codigoUC, String userAluno){
        this.listaRegistos.removeAluno_UC(codigoUC, userAluno);
    }
    
    public String getTurno_UC_Aluno(String nomeUC, String username){
        return this.listaAlunos.getTurnoDeUc(nomeUC, username);
    }
    
    public void trocaTurnos(String alunoOrigem, String alunoDestino, String turnoF, String turnoI, String nomeUC){
        String codigoUC = this.getCodigoUC(nomeUC);
        this.listaRegistos.removeRegisto(alunoOrigem, turnoI, codigoUC);
        this.listaRegistos.removeRegisto(alunoDestino, turnoF, codigoUC);
        this.listaRegistos.novoRegisto(alunoOrigem, turnoF, codigoUC);
        this.listaRegistos.novoRegisto(alunoDestino, turnoI, codigoUC);
    }
    
    public void criaTroca(String aluno, String nomeuc, String turnoF){
        String codigoUC = this.getCodigoUC(nomeuc);
        this.listaAlunos.insereTroca(aluno, codigoUC, nomeuc, turnoF);
    }
    
    public ArrayList<String> getTrocas(){
        String nomeUC, codigoUC, aluno, turnoI, turnoF;
        ArrayList<String> res = new ArrayList<>();
        for(String s: this.listaAlunos.getTodasTrocas()){
            codigoUC = s.substring(s.indexOf("-")+2, s.indexOf("f")-4);
            nomeUC = this.getNomeUc(codigoUC);
            aluno = s.substring(0, s.indexOf(" "));
            turnoF = s.substring(s.indexOf("f")+2, s.indexOf("i")-4);
            turnoI = s.substring(s.indexOf("i")+2, s.length()-1);
            res.add(aluno + " - UC: " + nomeUC + " - Turno Oferecido: " + turnoI + " - Turno Requerido: " + turnoF);
        }
        return res;
        
    }
    
    public String getNomeUc(String codigoUC){
        if(this.listaUCsLic.getNomeUc(codigoUC) != null) {
            return this.listaUCsLic.getCodigoUC(codigoUC);
        }
        if(this.listaUCsComp.getNomeUc(codigoUC) != null){
            return this.listaUCsComp.getCodigoUC(codigoUC);
        }
        if(this.listaPerfis.getNomeUc(codigoUC) != null){
            return this.listaPerfis.getNomeUc(codigoUC);
        }
        return null;
    }
    
    public void alteraPassword(String username, String nova){
        this.listaAlunos.mudaPassAluno(username, nova);
    }
    
    public void alteraEmailAluno(String username, String nova){
        this.listaAlunos.mudaEmailAluno(username, nova);
    }
}
