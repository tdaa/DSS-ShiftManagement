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
public class ShiftManagement {

    private Aluno aluno;
    private Professor professor;
    private Admin admin;
    //TODO
    //listaUCs
    //listaAlunos
    //listaProfessores
    
    public ShiftManagement(){
        aluno = null;
        professor = null;
        admin = null;
    }
    
    public ShiftManagement(ShiftManagement s){
        aluno = s.getAluno();
        professor = s.getProfessor();
        admin = s.getAdmin();
    }
    
    public ShiftManagement(Aluno a, Professor p, Admin ad){
        aluno = a;
        professor = p;
        admin = ad;
    }
    
    public Aluno getAluno(){
        return aluno;
    }
    
    public Professor getProfessor(){
        return professor;
    }
    
    public Admin getAdmin(){
        return admin;
    }
    
    public void iniciaSessao(String username, String pass, int id) throws UsernameErradoException{
        try{
            if(id == 1){
                this.admin.verificaDados(username, pass);
            }
            if(id == 2){
                this.aluno.verificaDados(username, pass);
            }
            if(id == 3){
                this.professor.verificaDados(username, pass);
            }
        }
        catch(Exception e){
            throw new UsernameErradoException(e.getMessage());
        }
    }
    
    public String getCodigoUc(String nomeUc){
        for(Map.Entry<String, UC,)
        this.listaUcs.
    }

}
