/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class GestaoPerfis {
    
    private HashMap<String, Perfil> listaPerfis;
    
    public GestaoPerfis(){
        this.listaPerfis = new HashMap<>();
    }
    
    public void addNovaUc(UCPerfil uc, String nomePerfil){
        this.listaPerfis.get(nomePerfil).novaUc(uc);
    }
    
    public void atualizaTurnos(String idTurno, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).corrigeIdTurnos(idTurno, codigoUC);
    }
    
    public void removeTurno(String turno, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).ripTurno(turno, codigoUC);
    }
    
    public void addNovoTurno(Turno t, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).addTurno(t, codigoUC);
    }
    
    public Turno getTurnoPorId(String idTurno, String codigoUC, String nomePerfil){
        return this.listaPerfis.get(nomePerfil).getTurno(codigoUC, idTurno);
    }
    
    public void removeProf(Professor p, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).removeDeDocentes(p, codigoUC);
    }
    
    public void addProfToPerfil(Professor p, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).addProfToDocentes(p, codigoUC);
    }
    
    public ArrayList<String> getProfs(String codigoUC, String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.get(nomePerfil).getDocentes(codigoUC).forEach((p) -> {
            res.add(p.getNome() + " - " + p.getUsername());
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getListaProfs(String codigoUC, String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.get(nomePerfil).getDocentes(codigoUC).forEach((p) -> {
            res.add(p.getUsername() + " - " + p.getNome());
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @param nomePerfil
     * @return Nome de professor de uma dada uc de um perfil.
     */
    public String getStor(String codigoUC, String nomePerfil){
        return this.listaPerfis.get(nomePerfil).getProfDeUc(codigoUC);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getNomePerfis(){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.values().forEach((p) -> {
            res.add(p.getNome());
    });
        return res;
    }
    
    /**
     *
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getUcs(String nomePerfil){
        return this.listaPerfis.get(nomePerfil).getUcsPerfil();
    }
    
    /**
     *
     * @param codigoUC
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getListaTurnos(String codigoUC, String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        UCPerfil uc = this.listaPerfis.get(nomePerfil).getUc(codigoUC);
        uc.getTurnos().forEach((t) -> {
            res.add(t.getId());
        });
        return res;
    }
    
    public void removeUc(String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).eliminaUc(codigoUC);
    }
   
}
