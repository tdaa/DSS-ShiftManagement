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
import java.util.Map;
import java.util.Set;
import shiftmanagement.Business.Utilizador.Aluno;

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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno WHERE Username = ?");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Aluno WHERE Username = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setUsername(rs.getString("Username"));
                a.setNome(rs.getString("Nome"));
                a.setPassword(rs.getString("Password"));
                a.setMail(rs.getString("Mail"));
                a.setTrabalhador(rs.getBoolean("Trabalhador"));
                
                ArrayList<String> turnos = new ArrayList<>();
                ps = con.prepareStatement("SELECT UC.Designaçao, Turno.idTurno FROM UC "
                        + "INNER JOIN Turno ON Turno.codigoUC = UC.codigoUC "
                        + "INNER JOIN Registo ON Registo.idTurno = Turno.idTurno "
                        + "INNER JOIN Aluno ON Aluno.idAluno = Registo.idAluno");
                rs = ps.executeQuery();
                String uc;
                while(rs.next()){
                    uc = rs.getString("Designaçao");
                    turnos.add(uc + " - " + rs.getString("idTurno"));
                }
                a.setHorario(turnos);
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM Aluno WHERE Username = ?");
            ps.setString(1, key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO Aluno (Username, Nome, Password, Email, Trabalhador) VALUES (?,?,?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getPass());
            ps.setString(4, value.getMail());
            ps.setBoolean(5, value.getTrabalhador());
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
