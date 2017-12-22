/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.database.PerfilDAO;

/**
 *
 * @author Tiago
 */
public class GestaoPerfis {
    
    private PerfilDAO listaPerfis;
    
    public GestaoPerfis(){
        this.listaPerfis = new PerfilDAO();
    }
    
    public int addPerfil(Perfil p){
        this.listaPerfis.put(p.getNome(), p);
        if(this.listaPerfis.containsKey(p.getNome()))
            return 1;
        else return 0;
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
        Perfil p = this.listaPerfis.get(nomePerfil).addTurno(t, codigoUC);
        this.listaPerfis.put(p.getNome(), p);
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
    
    public ArrayList<UCPerfil> getAllUcs(){
        ArrayList<UCPerfil> res = new ArrayList<>();
        for(Perfil p: this.listaPerfis.values()){
            for(UCPerfil uc: p.getListaUcs().values())
                res.add(uc);
        }
        return res;
    }
   
    public String getNomeUc(String codigoUC){
        String nome = null;
        for(Perfil p: this.listaPerfis.values()){
            if(p.getListaUcs().containsKey(codigoUC)){
                nome = p.getListaUcs().get(codigoUC).getNome();
            }
        }
        return nome;
    }
    
    public int eliminaPerfil(String perfil){
        this.listaPerfis.remove(perfil);
        if(!this.listaPerfis.containsKey(perfil)){
            return 1;
        }
        else return 0;
    }
    
    public boolean existeUc(String codigoUC){
        boolean res=false;
        for(Perfil p: this.listaPerfis.values()){
            for(UCPerfil uc: p.getListaUcs().values())
                if(uc.getCodigo().equals(codigoUC)) return true;
        }
        return res;
    }
    
    public String getCodigoUC(String nomeUC){
        for(Perfil p: this.listaPerfis.values()){
            for(UCPerfil uc: p.getListaUcs().values()){
                if(uc.getNome().equals(nomeUC)){
                    return uc.getCodigo();
                }
            }
        }
        return null;
    }
    
    public String getPerfilDeUC(String codigoUC){
        for(Perfil p: this.listaPerfis.values()){
            if(p.getListaUcs().containsKey(codigoUC)){
                p.getNome();
            }
        }
        return null;
    }
    
    public int getAulas(String uc, String turno){
        int res=-1;
        String ucp;
        for(Perfil p: this.listaPerfis.values()){
            for(String s: p.getUcsPerfil()){
                ucp = s.substring(0, s.indexOf(" "));
                res = p.getListaUcs().get(ucp).getNAulas(turno);
                break;
            }
            if(res != -1) break;
        }
        return res;
    }
}
