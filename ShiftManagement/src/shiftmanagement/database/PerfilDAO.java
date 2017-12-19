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
import shiftmanagement.Business.UC.Perfil;
import shiftmanagement.Business.UC.Registo;
import shiftmanagement.Business.UC.UCPerfil;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class PerfilDAO implements Map<String, Perfil>{

    private Connection con;
    
    @Override
    public int size() {
        int size = -1;

        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Perfil");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Perfil WHERE idPerfil = ?");
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
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Perfil get(Object key) {
        Perfil p = new Perfil();
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Perfil WHERE idPerfil = ?");
            ps.setString(1, (String) key);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p.setNome(rs.getString("idPerfil"));
            }
            
            Map<String, UCPerfil> ucs = new HashMap<String, UCPerfil>();
            ps = con.prepareStatement("SELECT UCPerfil.*, UC.* FROM UCPerfil "
                    + "INNER JOIN Perfil ON Perfil.idPerfil = UCPerfil.idPerfil"
                    + " INNER JOIN UC ON UC.codigoUC = UCPerfil.codigoUCPerfil"
                    + " WHERE Perfil.idPerfil = ?");
            ps.setString(1, (String) key);
            rs = ps.executeQuery();
            UCPerfil uc;
            while(rs.next()){
                uc = new UCPerfil();
                uc.setCodigo(rs.getString("codigoUCPerfil"));
                uc.setDiaS(rs.getString("diaSemana"));
                uc.setNome(rs.getString("Designaçao"));
                uc.setResponsavel("usernameRegente");
                
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
                Professor prof; 
                while(rs.next()){
                    prof = new Professor();
                    prof.setNome(rs.getString("Username"));
                    prof.setNome(rs.getString("Nome"));
                    prof.setMail(rs.getString("Mail"));
                    prof.setPassword(rs.getString("Password"));
                    prof.setRegente(rs.getBoolean("Regente"));
                    profs.add(prof);
                }
                uc.setDocentes(profs);
                
                ucs.put(uc.getCodigo(), uc);
            }
            
            p.setListaUcs((HashMap<String, UCPerfil>) ucs);        
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
    public Perfil put(String key, Perfil value) {
        Perfil p;
        
        if(this.containsKey(key))
            p = this.get(key);
        
        else p = value;
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Perfil WHERE idPerfil = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();
            
            ps = con.prepareStatement("INSERT INTO Perfil (idPerfil) VALUES (?)");
            ps.setString(1, key);
            ps.executeUpdate();
            
            
            Map<String, UCPerfil> listaUcs = value.getListaUcs();
            if(listaUcs!=null){
                for(UCPerfil uc: listaUcs.values()){
                    ps = con.prepareStatement("INSERT INTO UC (codigoUC, Designaçao, usernameRegente) VALUES (?,?,?)");
                    ps.setString(1, uc.getCodigo());
                    ps.setString(2, uc.getNome());
                    ps.setString(3, uc.getResponsavel());
                    ps.executeUpdate();
            
                    ps = con.prepareStatement("INSERT INTO UCPerfil (codigoUCPerfil, dia Semana, idPerfil) VALUES (?,?,?)");
                    ps.setString(1, uc.getCodigo());
                    ps.setString(2, uc.getDiaS());
                    ps.setString(3, key);
                    ps.executeUpdate();

                    HashSet<Turno> turnos = uc.getTurnos();
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

                    HashSet<Professor> professores = uc.getEquipaDocente();
                    if(professores!=null){
                        for(Professor prof: professores){
                            ps = con.prepareStatement("INSERT INTO Professor (Username, Nome, Email, Password, Regente) VALUES (?,?,?,?,?)");
                            ps.setString(1, prof.getUsername());
                            ps.setString(2, prof.getNome());
                            ps.setString(3, prof.getMail());
                            ps.setString(4, prof.getPass());
                            ps.setBoolean(5, prof.getRegente());
                            ps.executeUpdate();
                        }
                    }
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
    public Perfil remove(Object key) {
        Perfil p = this.get((String) key);
        
        try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Perfil WHERE idPerfil = ?");
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
    public void putAll(Map<? extends String, ? extends Perfil> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
       try{
            con = Connect.connect();
            PreparedStatement ps = con.prepareStatement("DELETE * FROM Perfil");
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
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Perfil");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                set.add(rs.getString("idPerfil"));
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
    public Collection<Perfil> values() {
       Set<Perfil> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        keys.forEach((key) -> {
            set.add(this.get(key));
        });
        return set;
    }

    @Override
    public Set<Entry<String, Perfil>> entrySet() {
       Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String, Perfil> map = new HashMap<>();
        keys.forEach((key) -> {
            map.put(key,this.get(key));
        });
        return map.entrySet();
    }
    

}
