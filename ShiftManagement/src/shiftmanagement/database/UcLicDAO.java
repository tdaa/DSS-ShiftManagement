/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import shiftmanagement.Business.Turno.Turno;
import shiftmanagement.Business.UC.UCLicenciatura;
import shiftmanagement.Business.Utilizador.Professor;

/**
 *
 * @author Tiago
 */
public class UcLicDAO implements Map<String, UCLicenciatura>{
    
    private Connection con;
   
    
    public ArrayList<String> getTurnos(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        for(UCLicenciatura uc: this.values()){
            if(uc.getCodigo().equals(codigoUC)){
                for(Turno t: uc.getTurnos()){
                    res.add(t.getId());
                }
            }
        }
        return res;
    }
    
    public ArrayList<String> getProfs(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        for(UCLicenciatura uc: this.values()){
            if(uc.getCodigo().equals(codigoUC)){
                for(Professor p: uc.getEquipaDocente()){
                    res.add(p.getUsername() + " - " + p.getNome());
                }
            }
        }
        return res;
    }
    
    //util para o dialogAddProfessor
     public ArrayList<String> getProfsPorNome(String codigoUC){
        ArrayList<String> res = new ArrayList<>();
        for(UCLicenciatura uc: this.values()){
            if(uc.getCodigo().equals(codigoUC)){
                for(Professor p: uc.getEquipaDocente()){
                    res.add(p.getNome());
                }
            }
        }
        return res;
    }
    
    public String getCodigo(String nome){
        for(UCLicenciatura uc: this.values()){
            if(uc.getNome().equals(nome)) return uc.getCodigo();
        }
        return null;
    }
    
    public ArrayList<String> getNomesUcs(){
        ArrayList<String> res = new ArrayList<>();
        for(UCLicenciatura uc: this.values()){
            res.add(uc.getCodigo() + " - " + uc.getNome());
        }
        return res;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura get(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura put(Integer key, UCLicenciatura value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends UCLicenciatura> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<UCLicenciatura> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<Integer, UCLicenciatura>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura put(String key, UCLicenciatura value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura getOrDefault(Object key, UCLicenciatura defaultValue) {
        return Map.super.getOrDefault(key, defaultValue); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super UCLicenciatura> action) {
        Map.super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super UCLicenciatura, ? extends UCLicenciatura> function) {
        Map.super.replaceAll(function); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura putIfAbsent(String key, UCLicenciatura value) {
        return Map.super.putIfAbsent(key, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean replace(String key, UCLicenciatura oldValue, UCLicenciatura newValue) {
        return Map.super.replace(key, oldValue, newValue); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura replace(String key, UCLicenciatura value) {
        return Map.super.replace(key, value); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura computeIfAbsent(String key, Function<? super String, ? extends UCLicenciatura> mappingFunction) {
        return Map.super.computeIfAbsent(key, mappingFunction); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura computeIfPresent(String key, BiFunction<? super String, ? super UCLicenciatura, ? extends UCLicenciatura> remappingFunction) {
        return Map.super.computeIfPresent(key, remappingFunction); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura compute(String key, BiFunction<? super String, ? super UCLicenciatura, ? extends UCLicenciatura> remappingFunction) {
        return Map.super.compute(key, remappingFunction); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UCLicenciatura merge(String key, UCLicenciatura value, BiFunction<? super UCLicenciatura, ? super UCLicenciatura, ? extends UCLicenciatura> remappingFunction) {
        return Map.super.merge(key, value, remappingFunction); //To change body of generated methods, choose Tools | Templates.
    }
    
}
