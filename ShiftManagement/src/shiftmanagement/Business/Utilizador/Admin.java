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
public class Admin extends Utilizador{
    
    private final String username = "admin";
    private final String password = "dir1718";
    
    public Admin(){}
    
    public Admin verificaDados(String username, String pass) throws UsernameErradoException, PassErradaException{
        if(username.equals(this.username)){
            if(pass.equals(this.password)) return this;
            throw new PassErradaException("Password Incorreta!");
        }
        else{
            throw new UsernameErradoException("Username Não Existe!");
        }
    }
}
