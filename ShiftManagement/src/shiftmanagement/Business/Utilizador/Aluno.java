/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

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
    
    /**
     *
     */
    public Aluno(){
        super();
        this.trabalhador = false;
        this.horario = new ArrayList<>();
        this.trocas = new ArrayList<>();
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
    
    public void addTroca(Troca t){
        this.trocas.add(t);
    }
}
