/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.Collection;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.database.UcCompDAO;

/**
 *
 * @author Tiago
 */
public class GestaoUCsComplementares {
    
    private UcCompDAO listaUCs;
    
    public GestaoUCsComplementares(){
        this.listaUCs = new UcCompDAO();
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getListaTurnos(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        UCComplementar uc = this.listaUCs.get(codigoUC);
        uc.getTurnos().forEach((t) -> {
            res.add(t.getId());
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getListaProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p.getUsername() + " - " + p.getNome());
        });
        return res;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getStor(String codigoUC){
        return this.listaUCs.get(codigoUC).getResponsavel();
    }
    
    /**
     *
     * @param codigo
     */
    public void removeUc(String codigo){
        this.listaUCs.remove(codigo);
    }
    
    /**
     *
     * @param uc
     */
    public void addNovaUc(UCComplementar uc){
        this.listaUCs.put(uc.getCodigo(), uc);
    }
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    public void removeProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).removeDeDocentes(p);
    }
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    public void addProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).addProfToDocentes(p);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     */
    public void atualizaTurnos(String idTurno, String codigoUC){
        this.listaUCs.get(codigoUC).corrigeIdTurnos(idTurno);
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     */
    public void removeTurno(String turno, String codigoUC){
        this.listaUCs.get(codigoUC).ripTurno(turno);
    }
    
    /**
     *
     * @param t
     * @param codigoUC
     */
    public void addNovoTurno(Turno t, String codigoUC){
        this.listaUCs.get(codigoUC).addTurno(t);
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public boolean checkTeorica(String codigoUC){
        return this.listaUCs.get(codigoUC).existeTeorica();
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public ArrayList<String> getNomesProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p.getNome() + " - " + p.getUsername());
        });
        return res;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getNome_E_Cod_Ucs(){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.values().forEach((uc) -> {
            res.add(uc.getCodigo() + " - " + uc.getNome());
        });
        return res;
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @return
     */
    public Turno getTurnoPorId(String idTurno, String codigoUC){
        for(Turno t: this.listaUCs.get(codigoUC).getTurnos()){
            if(t.getId().equals(idTurno)) return t;
        }
        return null;
    }
    
    public Collection<UCComplementar> getAll(){
        return this.listaUCs.values();
    }
    
    public String getNomeUc(String codigoUC){
        String nome = null;
        if(this.listaUCs.containsKey(codigoUC)) nome = this.listaUCs.get(codigoUC).getNome();
        return nome;
    }
    
}
