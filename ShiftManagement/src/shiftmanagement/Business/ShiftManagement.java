/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

import java.sql.Time;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.Business.Utilizador.Admin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private Map<Integer, ArrayList<ArrayList<Turno>>> horarios;
    private int casoTeste;
    
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
        this.listaRegistos = new GestaoRegistos();
        this.horarios = new HashMap<Integer, ArrayList<ArrayList<Turno>>>();
        this.casoTeste = 1;
    }
    
    /**
     *
     * @param s
     */
    public ShiftManagement(ShiftManagement s){
        utilizador = s.getUtilizador();
        curso = s.getCurso();
        this.listaUCsLic = new GestaoUCsLicenciatura();
        this.listaUCsComp = new GestaoUCsComplementares();
        this.listaAlunos = new GestaoAlunos();
        this.listaProfs = new GestaoProfessores();
        this.listaPerfis = new GestaoPerfis();
        this.listaRegistos = new GestaoRegistos();
        this.horarios = new HashMap<Integer, ArrayList<ArrayList<Turno>>>();
        this.casoTeste = 1;
    }
    
    /**
     *
     * @param u
     * @param c
     */
    public ShiftManagement(Utilizador u, Curso c){
        this.utilizador = u;
        curso = c;
        this.listaUCsLic = new GestaoUCsLicenciatura();
        this.listaUCsComp = new GestaoUCsComplementares();
        this.listaAlunos = new GestaoAlunos();
        this.listaProfs = new GestaoProfessores();
        this.listaPerfis = new GestaoPerfis();
        this.listaRegistos = new GestaoRegistos();
        this.horarios = new HashMap<Integer, ArrayList<ArrayList<Turno>>>();
        this.casoTeste = 1;
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
    public int getTeste(){
        return this.casoTeste;
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
                Professor p = this.listaProfs.getProf(s);
                profs.add(p.getUsername() + " - " + p.getNome());
                
            });
        }
        if(tipoUC == 2){
            this.listaUCsComp.getListaProfs(codigoUC).forEach((s) -> {
                Professor p = this.listaProfs.getProf(s);
                profs.add(p.getUsername() + " - " + p.getNome());
            });
        }
        if(tipoUC == 3){
            this.listaPerfis.getListaProfs(codigoUC, nomePerfil).forEach((s) -> {
                Professor p = this.listaProfs.getProf(s);
                profs.add(p.getUsername() + " - " + p.getNome());
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
        if(tipoUC == 3){
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
    
    /**
     *
     * @param nomePerfil
     * @return
     */
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
                Professor p = this.listaProfs.getProf(s);
                res.add(p.getNome());
            });
        }
        if(tipoUC == 2){
            this.listaUCsComp.getNomesProfs(codigoUC).forEach((s) -> {
                Professor p = this.listaProfs.getProf(s);
                res.add(p.getNome());
            });
        }
        if(tipoUC==3){
            this.listaPerfis.getProfs(codigoUC, nomePerfil).forEach((s) -> {
                Professor p = this.listaProfs.getProf(s);
                res.add(p.getNome());
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
    
    public void setTeste(int t){
        this.casoTeste = t;
    }
  
    /**
     *
     * @param prof
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     */
    /*public void addProfToUC(String prof, String codigoUC, int tipoUC, String nomePerfil){
        Professor p = this.listaProfs.getProf(prof);
        if(tipoUC == 1) this.listaUCsLic.addProf(p, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.addProf(p, codigoUC);
        if(tipoUC == 3) this.listaPerfis.addProfToPerfil(p, codigoUC, nomePerfil);
    }*/
    
    /**
     *
     * @param usernameProf
     * @param codigoUC
     * @param tipoUC
     */
    /*public void removeProfDeUc(String usernameProf, String codigoUC, int tipoUC, String nomePerfil){
        Professor p = this.listaProfs.getProf(usernameProf);
        if(tipoUC == 1) this.listaUCsLic.removeProf(p, codigoUC);
        if(tipoUC == 2) this.listaUCsComp.removeProf(p, codigoUC);
        if(tipoUC == 3) this.listaPerfis.removeProf(p, codigoUC, nomePerfil);
    }*/
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param tipoUC
     * @param nomePerfil
     * @return Turno com determinado id, de determinada uc.
     */
    public Turno getTurno(String idTurno, String codigoUC, int tipoUC, String nomePerfil){
        if(tipoUC == 1) return this.listaUCsLic.getTurnoPorId(idTurno, codigoUC);
        if(tipoUC == 2) return this.listaUCsComp.getTurnoPorId(idTurno, codigoUC);
        if(tipoUC == 3) return this.listaPerfis.getTurnoPorId(idTurno, codigoUC, nomePerfil);
        return null;
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
        if(tipoUC == 3) this.listaPerfis.addNovoTurno(t, codigoUC, nomePerfil);
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
    
    /**
     *
     * @return 
     */
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
     */
    public void insereUCComp(UCComplementar uc){
        this.listaUCsComp.addNovaUc(uc);
    }
    
    /**
     *
     * @param map
     * @param nomePerfil
     */
    public void insereUCPerfil(HashMap<String, UCPerfil> map, String nomePerfil){
        Perfil p = new Perfil(nomePerfil, map);
        this.listaPerfis.addPerfil(p);
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
    
    /**
     *
     * @param username
     * @return
     */
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
           res.add(uc + " - " + nome);
       }
       return res;
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getUCsNIAluno(String username){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> naoUcs = new ArrayList<>();
        ArrayList<String> ucs = new ArrayList<>();
        for(UCLicenciatura ucl: this.listaUCsLic.getAll()) 
            ucs.add(ucl.getCodigo());
        
        for(UCComplementar ucc: this.listaUCsComp.getAll()) 
            ucs.add(ucc.getCodigo());
        
        for(UCPerfil ucp: this.listaPerfis.getAllUcs()) 
            ucs.add(ucp.getCodigo());
        
        naoUcs = this.listaRegistos.getUcsNIAluno(username, ucs);
        
        for(String s: naoUcs){
            String nome = this.getNomeUc(s);
            if(nome!=null){
                res.add(s + " - " + nome);
            }
        }
        
        return res;
    }
    
    /**
     *
     * @param p
     * @return
     */
    public int addNovoPerfil(Perfil p){
        return this.listaPerfis.addPerfil(p);
    }
    
    /**
     *
     * @param perfil
     * @return
     */
    public int removePerfil(String perfil){
        return this.listaPerfis.eliminaPerfil(perfil);
    }
    
    /**
     *
     * @param p
     */
    public void addNovoProfessor(Professor p){
        this.listaProfs.addProf(p);
    }
    
    /**
     *
     * @param username
     * @return
     */
    public ArrayList<String> getAlunoHorario(String username){
        return this.listaAlunos.getHorario(username);
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public int getTipoUC(String codigoUC){
        if(this.listaUCsLic.existeUc(codigoUC)) return 1;
        if(this.listaUCsComp.existeUc(codigoUC)) return 2;
        if(this.listaPerfis.existeUc(codigoUC)) return 3;
        return 0;
    }
    
    /**
     *
     * @param nomeUC
     * @return
     */
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
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getPerfil(String codigoUC){
        return this.listaPerfis.getPerfilDeUC(codigoUC);
    }
    
    /**
     *
     * @param codigoUC
     * @param userAluno
     */
    public void removeAlunoDeUC(String codigoUC, String userAluno){
        this.listaRegistos.removeAluno_UC(codigoUC, userAluno);
        String nomeUC = this.getNomeUc(codigoUC);
        this.listaAlunos.removeAluno_UC(nomeUC, userAluno, codigoUC);
    }
    
    /**
     *
     * @param nomeUC
     * @param username
     * @return
     */
    public String getTurno_UC_Aluno(String nomeUC, String username){
        return this.listaAlunos.getTurnoDeUc(nomeUC, username);
    }
    
    /**
     *
     * @param alunoOrigem
     * @param alunoDestino
     * @param turnoF
     * @param turnoI
     * @param nomeUC
     */
    public void trocaTurnos(String alunoOrigem, String alunoDestino, String turnoF, String turnoI, String nomeUC){
        String codigoUC = this.getCodigoUC(nomeUC);
        this.listaRegistos.removeRegisto(alunoOrigem, turnoI, codigoUC);
        this.listaRegistos.removeRegisto(alunoDestino, turnoF, codigoUC);
        this.listaRegistos.novoRegisto(alunoOrigem, turnoF, codigoUC);
        this.listaRegistos.novoRegisto(alunoDestino, turnoI, codigoUC);
        this.removeTroca(alunoOrigem, nomeUC, turnoF, turnoI);
        this.removeTroca(alunoDestino, nomeUC, turnoI, turnoF);
    }
    
    /**
     *
     * @param aluno
     * @param nomeuc
     * @param turnoF
     */
    public void criaTroca(String aluno, String nomeuc, String turnoF){
        String codigoUC = this.getCodigoUC(nomeuc);
        this.listaAlunos.insereTroca(aluno, codigoUC, nomeuc, turnoF);
    }
    
    /**
     *
     * @param aluno
     * @param nomeUC
     * @param turnoF
     * @param turnoI
     */
    public void removeTroca(String aluno, String nomeUC, String turnoF, String turnoI){
        String codigoUC = this.getCodigoUC(nomeUC);
        this.listaAlunos.remTroca(aluno, codigoUC, turnoF, turnoI);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getTrocas(){
        String nomeUC, codigoUC, aluno, turnoI, turnoF;
        ArrayList<String> res = new ArrayList<>();
        for(String s: this.listaAlunos.getTodasTrocas()){
            codigoUC = s.substring(s.indexOf("-")+2, s.indexOf("f")-3);
            nomeUC = this.getNomeUc(codigoUC);
            aluno = s.substring(0, s.indexOf(" "));
            turnoF = s.substring(s.indexOf("f")+2, s.indexOf("i")-3);
            turnoI = s.substring(s.indexOf("i")+2, s.length());
            res.add(aluno + " - UC: " + nomeUC + " - Turno Oferecido: " + turnoI + " - Turno Requerido: " + turnoF);
        }
        return res;
        
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getNomeUc(String codigoUC){
        if(this.listaUCsLic.getNomeUc(codigoUC) != null) {
            return this.listaUCsLic.getNomeUc(codigoUC);
        }
        if(this.listaUCsComp.getNomeUc(codigoUC) != null){
            return this.listaUCsComp.getNomeUc(codigoUC);
        }
        if(this.listaPerfis.getNomeUc(codigoUC) != null){
            return this.listaPerfis.getNomeUc(codigoUC);
        }
        return null;
    }
    
    /**
     *
     * @param username
     * @param nova
     */
    public void alteraPassword(String username, String nova){
        this.listaAlunos.mudaPassAluno(username, nova);
    }
    
    /**
     *
     * @param username
     * @param nova
     */
    public void alteraEmailAluno(String username, String nova){
        this.listaAlunos.mudaEmailAluno(username, nova);
    }
    
    /**
     *
     * @param username
     * @return Lista com as ucs e turnos de um professor.
     */
    public ArrayList<String> get_Uc_E_Turnos_Prof(String username){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> neo = this.listaProfs.getUcsTurnos(username);
        String nomeUC, codigoUC, turno, hora;
        int tipo;
        for(String s: neo){
            nomeUC = s.substring(0, s.indexOf("-")-1);
            codigoUC = this.getCodigoUC(nomeUC);
            tipo = this.getTipoUC(codigoUC);       
            turno = s.substring(s.indexOf("-")+2, s.length());
            hora = this.getHoraTurno(turno, codigoUC);
            if(tipo == 1) res.add(nomeUC + " - " + turno + ": " + hora + " -> Cadeira de Licenciatura");
            if(tipo == 2) res.add(nomeUC + " - " + turno + ": " + hora + " -> Cadeira Complementar");
            if(tipo == 3) res.add(nomeUC + " - " + turno + ": " + hora + " -> Cadeira de Mestrado");
        }
        return res;
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     * @return Hora de um dado turno de uma uc.
     */
    public String getHoraTurno(String turno, String codigoUC){
        int tipo = this.getTipoUC(codigoUC);
        return this.getTurno(turno, codigoUC, tipo, null).getHora().toString();
    }
    
    /**
     *
     * @param codigoUC
     * @param idTurno
     * @return lista com username dos alunos e correspondentes faltas a um turno de uma uc.
     */
    public ArrayList<String> getAlunosFaltas(String codigoUC, String idTurno){
        ArrayList<String> aux = this.listaRegistos.getAlunosEmTurnoUc(codigoUC, idTurno);
        return this.listaAlunos.getFaltas(aux, codigoUC);
        
    }
    
    /**
     *
     * @param aluno
     * @param uc
     * @param turno
     */
    public void adicionaFaltaAAluno(String aluno, String uc, String turno){
        this.listaAlunos.setFalta(aluno, uc, turno);
    }
    
    /**
     *
     * @param uc
     * @param turno
     * @return
     */
    public int getNumeroAulas(String uc, String turno){
        int tipo = this.getTipoUC(uc);
        if(tipo == 1) return this.listaUCsLic.getAulas(uc, turno);
        if(tipo == 2) return this.listaUCsComp.getAulas(uc, turno);
        if(tipo == 3) return this.listaPerfis.getAulas(uc, turno);
        return -1;
    }
    
    /**
     *
     * @param ano
     * @param uc
     * @param idTurno
     * @param hora
     * @param dia
     */
    public void alocaTurno(String ano, String uc, String idTurno, Time hora, String dia){
        int a = Integer.parseInt(ano);
        this.listaUCsLic.alocaTurno(uc, idTurno, hora, dia);
    }
    
    /**
     *
     * @param list
     * @param a
     */
    public void addHorario(ArrayList<Turno> list, String a){
        int ano = Integer.parseInt(a); 
        ArrayList<ArrayList<Turno>> ts;
        if(ano == 1){
            if(this.horarios.get(1) != null){
                ts = this.horarios.get(1);
                ts.add(list);
                this.horarios.put(1, ts);
            }
            else{
                ts = new ArrayList<ArrayList<Turno>>();
                ts.add(list);
                this.horarios.put(1, ts);
            }
        }
        if(ano == 2){
            if(this.horarios.get(2) != null){
                ts = this.horarios.get(2);
                ts.add(list);
                this.horarios.put(2, ts);
            }
            else{
                ts = new ArrayList<ArrayList<Turno>>();
                ts.add(list);
                this.horarios.put(2, ts);
            }
        }
        if(ano == 3){
            if(this.horarios.get(3) != null){
                ts = this.horarios.get(3);
                ts.add(list);
                this.horarios.put(3, ts);
            }
            else{
                ts = new ArrayList<ArrayList<Turno>>();
                ts.add(list);
                this.horarios.put(3, ts);
            }
        }
    }
    
    /**
     *
     * @param user
     * @param uc
     * @param tipoUC
     * @param perfil
     */
    public void setRegente(String user, String uc, int tipoUC, String perfil){
        if(tipoUC == 1) this.listaUCsLic.setRegente(user, uc);
        if(tipoUC == 2) this.listaUCsComp.setRegente(user, uc);
        if(tipoUC == 3) this.listaPerfis.setRegente(user, uc, perfil);
        this.listaProfs.setRegente(user);
    }
    
    /**
     *
     * @return
     */
    public Map<Integer, ArrayList<ArrayList<Turno>>> getHorarios(){
        return this.horarios;
    }
    
    /**
     *
     * @param uc
     * @return
     */
    public ArrayList<String> getTurnosUc(String uc){
        int tipo = this.getTipoUC(uc);
        if(tipo==1) return this.listaUCsLic.getTurnos(uc);
        if(tipo==2) return this.listaUCsComp.getTurnos(uc);
        if(tipo==3) return this.listaPerfis.getTurnosPerfil(uc);
        return null;
    }
    
    /**
     *
     * @param aluno
     * @param turno
     * @param uc
     */
    public void addTurnoParaAluno(String aluno, String turno, String uc){
        uc = uc.substring(0, uc.indexOf(" "));
        this.listaRegistos.novoRegisto(aluno, turno, uc);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param tipo
     * @return
     */
    public int getCapSala(String idTurno, String codigoUC, int tipo){
        if(tipo==1) return this.listaUCsLic.getCapacidadeSala(idTurno, codigoUC);
        if(tipo==2) return this.listaUCsComp.getCapacidadeSala(idTurno, codigoUC);
        if(tipo==3) return this.listaPerfis.getCapacidadeSala(idTurno, codigoUC);
        return -1;
    }
    
    public int getInscritosTurno(String turno, String codigoUC, int tipo){
        if(tipo==1) return this.listaUCsLic.getTotalInscritos(turno, codigoUC);
        if(tipo==2) return this.listaUCsComp.getTotalInscritos(turno, codigoUC);
        if(tipo==3) return this.listaPerfis.getTotalInscritos(turno, codigoUC);
        return -1;
    }
    
    public int getMaxAlunos(String turno, String uc, int tipo){
        if(tipo==1) return this.listaUCsLic.getMaximoAl(turno, uc);
        if(tipo==2) return this.listaUCsComp.getMaximoAl(turno, uc);
        if(tipo==3) return this.listaPerfis.getMaximoAl(turno, uc);
        return -1;
    }
    
    public boolean verificaEstudante(String usernameAluno){
        return this.listaAlunos.getAluno(usernameAluno).getTrabalhador();
    }
    
    
    
    
    
    
}
