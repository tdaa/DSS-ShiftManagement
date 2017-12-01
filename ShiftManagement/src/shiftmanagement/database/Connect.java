/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Tiago
 */
public class Connect {
    private static final String URL = "localhost";
    private static final String SCHEMA = "DSS";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "140697";
    
    public static Connection connect(){
           try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://"+URL+"/"+SCHEMA+"?user="+USERNAME+"&password="+PASSWORD);
            return cn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void close(Connection connection){
        try{
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
}
