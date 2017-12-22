/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import shiftmanagement.Business.Turno.Troca;
/**
 *
 * @author Tiago
 */
public class Aluno extends Utilizador{
    
    private boolean trabalhador;
    private ArrayList<String> horario;
    private ArrayList<Troca> trocas;
    private ArrayList<Falta> faltas;
    
    /**
     *
     */
    public Aluno(){
        super();
        this.trabalhador = false;
        this.horario = new ArrayList<>();
        this.trocas = new ArrayList<>();
        this.faltas = new ArrayList<>();
    }
    
    /**
     *
     * @param user
     * @param nome
     * @param mail
     * @param pass
     * @param trabalhador
     */
    public Aluno(String user, String nome, String mail, String pass, boolean trabalhador){
        super(user, nome, mail, pass);
        this.trabalhador = trabalhador;
        this.horario = new ArrayList<>();
        this.trocas = new ArrayList<>();
        this.faltas = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Falta> getFaltas() {
        return faltas;
    }

    /**
     *
     * @param faltas
     */
    public void setFaltas(ArrayList<Falta> faltas) {
        this.faltas = faltas;
    }
    

    /**
     *
     * @return
     */
    public ArrayList<Troca> getTrocas() {
        return trocas;
    }

    public void setTrocas(ArrayList<Troca> trocas) {
        this.trocas = trocas;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getHorario(){
        ArrayList<String> res = new ArrayList<>();
        for(String t: this.horario)
            res.add(t);
        return res;
    }
    
    /**
     *
     * @return
     */
    public boolean getTrabalhador(){
        return this.trabalhador;
    }
    
    /**
     *
     * @param horario
     */
    public void setHorario(ArrayList<String> horario){
        this.horario = horario;
    }
    
    /**
     *
     * @param r
     */
    public void setTrabalhador(boolean r){
        this.trabalhador = r;
    }
    
    /**
     *
     * @param t
     */
    public void adicionaTurno(String t){
        this.horario.add(t);
    }
   
    
    @Override
    public boolean equals(Object o){
        if(o==null || this.getClass()!=o.getClass()) return false;
        if(o == this) return true;
        else {
            Aluno s = (Aluno) o;
            return (this.getNome().equals(s.getNome()) &&
                    this.getPass().equals(s.getPass()) && this.getMail().equals(s.getMail()) 
                    && this.getTrabalhador() == s.getTrabalhador() );
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.trabalhador ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.horario);
        hash = 59 * hash + Objects.hashCode(this.trocas);
        return hash;
    }
    
    @Override
     public String toString() {
        StringBuffer sb = new StringBuffer("Aluno(");
        sb.append(this.getNome());
        sb.append(",");
        sb.append(this.getMail());
        sb.append(",");
        sb.append("Trabalhador: " +  this.getTrabalhador());
        sb.append(")");
        return sb.toString();
    }
     
    /**
     *
     * @param nomeuc
     * @return
     */
    public String getTurnoUC(String nomeuc){
        Iterator<String> it = this.horario.iterator();
        String uc, aux;
        String turnoI=null;
        boolean flag = false;
        while(it.hasNext() && !flag){
            aux = it.next();
            uc = aux.substring(0, aux.indexOf(" "));
            if(uc.equals(nomeuc)){
                flag = true;
                turnoI = aux.substring(aux.indexOf("-")+2, aux.length()-1);
            }
        }
        return turnoI; 
    }
    
    /**
     *
     * @param t
     */
    public void addTroca(Troca t){
        this.trocas.add(t);
    }
    
    /**
     *
     * @param codigoUC
     * @return
     */
    public int getTotalFaltas(String codigoUC){
        int res=0;
        for(Falta f: this.faltas){
            if(f.getCodigoUC().equals(codigoUC)){
                res++;
            }
        }
        return res;
    }
    
    /**
     *
     * @param uc
     * @param turno
     * @return
     */
    public Aluno setFaltas(String uc, String turno){
        LocalDate d = null;
        Falta f = new Falta(d.now(), uc, turno);
        this.faltas.add(f);
        return this;
    }
    
    /**
     *
     * @param nomeUC
     * @return
     */
    public Aluno removeDeUC(String nomeUC){
        Iterator<String> it = this.horario.iterator();
        String s, u;
        boolean flag=false;
        while(it.hasNext() && !flag){
            s = it.next();
            u = s.substring(0, s.indexOf(" ")-1);
            if(u.equals(nomeUC)){
                flag = true;
                this.horario.remove(s);
            }
        }
        return this;
    }
    
    /**
     *
     * @param codigoUC
     */
    public void removeDeFaltas(String codigoUC){
        Iterator<Falta> it = this.faltas.iterator();
        Falta f;
        boolean flag=false;
        while(it.hasNext() && !flag){
            f = it.next();
            if(f.getCodigoUC().equals(codigoUC)){
                this.faltas.remove(f);
                flag = true;
            }
        }
    }
}
