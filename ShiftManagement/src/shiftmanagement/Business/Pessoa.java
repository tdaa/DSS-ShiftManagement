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
public class Pessoa{
    
    private String username;
    private String nome;
    private String numero;
    private String mail;
    private String password;
    
    public Pessoa(){
        this.username = "";
        this.nome = "";
        this.numero = "";
        this.mail = "";
        this.password = "";
    }
    
    public Pessoa(String user, String nome, String numero, String mail, String pass){
        this.username = user;
        this.nome = nome;
        this.numero = numero;
        this.mail = mail;
        this.password = pass;
    }
    
    public String getUser(){
        return this.username;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getNumero(){
        return this.numero;
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
        sb.append(this.numero);
        sb.append(",");
        sb.append(this.mail);
        sb.append(")");
        return sb.toString();
    }
}
