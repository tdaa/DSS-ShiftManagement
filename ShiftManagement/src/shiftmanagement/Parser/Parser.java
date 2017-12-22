/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Parser;

import com.google.gson.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Time;
import java.util.ArrayList;
import shiftmanagement.Business.ShiftManagement;
import shiftmanagement.Business.Turno.PL;
import shiftmanagement.Business.Turno.Sala;
import shiftmanagement.Business.Turno.TP;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.UC.UCPerfil;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.UC.UCComplementar;
import shiftmanagement.Business.Utilizador.Aluno;
import shiftmanagement.Business.Utilizador.Professor;

/**
 * 
 * @author franciscolira
 */
public class Parser {
    
    private ShiftManagement system;
    
    public Parser(ShiftManagement s){
        this.system = s;
    }
    
    public Parser(){}
        
    public void parsePerfis(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Perfis.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray perfis = rootObject.getAsJsonArray("perfil");
        String aux = "";
        for(int i=0; i<perfis.size(); i++){
            JsonObject item = perfis.get(i).getAsJsonObject();
            String code = item.get("cod").getAsString();
            String name = item.get("nome").getAsString();
            String diasem = item.get("diasem").getAsString();
            UCPerfil ucperfil = new UCPerfil(name,code,diasem);
            String nomePerfil = code.substring(0, code.indexOf("-"));
            if(!aux.equals(nomePerfil)){
                aux = nomePerfil;
                this.system.inserePerfil(nomePerfil);
            }
            this.system.insereUCPerfil(ucperfil, nomePerfil);
        }
    }
    
    public void parseUcs(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Ucs.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray ucsLic = rootObject.getAsJsonArray("Ucs");
        for(int i=0; i<ucsLic.size(); i++){
            JsonObject item = ucsLic.get(i).getAsJsonObject();
            String code = item.get("code").getAsString();
            String name = item.get("name").getAsString();
            String abr = item.get("abr").getAsString();
            UCLicenciatura uclic = new UCLicenciatura(name,code,abr);
            this.system.insereUCLic(uclic);
        }
    }
    
    public void parseUcComp(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ucsPerfil.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray ucsComp = rootObject.getAsJsonArray("complementar");
        for(int i=0; i<ucsComp.size(); i++){
            JsonObject item = ucsComp.get(i).getAsJsonObject();
            String code = item.get("code").getAsString();
            String nome = item.get("nome").getAsString();
            String diasem = item.get("diasem").getAsString();
            String per = item.get("per").getAsString();
            System.out.println(code);
            UCComplementar uccomp = new UCComplementar(nome,code,per,diasem);
            //UcLicenciaturaDAO.put(code,uclic);
        }
    }
    
    public void parseAlunos(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Alunos.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray ucsComp = rootObject.getAsJsonArray("Alunos");
        for(int i=0; i<ucsComp.size(); i++){
            JsonObject item = ucsComp.get(i).getAsJsonObject();
            String name = item.get("nome").getAsString();
            String username = item.get("username").getAsString();
            String pass = item.get("password").getAsString();
            String mail = item.get("email").getAsString();
            boolean trabalhador = item.get("trabalhador").getAsBoolean();
            Aluno a = new Aluno(username, name, mail, pass, trabalhador);
            this.system.addNovoAluno(a);
        }
    }
    
    public void parseProfessores(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Professores.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray ucsComp = rootObject.getAsJsonArray("Professores");
        for(int i=0; i<ucsComp.size(); i++){
            JsonObject item = ucsComp.get(i).getAsJsonObject();
            String name = item.get("nome").getAsString();
            String username = item.get("username").getAsString();
            String pass = item.get("password").getAsString();
            String mail = item.get("email").getAsString();
            Professor p = new Professor(username, name, mail, pass);
            this.system.addNovoProfessor(p);
        }
    }
   
    public void parseShifts(){
        ArrayList<Turno> list;
        Turno t;
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("shifts.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray shifts = rootObject.getAsJsonArray("horario");
        for(int i=0; i<shifts.size(); i++){
            JsonObject item = shifts.get(i).getAsJsonObject();
            String ano = item.get("ano").getAsString();
            JsonArray shift = item.getAsJsonArray("turno");
            for(int j=0; j<shift.size(); j++){
                JsonArray h = shift.get(j).getAsJsonArray();
                list = new ArrayList<>();
                for(int k=0; k<h.size(); k++){
                    JsonObject ite = h.get(k).getAsJsonObject();
                    String uc = ite.get("UC").getAsString();
                    String idTurno = ite.get("cod").getAsString();
                    Time hora = Time.valueOf(ite.get("hora").getAsString());
                    String dia = ite.get("diaS").getAsString();
                    t = new Turno(uc, idTurno, hora, dia);
                    t.setDia(dia);
                    t.setId(idTurno);
                    t.setHora(hora);
                    t.setUc(uc);
                    if(idTurno.charAt(0) == 'P'){
                        PL pl = (PL) t;
                        pl.setMax(30);
                        list.add(pl);
                    }
                    else if(idTurno.charAt(0) == 'T'){
                        TP tp = (TP) t;
                        tp.setMax(40);
                        list.add(tp);
                    }                  
                }
                this.system.addHorario(list, ano);
            }
        }
    }
    
    public void parseTurnos(){
        Sala sala;
        Turno t;
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Turnos.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray shifts = rootObject.getAsJsonArray("turnosUCS");
        for(int i=0; i<shifts.size(); i++){
            JsonObject item = shifts.get(i).getAsJsonObject();
            String codigoUC = item.get("uc").getAsString();
            JsonArray shift = item.getAsJsonArray("turnos");
            for(int j=0; j<shift.size(); j++){
                JsonObject it = shift.get(j).getAsJsonObject();
                String id = it.get("cod").getAsString();
                Time h = Time.valueOf(it.get("hora").getAsString());
                String dia = it.get("diaS").getAsString();
                String ids = it.get("idSala").getAsString();
                int cap = it.get("cap").getAsInt();
                int aulas = it.get("aulas").getAsInt();
                sala = new Sala(cap, ids);
                t = new Turno(id, sala, null, h, codigoUC, aulas, dia);
                this.system.addTurno(t, codigoUC, 1, null);
            }
        }
    }
    

}
