/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    /**
     *
     * @param p
     * @return
     */
    public int addPerfil(Perfil p){
        this.listaPerfis.put(p.getNome(), p);
        if(this.listaPerfis.containsKey(p.getNome()))
            return 1;
        else return 0;
    }
    
    /**
     *
     * @param map
     * @param nomePerfil
     */
    public void addUCs(HashMap<String, UCPerfil> map, String nomePerfil){
        Perfil p = this.listaPerfis.get(nomePerfil).novasUcs(map);
        System.out.println("antes: " + this.listaPerfis.get(nomePerfil).getListaUcs().size());
        //this.listaPerfis.put(p.getNome(), p);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param nomePerfil
     */
    public void atualizaTurnos(String idTurno, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).corrigeIdTurnos(idTurno, codigoUC);
    }
    
    /**
     *
     * @param turno
     * @param codigoUC
     * @param nomePerfil
     */
    public void removeTurno(String turno, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).ripTurno(turno, codigoUC);
    }
    
    /**
     *
     * @param t
     * @param codigoUC
     * @param nomePerfil
     */
    public void addNovoTurno(Turno t, String codigoUC, String nomePerfil){
        Perfil p = this.listaPerfis.get(nomePerfil).addTurno(t, codigoUC);
        this.listaPerfis.put(p.getNome(), p);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @param nomePerfil
     * @return
     */
    public Turno getTurnoPorId(String idTurno, String codigoUC, String nomePerfil){
        return this.listaPerfis.get(nomePerfil).getTurno(codigoUC, idTurno);
    }
    
    /**
     *
     * @param p
     * @param codigoUC
     * @param nomePerfil
     */
    /*public void removeProf(Professor p, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).removeDeDocentes(p, codigoUC);
    }*/
    
    /**
     *
     * @param p
     * @param codigoUC
     * @param nomePerfil
     */
    /*public void addProfToPerfil(Professor p, String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).addProfToDocentes(p, codigoUC);
    }*/
    
    /**
     *
     * @param codigoUC
     * @param nomePerfil
     * @return
     */
    public ArrayList<String> getProfs(String codigoUC, String nomePerfil){
        ArrayList<String> res = new ArrayList<>();
        this.listaPerfis.get(nomePerfil).getDocentes(codigoUC).forEach((p) -> {
            res.add(p);
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
            res.add(p);
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
    
    /**
     *
     * @param codigoUC
     * @param nomePerfil
     */
    public void removeUc(String codigoUC, String nomePerfil){
        this.listaPerfis.get(nomePerfil).eliminaUc(codigoUC);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<UCPerfil> getAllUcs(){
        ArrayList<UCPerfil> res = new ArrayList<>();
        for(Perfil p: this.listaPerfis.values()){
            for(UCPerfil uc: p.getListaUcs().values())
                res.add(uc);
        }
        return res;
    }
   
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getNomeUc(String codigoUC){
        String nome = null;
        for(Perfil p: this.listaPerfis.values()){
            if(p.getListaUcs().containsKey(codigoUC)){
                nome = p.getListaUcs().get(codigoUC).getNome();
            }
        }
        return nome;
    }
    
    /**
     *
     * @param perfil
     * @return
     */
    public int eliminaPerfil(String perfil){
        this.listaPerfis.remove(perfil);
        if(!this.listaPerfis.containsKey(perfil)){
            return 1;
        }
        else return 0;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public boolean existeUc(String codigoUC){
        boolean res=false;
        for(Perfil p: this.listaPerfis.values()){
            for(UCPerfil uc: p.getListaUcs().values())
                if(uc.getCodigo().equals(codigoUC)) return true;
        }
        return res;
    }
    
    /**
     *
     * @param nomeUC
     * @return
     */
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
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getPerfilDeUC(String codigoUC){
        for(Perfil p: this.listaPerfis.values()){
            if(p.getListaUcs().containsKey(codigoUC)){
                p.getNome();
            }
        }
        return null;
    }
    
    /**
     *
     * @param uc
     * @param turno
     * @return
     */
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
    
    /**
     *
     * @param prof
     * @param uc
     * @param perfil
     */
    public void setRegente(String prof, String uc, String perfil){
        Perfil p = this.listaPerfis.get(perfil).setReg(uc, prof);
        this.listaPerfis.put(p.getNome(), p);
    }
    
    /**
     *
     * @param uc
     * @param perfil
     */
    public void addNovaUc(UCPerfil uc, String perfil){
        Perfil p = this.listaPerfis.get(perfil).addUc(uc);
        this.listaPerfis.put(p.getNome(), p);
    }
    
    /**
     *
     * @param uc
     * @return
     */
    public ArrayList<String> getTurnosPerfil(String uc){
        ArrayList<String> res = new ArrayList<>();
        String p = this.getPerfilDeUC(uc);
        if(p!=null){
            for(Turno t: this.listaPerfis.get(p).getListaUcs().get(uc).getTurnos()){
                res.add(t.getId() + " às: " + t.getHora());
            }
        }
        return res;
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return
     */
    public int getCapacidadeSala(String turno, String uc){
        String perfil = this.getPerfilDeUC(uc);
        return this.listaPerfis.get(perfil).getCapacidadeSala(turno, uc);
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return
     */
    public int getTotalInscritos(String turno, String uc){
        String perfil = this.getPerfilDeUC(uc);
        return this.listaPerfis.get(perfil).getInscritos(turno, uc);
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return
     */
    public int getMaximoAl(String turno, String uc){
        String perfil = this.getPerfilDeUC(uc);
        return this.listaPerfis.get(perfil).getMaximo(turno, uc);
    }
}
