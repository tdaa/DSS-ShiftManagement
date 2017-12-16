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
import java.util.ArrayList;
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
import shiftmanagement.Business.UC.UCComplementar;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class UcCompDAO implements Map<String, UCComplementar>{
    
    private Connection con;
    
    
    @Override
    public int size() {
        int size = -1;

        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM UCComplementar");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCComplementar WHERE codigoUCComp = ?");
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
    public UCComplementar get(Object key) {
        UCComplementar uc = new UCComplementar();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UC "
                    + "INNER JOIN UCComplementar AS UCC ON UCC.codigoUCComp = UC.codigoUC "
                    + "WHERE UCC.codigoUCComp = ?");
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
                
                HashSet<Professor> profs = new HashSet<>();
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
                uc.setDocentes(profs);
            }
            ps = con.prepareStatement("SELECT * FROM UCComplementar AS UCC"
                    + " INNER JOIN UC ON UC.codigoUC = UCC.codigoUCComp"
                    + " WHERE UCC.codigoUCComp = ?");
            ps.setString(1, (String) key);
            rs = ps.executeQuery();
            if(rs.next()){
                uc.setDiaS(rs.getString("diaSemana"));
                uc.setPer(rs.getString("Per"));
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
    public UCComplementar put(String key, UCComplementar value) {
        UCComplementar uc;
       
        if(this.containsKey(key))
            uc = this.get(key);
        
        else uc = value;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCComplementar WHERE codigoUCComp = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UC (codigoUC, Designaçao, usernameRegente) VALUES (?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getResponsavel());
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UCComplementar (codigoUCComp, dia Semana, Per) VALUES (?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getDiaS());
            ps.setString(3, value.getPer());
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
                }
            }
            
            HashSet<Professor> profs = value.getEquipaDocente();
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
    public UCComplementar remove(Object key) {
        UCComplementar uc = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCComplementar WHERE codigoUCComp = ?");
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
            PreparedStatement ps = con.prepareStatement("DELETE * FROM UCComplementar");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCComplementar");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("codigoUCComp"));
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
    public Collection<UCComplementar> values() {
        Set<UCComplementar> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, UCComplementar>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String, UCComplementar> map = new HashMap<>();
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
    public void putAll(Map<? extends String, ? extends UCComplementar> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
