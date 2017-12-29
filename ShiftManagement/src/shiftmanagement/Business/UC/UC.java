/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.sql.Time;
import java.util.HashSet;
import java.util.Iterator;
import shiftmanagement.Business.Turno.PL;
import shiftmanagement.Business.Turno.TP;
import shiftmanagement.Business.Turno.Teorica;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class UC {
    
    private String nome;
    private String codigo;
    private String usernameResponsavel;
    private HashSet<Turno> turnos;
    //private HashSet<Professor> equipaDocente;
    
    
    public UC(){
        this.nome = "";
        this.codigo = "";
        this.usernameResponsavel = null;
        this.turnos = new HashSet<>();
        //this.equipaDocente = new HashSet<>();
    }
    
    public UC(String n, String cod){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = null;
        this.turnos = new HashSet<>();
        //this.equipaDocente = new HashSet<>();
    }
    
    public UC(String n, String cod, String p, HashSet<Professor> equipaDocente){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = p;
        //this.equipaDocente = equipaDocente;
    }
    
    public UC(String n, String cod, String p, HashSet<Turno> turnos, HashSet<Professor> docentes){
        this.nome = n;
        this.codigo = cod;
        this.usernameResponsavel = p;
        this.turnos = turnos;
        //this.equipaDocente = docentes;
    }
    
    /**
     *
     * @return
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     *
     * @return
     */
    public String getResponsavel(){
        return this.usernameResponsavel;
    }
    
    /**
     *
     * @return
     */
    public HashSet<Turno> getTurnos(){
       HashSet<Turno> lista = new HashSet<>();
       try{
       for(Turno t : this.turnos)
           lista.add(t);
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
       return lista;
    }
    
    /**
     *
     * @return
     */
    public String getCodigo(){
        return this.codigo;
    }
    
    /**
     *
     * @return
     */
    public HashSet<String> getEquipaDocente(){
        HashSet<String> lista = new HashSet<>();
        for(Turno t: this.turnos){
            lista.add(t.getProf());
        }
        return lista;
    }
     
    /**
     *
     * @param n
     */
    public void setNome(String n){
        this.nome = n;
    }
    
    /**
     *
     * @param c
     */
    public void setCodigo(String c){
        this.codigo = c;
    }
    
    /**
     *
     * @param p
     */
    public UC setResponsavel(String p){
        this.usernameResponsavel = p;
        return this;
    }
    
    /**
     *
     * @param t
     */
    public void setTurnos(HashSet<Turno> t){
        this.turnos = t;
    }
    
    /**
     *
     * @param profs
     */
    /*public void setDocentes(HashSet<Professor> profs){
        this.equipaDocente = profs;
    }*/

    /**
     *
     * @param t
     */
    public UC addTurno(Turno t){
        if(t instanceof Teorica && !(this.turnos.contains("Teorica")))
            this.turnos.add(t);
        if(! (t instanceof Teorica)) this.turnos.add(t);
        return this;
    }
    
    /**
     *
     * @param turno
     */
    public void ripTurno(String turno){
        Turno t = getTurno(turno);
        this.getTurnos().remove(t);
    }
    
    /**
     *
     * @param turno
     * @return
     */
    public Turno getTurno(String turno){
        for(Turno t: this.getTurnos()){
            if(t.getId().equals(turno)) return t;
        }
        return null;
    }
    
    /**
     *
     * @param p
     */
    /*public UC addProfToDocentes(Professor p){
        if(p!=null){
            this.equipaDocente.add(p);
        }
        return this;
    }*/
    
    /**
     *
     * @param p
     */
    /*public void removeDeDocentes(Professor p){
        this.equipaDocente.remove(p);
    }*/
    
    /**
     *
     * @param turno
     */
    public void corrigeIdTurnos(String turno){
        int idT;
        int id = Integer.parseInt(Character.toString(turno.charAt(turno.length())));
        Iterator<Turno> it = this.getTurnos().iterator();
        Turno t;
        while(it.hasNext()){
            t = it.next();
            if(t.getClass().equals("PL") || t.getClass().equals("TP")){
                idT = Integer.parseInt(Character.toString(t.getId().charAt(t.getId().length())));
                if(idT > id){
                    String newId = t.getId().substring(0, 1) + Integer.toString(id + (idT-id));
                    t.setId(newId);
                }
            }
        }
    }
    
    /**
     *
     * @param turno
     * @return Numero de aulas.
     */
    public int getNAulas(String turno){
        int res=-1;
        Iterator<Turno> it = this.turnos.iterator();
        Turno t;
        boolean flag=false;
        while(it.hasNext() && !flag){
            t = it.next();
            if(t.getId().equals(turno)){
                flag=true;
                res = t.getNumeroAulas();
            }
        }
        return res;
    }

    
    public void daHora(String turno, Time h, String dia){
        Iterator<Turno> it = this.turnos.iterator();
        Turno t;
        boolean flag=false;
        while(it.hasNext() && !flag){
            t = it.next();
            if(t.getId().equals(turno)){
                flag=true;
                t.setHora(h);
                t.setDia(dia);
            }
        }
    }
    
    /**
     *
     * @param turno
     * @return capacidade da sala.
     */
    public int getCapacidadeSala(String turno){
        int res=-1;
        Iterator<Turno> it = this.turnos.iterator();
        Turno t;
        boolean flag = false;
        while(it.hasNext() && !flag){
            t = it.next();
            if(t.getId().equals(turno)){
                flag = true;
                res = t.getSala().getMax();
            }
        }
        return res;
    }
    
    public int getInscritos(String turno){
        int res=-1;
        Iterator<Turno> it = this.turnos.iterator();
        Turno t;
        boolean flag = false;
        while(it.hasNext() && !flag){
            t = it.next();
            if(t.getId().equals(turno)){
                flag = true;
                if(t instanceof PL){
                    PL pl = (PL) t;
                    res = pl.getAlunos();                  
                }
                if(t instanceof TP){
                    TP tp = (TP) t;
                    res = tp.getAlunos();                  
                }
            }
        }
        return res;
    }
    
    public int getMaximo(String turno){
        int res=-1;
        Iterator<Turno> it = this.turnos.iterator();
        Turno t;
        boolean flag = false;
        while(it.hasNext() && !flag){
            t = it.next();
            if(t.getId().equals(turno)){
                flag = true;
                if(t instanceof PL){
                    PL pl = (PL) t;
                    res = pl.getMax();                  
                }
                if(t instanceof TP){
                    TP tp = (TP) t;
                    res = tp.getMax();                  
                }
            }
        }
        return res;
    }
}
