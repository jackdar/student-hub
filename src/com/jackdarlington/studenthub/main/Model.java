/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jackdarlington.studenthub.main;

import com.jackdarlington.studenthub.utils.ConfigParser;
import com.jackdarlington.studenthub.utils.ConfigSection;
import com.jackdarlington.studenthub.entity.AbstractUser;
import com.jackdarlington.studenthub.entity.Course;
import com.jackdarlington.studenthub.entity.Paper;
import com.jackdarlington.studenthub.entity.StaffMember;
import com.jackdarlington.studenthub.entity.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author jack
 */
public class Model {
    
    public static String dbName;
    public static String url;
    public static String port;
    public static String dbUserName;
    public static String dbPassword;
    public static boolean isDBEmbedded;
    
    public static Connection conn;
    
    public static ConfigParser config;
    public static ConfigSection dbInfo;
    
    public static ArrayList<AbstractUser> users;
    public static HashMap<String, Paper> papers;
    public static HashMap<String, Course> courses;
    
    public static AbstractUser loggedInUser;
    
    public Model() {
        config = new ConfigParser();
        Controller.updateModelInfo();
        try {
            establishConnection();
            papers = Paper.initialisePapers();
            courses = Course.initialiseCourses();
            Controller.loadUsers();
        } catch (SQLException ex) {
            System.err.println("SQL Not Connected!");
        }
    }
    
    public static String getPasswordCovered() {
        String out = "";
        if (dbPassword != null) {
            for (int i = 0; i < dbPassword.length(); i++) {
                out += "*";
            }
        }
        return out;
    }
    
    public static void establishConnection() throws SQLException {
        String db;
        if (config.getSection("databaseConnection").get("embedded").equals("false")) {
            db = "//" + url + ":" + port + "/" + dbName;
        } else {
            db = dbName;
        }
        Model.conn = DriverManager.getConnection(("jdbc:derby:" + db + "; create=true"), dbUserName, dbPassword);
    }
    
}
