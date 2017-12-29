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
            res.add(p);
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
    /*public void removeProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).removeDeDocentes(p);
    }*/
    
    /**
     *
     * @param p
     * @param codigoUC
     */
    /*public void addProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).addProfToDocentes(p);
    }*/
    
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
        UCComplementar uc = (UCComplementar) this.listaUCs.get(codigoUC).addTurno(t);
        this.listaUCs.put(uc.getCodigo(), uc);
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
            res.add(p);
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
    
    /**
     *
     * @return
     */
    public Collection<UCComplementar> getAll(){
        return this.listaUCs.values();
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public String getNomeUc(String codigoUC){
        String nome = null;
        if(this.listaUCs.containsKey(codigoUC)) nome = this.listaUCs.get(codigoUC).getNome();
        return nome;
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public boolean existeUc(String codigoUC){
        return this.listaUCs.containsKey(codigoUC);
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public String getCodigoUC(String nome){
        for(UCComplementar uc: this.listaUCs.values()){
            if(uc.getNome().equals(nome)) return uc.getCodigo();
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
        return this.listaUCs.get(uc).getNAulas(turno);
    }
    
    /**
     *
     * @param prof
     * @param uc
     */
    public void setRegente(String prof, String uc){
        UCComplementar u = (UCComplementar) this.listaUCs.get(uc).setResponsavel(prof);
        this.listaUCs.put(u.getCodigo(), u);     
    }
    
    /**
     *
     * @param uc
     * @return Lista com id e hora dos turnos de uma uc.
     */
    public ArrayList<String> getTurnos(String uc){
        ArrayList<String> res = new ArrayList<>();
        for(Turno t: this.listaUCs.get(uc).getTurnos()){
            res.add(t.getId() + " às: " + t.getHora());
        }
        return res;
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return Capacidade de uma sala de um dado turno de uma uc.
     */
    public int getCapacidadeSala(String turno, String uc){
        return this.listaUCs.get(uc).getCapacidadeSala(turno);
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return
     */
    public int getTotalInscritos(String turno, String uc){
        return this.listaUCs.get(uc).getInscritos(turno);
    }
    
    /**
     *
     * @param turno
     * @param uc
     * @return
     */
    public int getMaximoAl(String turno, String uc){
        return this.listaUCs.get(uc).getMaximo(turno);
    }
}
