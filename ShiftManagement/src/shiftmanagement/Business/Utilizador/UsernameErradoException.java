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
class UsernameErradoException extends Exception {
    
    public UsernameErradoException(){
        super();
    }
    
    public UsernameErradoException(String m){
        super(m);
    }
}
