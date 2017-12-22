/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;
import shiftmanagement.database.UcLicDAO;

/**
 *
 * @author Tiago
 */
public class GestaoUCsLicenciatura {
       
    private UcLicDAO listaUCs;
    
    /**
     *
     */
    public GestaoUCsLicenciatura(){
        this.listaUCs = new UcLicDAO();
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
    public void addNovaUc(UCLicenciatura uc){
        this.listaUCs.put(uc.getCodigo(), uc);
    }
    
    /**
     *
     * @param codigoUC
     * @param tipoTurno
     * @return
     */
    public int getIdUltimoTurno(String codigoUC, String tipoTurno){
        return this.listaUCs.get(codigoUC).ultimoTurno(tipoTurno);
    }
    
    /**
     *
     * @param idTurno
     * @param codigoUC
     * @return
     */
    public Turno getTurnoPorId(String idTurno, String codigoUC){
        return this.listaUCs.get(codigoUC).getTurnoById(idTurno);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> get_Nomes_E_Cod_UCs(){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.values().forEach((uc) -> {
            res.add(uc.getCodigo() + " - " + uc.getNome());
        });
        return res;
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public String getCodigoUC(String nome){
        for(UCLicenciatura uc: this.listaUCs.values()){
            if(uc.getNome().equals(nome)) return uc.getCodigo();
        }
        return null;
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
    public ArrayList<String> getListaTurnos(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        UCLicenciatura uc = this.listaUCs.get(codigoUC);
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
    public ArrayList<String> getNomesProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p.getNome() + " - " + p.getUsername());
        });
        return res;
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
     * @param p
     * @param codigoUC
     */
    public void addProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).addProfToDocentes(p);
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
     * @param codigoUC
     * @return
     */
    public boolean checkTeorica(String codigoUC){
        return this.listaUCs.get(codigoUC).existeTeorica();
    }
    
    /**
     *
     * @param t
     * @param codigoUC
     */
    public void addNovoTurno(Turno t, String codigoUC){
        UCLicenciatura uc = (UCLicenciatura) this.listaUCs.get(codigoUC).addTurno(t);
        this.listaUCs.put(uc.getCodigo(), uc);
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
     * @param idTurno
     * @param codigoUC
     */
    public void atualizaTurnos(String idTurno, String codigoUC){
        this.listaUCs.get(codigoUC).corrigeIdTurnos(idTurno);
    }
    
    /**
     *
     * @return
     */
    public Collection<UCLicenciatura> getAll(){
        return this.listaUCs.values();
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
     * @param uc
     * @param turno
     * @return
     */
    public int getAulas(String uc, String turno){
        return this.listaUCs.get(uc).getNAulas(turno);
    }
    
    /**
     *
     * @param uc
     * @param turno
     * @param hora
     * @param diaS
     */
    public void alocaTurno(String uc, String turno, Time hora, String diaS){
        this.listaUCs.get(uc).daHora(turno, hora, diaS);
    }
    

}
