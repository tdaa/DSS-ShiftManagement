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
import shiftmanagement.database.UcLicDAO;

/**
 *
 * @author Tiago
 */
public class GestaoUCsLicenciatura {
       
    private UcLicDAO listaUCs;
    
    public GestaoUCsLicenciatura(){
        this.listaUCs = new UcLicDAO();
    }
    
    public String getStor(String codigoUC){
        return this.listaUCs.get(codigoUC).getResponsavel();
    }
    
    public void removeUc(String codigo){
        this.listaUCs.remove(codigo);
    }
    
    public void addNovaUc(UCLicenciatura uc){
        this.listaUCs.put(uc.getCodigo(), uc);
    }
    
    public int getIdUltimoTurno(String codigoUC, String tipoTurno){
        return this.listaUCs.get(codigoUC).ultimoTurno(tipoTurno);
    }
    
    public Turno getTurnoPorId(String idTurno, String codigoUC){
        return this.listaUCs.get(codigoUC).getTurnoById(idTurno);
    }
    
    public ArrayList<String> get_Nomes_E_Cod_UCs(){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.values().forEach((uc) -> {
            res.add(uc.getCodigo() + " - " + uc.getNome());
        });
        return res;
    }
    
    public String getCodigoUC(String nome){
        for(UCLicenciatura uc: this.listaUCs.values()){
            if(uc.getNome().equals(nome)) return uc.getCodigo();
        }
        return null;
    }
    
    public ArrayList<String> getListaProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p.getUsername() + " - " + p.getNome());
        });
        return res;
    }
    
    public ArrayList<String> getListaTurnos(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        UCLicenciatura uc = this.listaUCs.get(codigoUC);
        uc.getTurnos().forEach((t) -> {
            res.add(t.getId());
        });
        return res;
    }
    
    public ArrayList<String> getNomesProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        this.listaUCs.get(codigoUC).getEquipaDocente().forEach((p) -> {
            res.add(p.getNome() + " - " + p.getUsername());
        });
        return res;
    }
    
    public String getNomeUc(String codigoUC){
        String nome = null;
        if(this.listaUCs.containsKey(codigoUC)) nome = this.listaUCs.get(codigoUC).getNome();
        return nome;
    }
    
    public void addProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).addProfToDocentes(p);
    }
    
    public void removeProf(Professor p, String codigoUC){
        this.listaUCs.get(codigoUC).removeDeDocentes(p);
    }
    
    public boolean checkTeorica(String codigoUC){
        return this.listaUCs.get(codigoUC).existeTeorica();
    }
    
    public void addNovoTurno(Turno t, String codigoUC){
        this.listaUCs.get(codigoUC).addTurno(t);
    }
    
    public void removeTurno(String turno, String codigoUC){
        this.listaUCs.get(codigoUC).ripTurno(turno);
    }
    
    public void atualizaTurnos(String idTurno, String codigoUC){
        this.listaUCs.get(codigoUC).corrigeIdTurnos(idTurno);
    }
    
    public Collection<UCLicenciatura> getAll(){
        return this.listaUCs.values();
    }
    
}
