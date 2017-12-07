/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.ArrayList;
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
        listaUCs = new UcLicDAO();
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
    
    public ArrayList<String> getNomesUCs(){
        return this.listaUCs.getNomesUcs();
    }
    
    public String getCodigoUC(String nome){
        return this.listaUCs.getCodigo(nome);
    }
    
    public ArrayList<String> getListaProfs(String codigoUC){
        return this.listaUCs.getProfs(codigoUC);
    }
    
    public ArrayList<String> getListaTurnos(String codigoUC){
        return this.listaUCs.getTurnos(codigoUC);
    }
    
    public ArrayList<String> getNomesProfs(String codigoUC){
        return this.listaUCs.getProfsPorNome(codigoUC);
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
    
}
