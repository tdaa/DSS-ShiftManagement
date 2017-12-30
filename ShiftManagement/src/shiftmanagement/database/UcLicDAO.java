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
import java.sql.Time;
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
import shiftmanagement.Business.UC.UCLicenciatura;

/**
 *
 * @author Tiago
 */
public class UcLicDAO implements Map<String, UCLicenciatura>{
    
    private Connection con;
  

    @Override
    public int size() {
        int size = -1;

        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM UCLicenciatura");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCLicenciatura WHERE codigoUC = ?");
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
    public UCLicenciatura get(Object key) {
        UCLicenciatura uc = new UCLicenciatura();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UC "
                    + "INNER JOIN UCLicenciatura AS UCL ON UCL.codigoUC = UC.codigoUC "
                    + "WHERE UCL.codigoUC = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                uc.setNome(rs.getString("Designaçao"));
                uc.setCodigo(rs.getString("codigoUC"));
                uc.setResponsavel(rs.getString("usernameRegente"));
                
                HashSet<Turno> turnos = new HashSet<>();
                ps = con.prepareStatement("SELECT Sala.idSala, Sala.MaxLugares, Turno.* FROM Turno"
                        + " INNER JOIN Sala ON Sala.idSala = Turno.idSala"
                        + " INNER JOIN UC ON UC.codigoUC = Turno.codigoUC"
                        + " WHERE UC.codigoUC = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Turno t;
                int max;
                while(rs.next()){
                    //t = new Turno();
                    Sala s = new Sala();
                    s.setMax(rs.getInt("MaxLugares"));
                    s.setNomeSala(rs.getString("idSala"));
                    Time h = rs.getTime("Hora");
                    String id = rs.getString("idTurno");
                    String prof = rs.getString("UsernameProf");
                    String uct = rs.getString("codigoUC");
                    String d = rs.getString("diaSemana");
                    int al=-1;
                    PreparedStatement ps2 = con.prepareStatement("SELECT DISTINCT Registo.idAluno, COUNT(*) AS `Total` FROM Registo"
                            + " WHERE idTurno = ?"
                            + " GROUP BY Registo.idAluno");
                    ps2.setString(1, id);
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        al = rs2.getInt("Total");
                    }
                    
                    int x = rs.getInt("aulas");
                    max = rs.getInt("maxAlunos");
                    if(rs.getString("Tipo").equals("TP")){
                        t = new TP(id, max, s, prof, h, uct, x, d, al);
                        //TP tp = (TP) t;
                        turnos.add(t);
                    }
                    if(rs.getString("Tipo").equals("PL")){
                        //PL pl = (PL) t;
                        t = new PL(id, max, s, prof, h, uct, x, d, al);
                        turnos.add(t);
                    }
                    if(rs.getString("Tipo").equals("Teorica")){
                        t = new Teorica(id, s, prof, h, uct, x, d);
                        //Teorica teo = (Teorica) t;
                        turnos.add(t);
                    }
                }
                uc.setTurnos(turnos);
                
                /*HashSet<Professor> profs = new HashSet<>();
                ps = con.prepareStatement("SELECT * FROM Professor AS P"
                        + " INNER JOIN Turno ON Turno.UsernameProf = P.Username"
                        + " INNER JOIN UC ON UC.codigoUC = Turno.codigoUC"
                        + " INNER JOIN UCLicenciatura AS UCL ON UCL.codigoUC = UC.codigoUC"
                        + " WHERE UCL.codigoUC = ?");
                ps.setString(1, (String) key);
                rs = ps.executeQuery();
                Professor p; 
                while(rs.next()){
                    p = new Professor();
                    p.setNome(rs.getString("Username"));
                    p.setNome(rs.getString("Nome"));
                    p.setMail(rs.getString("Email"));
                    p.setPassword(rs.getString("Password"));
                    p.setRegente(rs.getBoolean("Regente"));
                    profs.add(p);
                }
                uc.setDocentes(profs);*/
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
    public UCLicenciatura put(String key, UCLicenciatura value) {
        UCLicenciatura uc;
        if(this.containsKey(key))
            uc = this.get(key);
        
        else uc = value;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCLicenciatura WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UC (codigoUC, Designaçao, usernameRegente) VALUES (?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getResponsavel());
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO UCLicenciatura (codigoUC) VALUES (?)");
            ps.setString(1, key);
            ps.executeUpdate();
            
            HashSet<Turno> turnos = value.getTurnos();
            
            if(turnos.size() > 0){
                for(Turno t: turnos){
            
                    /*PreparedStatement ps2 = con.prepareStatement("DELETE FROM Sala WHERE Sala.idSala = ?");
                    ps2.setString(1, t.getSala().getNome());
                    ps2.executeUpdate();*/
                    
                    PreparedStatement ps2 = con.prepareStatement("SELECT Sala.idSala FROM Sala Where Sala.idSala = ?");
                    ps2.setString(1, t.getSala().getNome());
                    ResultSet rs = ps2.executeQuery();
                    if(!rs.next()){
                        ps2 = con.prepareStatement("INSERT INTO Sala(idSala, MaxLugares) VALUES (?,?)");
                        ps2.setString(1, t.getSala().getNome());
                        ps2.setInt(2, t.getSala().getMax());
                        ps2.executeUpdate();
                    }
                    
                    
                    
                    /*ps2 = con.prepareStatement("DELETE FROM Turno WHERE Turno.idTurno = ? AND Turno.codigoUC = ?");
                    ps2.setString(1, t.getId());
                    ps2.setString(2, key);
                    ps2.executeUpdate();*/
                    
                    ps2 = con.prepareStatement("INSERT INTO Turno (idTurno, codigoUC, Hora, idSala, UsernameProf, maxAlunos, Tipo, Aulas, diaSemana) VALUES (?,?,?,?,?,?,?,?,?)"
                            + " ON DUPLICATE KEY UPDATE idTurno=VALUES(idTurno), codigoUC=VALUES(codigoUC), Hora=VALUES(Hora),"
                            + " idSala=VALUES(idSala), UsernameProf=VALUES(UsernameProf), maxAlunos=VALUES(maxAlunos),"
                            + " Tipo=VALUES(Tipo), Aulas=VALUES(Aulas), diaSemana=VALUES(diaSemana)");
                    ps2.setString(1, t.getId());
                    ps2.setString(2, t.getUc());
                    ps2.setTime(3, t.getHora());
                    ps2.setString(4, t.getSala().getNome());
                    ps2.setString(5, t.getProf());
                    ps2.setInt(8, t.getNumeroAulas());
                    ps2.setString(9, t.getDia());
                    if(t instanceof TP){
                        TP tp = (TP) t;
                        ps2.setInt(6, tp.getMax());
                        ps2.setString(7, "TP");                      
                    }
                    if(t instanceof PL){
                        PL pl = (PL) t;
                        ps2.setInt(6, pl.getMax());
                        ps2.setString(7, "PL");
                    }
                    if(t instanceof Teorica){
                        Teorica teo = (Teorica) t;
                        ps2.setInt(6, NULL);
                        ps2.setString(7, "Teorica");
                    }
                    ps2.executeUpdate();
                    
                 
                }
            }
           
            /*HashSet<Professor> profs = this.get(key).getEquipaDocente();
             System.out.println("depois do antes: " + profs.size());
            if(profs.size()>0){
                for(Professor p: profs){
                    //System.out.println(p.getUsername());
                    ps = con.prepareStatement("DELETE FROM Professor WHERE Professor.Username = ?");
                    ps.setString(1, p.getUsername());
                    ps.executeUpdate();
                    
                    ps = con.prepareStatement("INSERT INTO Professor (Username, Nome, Email, Password, Regente) VALUES (?,?,?,?,?)");
                    ps.setString(1, p.getUsername());
                    ps.setString(2, p.getNome());
                    ps.setString(3, p.getMail());
                    ps.setString(4, p.getPass());
                    ps.setBoolean(5, p.getRegente());
                    ps.executeUpdate();
                    System.out.println("depois do update: " + this.get(key).getEquipaDocente().size());
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
    public UCLicenciatura remove(Object key) {
        UCLicenciatura uc = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM UC WHERE codigoUC = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("DELETE FROM UCLicenciatura WHERE codigoUC = ?");
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
            PreparedStatement ps = con.prepareStatement("DELETE * FROM UCLicenciatura");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UCLicenciatura");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("codigoUC"));
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
    public Collection<UCLicenciatura> values() {
        Set<UCLicenciatura> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, UCLicenciatura>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String, UCLicenciatura> map = new HashMap<>();
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
    public void putAll(Map<? extends String, ? extends UCLicenciatura> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
