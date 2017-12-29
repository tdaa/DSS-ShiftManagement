/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import shiftmanagement.Business.Turno.Troca;
import shiftmanagement.Business.Utilizador.Aluno;
import shiftmanagement.Business.Utilizador.Falta;

/**
 *
 * @author Tiago
 */
public class AlunoDAO implements Map<String, Aluno>{

    private Connection con;

    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno WHERE idAluno = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            res = rs.next();
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        
        return res;
    }

    @Override
    public Aluno get(Object key) {
        Aluno a = new Aluno();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno WHERE idAluno = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setUsername(rs.getString("idAluno"));
                a.setNome(rs.getString("Nome"));
                a.setPassword(rs.getString("Password"));
                a.setMail(rs.getString("Email"));
                a.setTrabalhador(rs.getBoolean("Trabalhador"));
                
                ArrayList<String> turnos = new ArrayList<>();
                ps = con.prepareStatement("SELECT UC.Designaçao, Turno.idTurno FROM UC "
                        + "INNER JOIN Turno ON Turno.codigoUC = UC.codigoUC "
                        + "INNER JOIN Registo ON Registo.idTurno = Turno.idTurno AND Registo.codigoUC = Turno.codigoUC "
                        + "INNER JOIN Aluno ON Aluno.idAluno = Registo.idAluno "
                        + "WHERE Aluno.idAluno = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                String uc;
                while(rs.next()){
                    uc = rs.getString("Designaçao");
                    turnos.add(uc + " - " + rs.getString("idTurno"));
                }
                a.setHorario(turnos);
                
                ArrayList<Troca> trocas = new ArrayList<>();
                ps = con.prepareStatement("SELECT * FROM Troca "
                        + "INNER JOIN Aluno ON Aluno.idAluno = Troca.idAluno "
                        + "WHERE Aluno.idAluno = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Troca t;
                while(rs.next()){
                    t = new Troca();
                    t.setCodigoUC(rs.getString("codigoUC"));
                    t.setIdTurnoFinal(rs.getString("idTurnoDesejado"));
                    t.setIdTurnoInicial(rs.getString("idTurnoAtual"));
                    trocas.add(t);
                }
                a.setTrocas(trocas);
                
                ArrayList<Falta> faltas = new ArrayList<>();
                ps = con.prepareStatement("SELECT * FROM Falta "
                        + "INNER JOIN Aluno ON Aluno.idAluno = Falta.idAluno"
                        + " WHERE Aluno.idAluno = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Falta f;
                while(rs.next()){
                    f = new Falta();
                    f.setCodigoUC(rs.getString("codigoUC"));
                    f.setIdTurno(rs.getString("idTurno"));
                    faltas.add(f);
                }
                a.setFaltas(faltas);
            }
        } 
        catch(SQLException e){
            System.out.printf(e.getMessage());
        } 
        finally{
            try{
               Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return a;
    }
    
    @Override
    public Aluno put(String key, Aluno value) {
       Aluno a;
        
        if(this.containsKey(key)){
            a = this.get(key);
        }
        else a = value;
        
        try{
            con = Connect.connect();
            /*PreparedStatement ps = con.prepareStatement("DELETE FROM Aluno WHERE idAluno = ?");
            ps.setString(1, key);
            ps.executeUpdate();*/
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno WHERE Aluno.idAluno = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String mail = rs.getString("Email");
                String pass = rs.getString("Password");
                boolean t = rs.getBoolean("Trabalhador");
                String nome = rs.getString("Nome");
                
                if(!mail.equals(value.getMail()) || !pass.equals(value.getPass()) ||
                        t!=value.getTrabalhador() || !nome.equals(value.getNome())){
                    
                    ps = con.prepareStatement("DELETE FROM Aluno WHERE idAluno = ?");
                    ps.setString(1, key);
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("INSERT INTO Aluno (idAluno, Nome, Password, Email, Trabalhador) VALUES (?,?,?,?,?)");
                    ps.setString(1, key);
                    ps.setString(2, value.getNome());
                    ps.setString(3, value.getPass());
                    ps.setString(4, value.getMail());
                    ps.setBoolean(5, value.getTrabalhador());
                    ps.executeUpdate();
                }
            }          
            else{
                ps = con.prepareStatement("INSERT INTO Aluno (idAluno, Nome, Password, Email, Trabalhador) VALUES (?,?,?,?,?)");
                ps.setString(1, key);
                ps.setString(2, value.getNome());
                ps.setString(3, value.getPass());
                ps.setString(4, value.getMail());
                ps.setBoolean(5, value.getTrabalhador());
                ps.executeUpdate();
            }

            ArrayList<Troca> trocas = value.getTrocas();
            if(!trocas.isEmpty()){
                for(Troca t: trocas){
                    
                    ps = con.prepareStatement("SELECT * FROM Troca WHERE Troca.codigoUC = ? AND Troca.idTurnoAtual = ? AND Troca.idTurnoDesejado = ?");
                    ps.setString(1, t.getCodigoUC());
                    ps.setString(2, t.getIdTurnoInicial());
                    ps.setString(3, t.getIdTurnoFinal());
                    rs = ps.executeQuery();
                    
                    if(!rs.next()){
                        ps = con.prepareStatement("INSERT INTO Troca (idAluno, idTurnoAtual, idTurnoDesejado, codigoUC) VALUES (?,?,?,?)");
                        ps.setString(1, key);
                        ps.setString(2, t.getIdTurnoInicial());
                        ps.setString(3, t.getIdTurnoFinal());
                        ps.setString(4, t.getCodigoUC());
                        ps.executeUpdate();
                    }    
                }
                
                ps = con.prepareStatement("SELECT * FROM Troca WHERE Troca.idAluno = ?");
                ps.setString(1, key);
                rs = ps.executeQuery();
                boolean flag;
                while(rs.next()){
                    Iterator<Troca> it = trocas.iterator();
                    Troca t;
                    flag=false;
                    while(it.hasNext() && !flag){
                        t = it.next();
                        if(t.getCodigoUC().equals(rs.getString("codigoUC")) && t.getIdTurnoFinal().equals(rs.getString("idTurnoDesejado")) 
                                && t.getIdTurnoInicial().equals(rs.getString("idTurnoAtual"))){
                            
                            flag=true;
                        }     
                    }
                    if(!flag){
                        ps = con.prepareStatement("DELETE FROM Troca WHERE Troca.idTroca = ?");
                        ps.setInt(1, rs.getInt("idTroca"));
                        ps.executeUpdate();
                    }
                }
                    
                    /*for(Troca t: trocas){
                        if(!t.getCodigoUC().equals(rs.getString("codigoUC")) && !t.getIdTurnoFinal().equals(rs.getString("idTurnoDesejado")) 
                                && !t.getIdTurnoInicial().equals(rs.getString("idTurnoAtual"))){
                            
                            ps = con.prepareStatement("DELETE FROM Troca WHERE Troca.idTroca = ?");
                            ps.setInt(1, rs.getInt("idTroca"));
                            ps.executeUpdate();
                        }
                        if(t.getCodigoUC().equals(rs.getString("codigoUC")) && t.getIdTurnoFinal().equals(rs.getString("idTurnoDesejado")) 
                                && t.getIdTurnoInicial().equals(rs.getString("idTurnoAtual"))){
                            
                            break;
                        }
                        
                    }*/
                
                
            }
            else{
               ps = con.prepareStatement("SELECT * FROM Troca WHERE Troca.idAluno = ?");
               ps.setString(1, key);
               rs = ps.executeQuery();
               
               ps = con.prepareStatement("DELETE FROM Troca WHERE Troca.idTroca = ?");
               while(rs.next()){
                   ps.setInt(1, rs.getInt("idTroca"));
                   ps.executeUpdate();
               }
            }
           
            ArrayList<Falta> faltas = value.getFaltas();
            System.out.println(faltas.size());
            if(!faltas.isEmpty()){
                
                ps = con.prepareStatement("DELETE FROM Falta WHERE Falta.idAluno = ?");
                        ps.setString(1, key);
                        ps.executeUpdate();
                    
                for(Falta f: faltas){
                    
                    /*ps = con.prepareStatement("SELECT * FROM Falta WHERE Falta.codigoUC = ? AND Falta.idTurno = ? AND Falta.idAluno = ?"
                            + " AND Falta.idAluno=?");
                    ps.setString(1, f.getCodigoUC());
                    ps.setString(2, f.getIdTurno());
                    ps.setString(3, key);
                    rs = ps.executeQuery();*/
                    
                    //if(!rs.next()){
                        
                    
                        ps = con.prepareStatement("INSERT INTO Falta (codigoUC, idTurno, idAluno) VALUES (?,?,?)");
                        ps.setString(1, f.getCodigoUC());
                        ps.setString(2, f.getIdTurno());
                        ps.setString(3, key);
                        ps.executeUpdate();
                    //}
                    
                    /*ps = con.prepareStatement("SELECT * FROM Falta WHERE Falta.idAluno = ?");
                    ps.setString(1, key);
                    rs = ps.executeQuery();
                    boolean flag;
                    while(rs.next()){
                        System.out.println(rs.getString("codigoUC"));
                        System.out.println(rs.getString("idTurno"));
                        Iterator<Falta> it = faltas.iterator();
                        Falta f2;
                        flag=false;
                        while(it.hasNext() && !flag){
                            f2 = it.next();
                            System.out.println(f2.getCodigoUC());
                            System.out.println(f2.getIdTurno());
                            if(f2.getCodigoUC().equals(rs.getString("codigoUC")) && f2.getIdTurno().equals(rs.getString("idTurno"))){
                                System.out.println("entrou");
                                flag=true;
                            }     
                        }
                        if(!flag){
                            System.out.println("ok");
                            ps = con.prepareStatement("DELETE FROM Falta WHERE Falta.idFalta = ?");
                            ps.setInt(1, rs.getInt("idFalta"));
                            ps.executeUpdate();
                        }
                    }*/
                }
            }
            else{
               ps = con.prepareStatement("SELECT * FROM Falta WHERE Falta.idAluno = ?");
               ps.setString(1, key);
               rs = ps.executeQuery();
               
               ps = con.prepareStatement("DELETE FROM Falta WHERE Falta.idFalta = ?");
               while(rs.next()){
                   ps.setInt(1, rs.getInt("idFalta"));
                   ps.executeUpdate();
               }
            }
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
               Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        
        return a;
    }

    @Override
    public Aluno remove(Object key) {
        Aluno a = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Aluno WHERE idAluno = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
        } catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
               Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return a;
    }

    @Override
    public void clear() {
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE * FROM Aluno");
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> set = null;
        
        try{
            con = Connect.connect();
            set = new HashSet<>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("idAluno"));
            }   
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return set;
    }

    @Override
    public Collection<Aluno> values() {
        Set<Aluno> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, Aluno>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String,Aluno> map = new HashMap<>();
        keys.forEach((key) -> {
            map.put(key,this.get(key));
        });
        return map.entrySet();
    }

    @Override
    public int size() {
        int size = -1;

        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Aluno");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.printf(e.getMessage());
        }
        finally{
            try{
                Connect.close(con);
            }
            catch(Exception e){
                System.out.printf(e.getMessage());
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Aluno> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
