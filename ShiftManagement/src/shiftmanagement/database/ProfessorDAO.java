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
import shiftmanagement.Business.Turno.Sala;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class ProfessorDAO implements Map<String, Professor>{

    private Connection con;
   
    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Professor WHERE Username = ?");
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
    public Professor get(Object key) {
        Professor p = new Professor();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Professor WHERE Username = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p.setUsername(rs.getString("Username"));
                p.setNome(rs.getString("Nome"));
                p.setMail(rs.getString("Mail"));
                p.setPassword(rs.getString("Password"));
                p.setRegente(rs.getBoolean("Regente"));
                
                ArrayList<Turno> turnos = new ArrayList<>();
                ps = con.prepareStatement("SELECT * FROM Turno WHERE UsernameProf = ?");
                ps.setString(1,(String) key);
                rs = ps.executeQuery();
                Turno t = new Turno();
                PreparedStatement ps2;
                while(rs.next()){                   
                    ps2 = con.prepareStatement("SELECT * FROM Sala INNER JOIN Turno ON Turno.idSala = Sala.idSala "
                            + "INNER JOIN Professor ON Professor.Username = Turno.UsernameProf WHERE Turno.idTurno = ?");
                    ps2.setString(1, rs.getString("idTurno"));
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        Sala s = new Sala();
                        s.setNomeSala(rs2.getString("idSala"));
                        s.setMax(rs2.getInt("MaxLugares"));
                        t.setSala(s);
                    }
                    t.setHora(rs.getTime("Hora"));
                    t.setId(rs.getString("idTurno"));
                    t.setProf(rs.getString("UsernameProf"));
                    turnos.add(t);
                }
                p.setTurnos(turnos);
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
        return p;
    }

    @Override
    public Professor put(String key, Professor value) {
        Professor p;
        
        if(this.containsKey(key)){
            p = this.get(key);
        }
        else p = value;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Professor WHERE Username = ?");
            ps.setString(1, key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO Professor (Username, Nome, Email, Password, Regente) VALUES (?,?,?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getMail());
            ps.setString(4, value.getPass());
            ps.setBoolean(5, value.getRegente());
            ps.executeUpdate();
            
            ArrayList<Turno> turnos = value.getTurnos();
            if(turnos != null){
                for(Turno t : turnos){
                        ps = con.prepareStatement("INSERT INTO Turno (id, codigoUC, Hora, idSala, UsernameProf) VALUES (?,?,?,?,?)");
                        ps.setString(1, t.getId());
                        ps.setString(2, t.getUc());
                        ps.setTime(3, t.getHora());
                        ps.setString(4, t.getSala().getNome());
                        ps.setString(5, t.getProf());
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
        
        return p;
    }

    @Override
    public Professor remove(Object key) {
        Professor p = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Professor WHERE Username = ?");
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
        return p;
    }

    @Override
    public void clear() {
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE * FROM Professor");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Professor");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("Username"));
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
    public Collection<Professor> values() {
        Set<Professor> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, Professor>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String,Professor> map = new HashMap<>();
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
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Professor");
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
    public void putAll(Map<? extends String, ? extends Professor> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
