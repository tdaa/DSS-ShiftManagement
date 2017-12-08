/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import shiftmanagement.database.UcPerfilDAO;

/**
 *
 * @author Tiago
 */
public class Perfil {
    
    private String nome;
    private UcPerfilDAO listaUcs;
    
    public Perfil(){
        this.nome = "";
        this.listaUcs = new UcPerfilDAO();
    }
    
    public Perfil(String nome){
        this.nome = nome;
        this.listaUcs = new UcPerfilDAO();
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String n){
        this.nome = n;
    }
}
