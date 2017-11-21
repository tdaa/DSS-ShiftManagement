/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement;

/**
 *
 * @author Tiago
 */
public class Sala {
    
    private final int maxLugares;
    private final String nomeSala;
    
    public Sala(){
        this.maxLugares = 0;
        this.nomeSala = "";
    }
    
    public Sala(int n, String s){
        this.maxLugares = n;
        this.nomeSala = s;
    }
}
