/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class Perfil {
    
    private String nome;
    private Map<String, UCPerfil> listaUcs;
    
    /**
     *
     */
    public Perfil(){
        this.nome = "";
        this.listaUcs = new HashMap<>();
    }
    
    /**
     *
     * @param nome
     */
    public Perfil(String nome){
        this.nome = nome;
        this.listaUcs = new HashMap<>();
    }

    public Perfil(String nome, Map<String, UCPerfil> listaUcs) {
        this.nome = nome;
        this.listaUcs = listaUcs;
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
    
    /**
     *
     * @param lista
     */
    public void setListaUcs(HashMap<String, UCPerfil> lista){
        this.listaUcs = lista;
    }
    
    /**
     *
     * @return
     */
    public HashMap<String, UCPerfil> getListaUcs(){
        return (HashMap<String, UCPerfil>) this.listaUcs;
    }
    
    /**
     *
     * @param codigoUC
     * @param idTurno
     * @return
     */
    public Turno getTurno(String codigoUC, String idTurno){
        return this.listaUcs.get(codigoUC).getTurno(idTurno);
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
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
    public ArrayList<String> getDocentes(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        
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
    /*public void addProfToDocentes(Professor p, String codigoUC){
        this.listaUcs.get(codigoUC).addProfToDocentes(p);
    }*/
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    /*public void removeDeDocentes(Professor p, String codigoUC){
        this.listaUcs.get(codigoUC).getEquipaDocente().remove(p);
    }*/
    
    /**
     *
     * @param t
     * @param codigoUC
     */
    public Perfil addTurno(Turno t, String codigoUC){
        this.listaUcs.get(codigoUC).addTurno(t);
        return this;
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     */
    public void ripTurno(String turno, String codigoUC){
        this.listaUcs.get(codigoUC).ripTurno(turno);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     */
    public void corrigeIdTurnos(String idTurno, String codigoUC){
        this.listaUcs.get(codigoUC).corrigeIdTurnos(idTurno);
    }
    
    /**
     *
     * @param map
     * @return 
     */
    public Perfil novasUcs(HashMap<String, UCPerfil> map){
        this.listaUcs = map;
        return this;
    }
    
    /**
     *
     * @param codigoUC
     */
    public void eliminaUc(String codigoUC){
        this.listaUcs.remove(codigoUC);
    }
    
    public Perfil setReg(String uc, String prof){
        UCPerfil u = (UCPerfil) this.listaUcs.get(uc).setResponsavel(prof);
        this.listaUcs.put(u.getCodigo(), u);
        return this;
    }
    
    public Perfil addUc(UCPerfil uc){
        this.listaUcs.put(uc.getCodigo(), uc);
        return this;
    }
    
    public int getCapacidadeSala(String turno, String uc){
        return this.listaUcs.get(uc).getCapacidadeSala(turno);
    }
    
    public int getInscritos(String turno, String uc){
        return this.listaUcs.get(uc).getInscritos(turno);
    }
    
    public int getMaximo(String turno, String uc){
        return this.listaUcs.get(uc).getMaximo(turno);
    }
}
