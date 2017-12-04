/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business;

/**
 *
 * @author Tiago
 */
public class Curso {
    
    private String tipo;
    
    public Curso(){
        tipo = "";
    }
    
    public Curso(String t){
        this.tipo = t;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String s){
        this.tipo = s;
    }
}
