/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jackdarlington.studenthub.main;

import com.jackdarlington.studenthub.utils.ConfigParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class Model {
    
    
    /* Networked Configuration */ /*
    public static final String DB_NAME = "studentHubDB";
    public static final String URL = "localhost";
    public static final String PORT = "1527";
    public static final String DB = "//" + URL + ":" + PORT + "/" + DB_NAME;
    
    /* Embedded Configuration */ 
    public static String DB_NAME = "studentHubDB";
    public static String URL = "";
    public static String PORT = "";
    public static String DB =  DB_NAME;
    
    /**/
    
    public static String USER_NAME = "pdc";
    private static String PASSWORD = "pdc";
    
    public static Connection conn;
    
    public static ConfigParser config;
    
    public static String getPasswordCovered() {
        String out = "";
        for (int i = 0; i < Model.PASSWORD.length(); i++) {
            out += "*";
        }
        return out;
    }
    
    public Model() {
        config = new ConfigParser();
        establishConnection();
        createTable("STUDENTS","ID INT","FIRST_NAME VARCHAR(20)", "LAST_NAME VARCHAR(20)", "EMAIL_ADDRESS VARCHAR(40)", "PASSWORD VARCHAR(40)");
    }
    
    public static void updateDBInformation(String dbName, String url, String port, String userName, String password) {
        Model.DB_NAME = dbName;
        Model.URL = url;
        Model.PORT = port;
        Model.USER_NAME = userName;
        Model.PASSWORD = password.charAt(0) == '*' ? "" : password;
        System.out.println("[Database Info Updated] dbName: " + Model.DB_NAME + ", url: " + Model.URL + ":" + Model.PORT + ", userName: " + Model.USER_NAME + ", password: " + Model.getPasswordCovered());
    }
    
    public static void establishConnection() {
        try {
            Model.conn = DriverManager.getConnection(("jdbc:derby:" + DB + "; create=true"), USER_NAME, PASSWORD);
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createTable(String tableName, String... variables) {
        try {
            Statement st = conn.createStatement();
            String createTable = "CREATE TABLE " + tableName + " (";
            for (String s : variables) {
                createTable += s + ", ";
            }
            createTable = createTable.substring(0, createTable.length() - 2) + ")";
            st.executeUpdate(createTable);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean logIn(String email, String password) {
        
        return false;
    }
    
}
