/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Turno;

/**
 *
 * @author Tiago
 */
public class Sala {
    
    private int maxLugares;
    private String nomeSala;
    
    public Sala(){
        this.maxLugares = 0;
        this.nomeSala = "";
    }
    
    public Sala(int n, String s){
        this.maxLugares = n;
        this.nomeSala = s;
    }
    
    public String getNome(){
        return this.nomeSala;
    }
    
    public void setNomeSala(String n){
        this.nomeSala = n;
    }
    
    public void setMax(int m){
        this.maxLugares = m;
    }
    
    public int getMax(){
        return this.maxLugares;
    }
    
    
}
