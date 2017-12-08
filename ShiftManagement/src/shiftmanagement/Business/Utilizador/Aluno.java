/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

import java.util.ArrayList;
/**
 *
 * @author Tiago
 */
public class Aluno extends Utilizador{
    
    private boolean trabalhador;
    private ArrayList<String> horario;
    
    public Aluno(){
        super();
        this.trabalhador = false;
    }
    
    public Aluno(String user, String nome, String mail, String pass, boolean trabalhador){
        super(user, nome, mail, pass);
        this.trabalhador = trabalhador;
    }
    
    public ArrayList<String> getHorario(){
        ArrayList<String> res = new ArrayList<>();
        for(String t: this.horario)
            res.add(t);
        return res;
    }
    
    public boolean getTrabalhador(){
        return this.trabalhador;
    }
    
    public void setHorario(ArrayList<String> horario){
        this.horario = horario;
    }
    
    public void setTrabalhador(boolean r){
        this.trabalhador = r;
    }
    
    public void adicionaTurno(String t){
        this.horario.add(t);
    }
   
    
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
}
