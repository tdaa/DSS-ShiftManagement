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
import shiftmanagement.Business.UC.UCPerfil;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.UC.UCComplementar;
import shiftmanagement.database.UcPerfilDAO;

/**
 * 
 * @author franciscolira
 */
public class Parser {
        
    public void parsePerfis(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Perfis.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray perfis = rootObject.getAsJsonArray("perfil");
        for(int i=0; i<perfis.size(); i++){
            JsonObject item = perfis.get(i).getAsJsonObject();
            String code = item.get("cod").getAsString();
            String name = item.get("nome").getAsString();
            String diasem = item.get("diasem").getAsString();
            UCPerfil ucperfil = new UCPerfil(name,code,diasem);
            //UcPerfilDAO.put(code,ucperfil);
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
            //UcLicenciaturaDAO.put(code,uclic);
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
    
    /*
    public void parseShifts(){
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
                for(int k=0; k<ite.size(); k++){
                JsonObject ite = shift.get(j).get(k).getAsJsonObject();
                String ucnome = ite.get("UC").getAsString();
                String uccod = ite.get("cod").getAsString();
                System.out.println(uccod);
                }
                
            }
            System.out.println(ano);
            //UcLicenciaturaDAO.put(code,uclic);
        }
    }*/
}
