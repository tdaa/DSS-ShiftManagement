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
import static java.sql.Types.NULL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import shiftmanagement.Business.Turno.PL;
import shiftmanagement.Business.Turno.Sala;
import shiftmanagement.Business.Turno.TP;
import shiftmanagement.Business.Turno.Teorica;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.UC.UCPerfil;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class UcPerfilDAO implements Map<String,UCPerfil>{
    
    private Connection con;
  
    
    public UcPerfilDAO(){}

    @Override
    public int size() {
        int size = -1;

        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM UCPerfil");
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
    public boolean containsKey(Object key) {
        boolean res = false;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCPerfil WHERE codigoUCPerfil = ?");
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
    public UCPerfil get(Object key) {
        UCPerfil uc = new UCPerfil();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UC "
                    + "INNER JOIN UCPerfil AS UCP ON UCP.codigoUCPerfil = UC.codigoUC "
                    + "WHERE UCP.codigoUCPerfil = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                uc.setNome(rs.getString("Designaçao"));
                uc.setCodigo(rs.getString("codigoUC"));
                uc.setResponsavel(rs.getString("usernameRegente"));
                
                HashSet<Turno> turnos = new HashSet<>();
                ps = con.prepareStatement("SELECT Sala.idSala, Sala.MaxLugares, Turno.* FROM Turno"
                        + " INNER JOIN UC ON UC.codigoUC = Turno.codigoUC"
                        + " WHERE UC.codigoUC = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Turno t;
                int max;
                while(rs.next()){
                    t = new Turno();
                    Sala s = new Sala();
                    s.setMax(rs.getInt("MaxLugares"));
                    s.setNomeSala(rs.getString("idSala"));
                    t.setSala(s);
                    t.setHora(rs.getTime("Hora"));
                    t.setId(rs.getString("idTurno"));
                    t.setProf(rs.getString("UsernameProf"));
                    t.setUc(rs.getString("codigoUC"));
                    max = rs.getInt("maxAlunos");
                    if(rs.getString("Tipo").equals("TP")){
                        TP tp = (TP) t;
                        tp.setMax(max);
                        turnos.add(tp);
                    }
                    if(rs.getString("Tipo").equals("PL")){
                        PL pl = (PL) t;
                        pl.setMax(max);
                        turnos.add(pl);
                    }
                    if(rs.getString("Tipo").equals("Teorica")){
                        Teorica teo = (Teorica) t;
                        turnos.add(teo);
                    }
                }
                uc.setTurnos(turnos);
                
                /*HashSet<Professor> profs = new HashSet<>();
                ps = con.prepareStatement("SELECT * FROM Professor AS P"
                        + " INNER JOIN Turno ON Turno.UsernameProf = P.Username"
                        + " INNER JOIN UC ON UC.codigoUC = Turno.codigoUC"
                        + " WHERE UC.codigoUC = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Professor p; 
                while(rs.next()){
                    p = new Professor();
                    p.setNome(rs.getString("Username"));
                    p.setNome(rs.getString("Nome"));
                    p.setMail(rs.getString("Mail"));
                    p.setPassword(rs.getString("Password"));
                    p.setRegente(rs.getBoolean("Regente"));
                    profs.add(p);
                }
                uc.setDocentes(profs);*/
            }
            ps = con.prepareStatement("SELECT * FROM UCPerfil AS UCP"
                    + " INNER JOIN UC ON UC.codigoUC = UCP.codigoUCPerfil"
                    + " WHERE UCP.codigoUCPerfil = ?");
            ps.setString(1, (String) key);
            rs = ps.executeQuery();
            if(rs.next()){
                uc.setDiaS(rs.getString("diaSemana"));
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
        return uc;
    }

    @Override
    public UCPerfil put(String key, UCPerfil value) {
        UCPerfil uc;
       
        if(this.containsKey(key))
            uc = this.get(key);
        
        else uc = value;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCPerfil WHERE codigoUCPerfil = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UC (codigoUC, Designaçao, usernameRegente) VALUES (?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getResponsavel());
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UCPerfil (codigoUCPerfil, dia Semana) VALUES (?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getDiaS());
            ps.executeUpdate();
            
            HashSet<Turno> turnos = value.getTurnos();
            if(turnos != null){
                for(Turno t: turnos){
                    ps = con.prepareStatement("INSERT INTO Turno (idTurno, codigoUC, Hora, idSala, UsernameProf, maxAlunos, Tipo) VALUES (?,?,?,?,?,?,?)");
                    ps.setString(1, t.getId());
                    ps.setString(2, t.getUc());
                    ps.setTime(3, t.getHora());
                    ps.setString(4, t.getSala().getNome());
                    ps.setString(5, t.getProf());
                    if(t instanceof TP){
                        TP tp = (TP) t;
                        ps.setInt(6, tp.getMax());
                        ps.setString(7, "TP");
                    }
                    if(t instanceof PL){
                        PL pl = (PL) t;
                        ps.setInt(6, pl.getMax());
                        ps.setString(7, "PL");
                    }
                    if(t instanceof Teorica){
                        Teorica teo = (Teorica) t;
                        ps.setInt(6, NULL);
                        ps.setString(7, "Teorica");
                    }
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("INSERT INTO Sala(idSala, MaxLugares) VALUES (?,?)");
                    ps.setString(1, t.getSala().getNome());
                    ps.setInt(2, t.getSala().getMax());
                    ps.executeUpdate();
                }
            }
            
            /*HashSet<Professor> profs = value.getEquipaDocente();
            if(profs!=null){
                for(Professor p: profs){
                    ps = con.prepareStatement("INSERT INTO Professor (Username, Nome, Email, Password, Regente) VALUES (?,?,?,?,?)");
                    ps.setString(1, p.getUsername());
                    ps.setString(2, p.getNome());
                    ps.setString(3, p.getMail());
                    ps.setString(4, p.getPass());
                    ps.setBoolean(5, p.getRegente());
                    ps.executeUpdate();
                }
            }*/
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
        return uc;
    }

    @Override
    public UCPerfil remove(Object key) {
        UCPerfil uc = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCPerfil WHERE codigoUCPerfil = ?");
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
        return uc;
    }

    @Override
    public void clear() {
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE * FROM UCPerfil");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCPerfil");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("codigoUCPerfil"));
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
    public Collection<UCPerfil> values() {
        Set<UCPerfil> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, UCPerfil>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String, UCPerfil> map = new HashMap<>();
        keys.forEach((key) -> {
            map.put(key,this.get(key));
        });
        return map.entrySet();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends UCPerfil> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
