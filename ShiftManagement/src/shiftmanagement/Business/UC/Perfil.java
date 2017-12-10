/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.database.UcPerfilDAO;

/**
 *
 * @author Tiago
 */
public class Perfil {
    
    private String nome;
    private UcPerfilDAO listaUcs;
    
    /**
     *
     */
    public Perfil(){
        this.nome = "";
        this.listaUcs = new UcPerfilDAO();
    }
    
    /**
     *
     * @param nome
     */
    public Perfil(String nome){
        this.nome = nome;
        this.listaUcs = new UcPerfilDAO();
    }
    
    /**
     *
     * @return
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     *
     * @param n
     */
    public void setNome(String n){
        this.nome = n;
    }
    
    public Turno getTurno(String codigoUC, String idTurno){
        return this.listaUcs.get(codigoUC).getTurno(idTurno);
    }
    
    public UCPerfil getUc(String codigoUC){
        return this.listaUcs.get(codigoUC);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getUcsPerfil(){
        ArrayList<String> res = new ArrayList<>();
        this.listaUcs.values().forEach((uc) -> {
            res.add(uc.getCodigo() + " - " + uc.getNome());
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getProfDeUc(String codigoUC){
        return this.listaUcs.get(codigoUC).getResponsavel();
    }
    
    /**
     *
     * @param codigoUC
     * @return Lista de professores de uma uc.
     */
    public ArrayList<Professor> getDocentes(String codigoUC){
        ArrayList<Professor> res = new ArrayList<>();
        this.listaUcs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p);
        });
        return res;
    }
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    public void addProfToDocentes(Professor p, String codigoUC){
        this.listaUcs.get(codigoUC).addProfToDocentes(p);
    }
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    public void removeDeDocentes(Professor p, String codigoUC){
        this.listaUcs.get(codigoUC).getEquipaDocente().remove(p);
    }
    
    /**
     *
     * @param t
     * @param codigoUC
     */
    public void addTurno(Turno t, String codigoUC){
        this.listaUcs.get(codigoUC).addTurno(t);
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     */
    public void ripTurno(String turno, String codigoUC){
        this.listaUcs.get(codigoUC).ripTurno(turno);
    }
    
    public void corrigeIdTurnos(String idTurno, String codigoUC){
        this.listaUcs.get(codigoUC).corrigeIdTurnos(idTurno);
    }
    
    public void novaUc(UCPerfil uc){
        this.listaUcs.put(uc.getCodigo(), uc);
    }
    
    public void eliminaUc(String codigoUC){
        this.listaUcs.remove(codigoUC);
    }
}
