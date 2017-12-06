/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.Utilizador;

/**
 *
 * @author Tiago
 */
public class Utilizador{
    
    private String username; 
    private String nome;
    private String mail;
    private String password;
    
    public Utilizador(){
        this.nome = "";
        this.mail = "";
        this.password = "";
    }
    
    public Utilizador(String username, String nome, String mail, String pass){
        this.username = username;
        this.nome = nome;
        this.mail = mail;
        this.password = pass;
    }
    
    public String getUsername(){
       return this.username;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getMail(){
        return this.mail;
    }
    
    public String getPass(){
        return this.password;
    }
    
   
    
    public String toString() {
        StringBuffer sb = new StringBuffer("Aluno(");
        sb.append(this.nome);
        sb.append(",");
        sb.append(this.mail);
        sb.append(")");
        return sb.toString();
    }
}
