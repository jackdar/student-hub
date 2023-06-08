/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jackdarlington.studenthub.main;

import com.jackdarlington.studenthub.entity.AbstractUser;
import com.jackdarlington.studenthub.entity.StaffMember;
import com.jackdarlington.studenthub.entity.Student;
import com.jackdarlington.studenthub.utils.ConfigSection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class Controller {
    
    public static void updateModelInfo() {
        ConfigSection dbInfo = Model.config.getSection("databaseConnection");
        
        Model.isDBEmbedded = dbInfo.get("embedded").equalsIgnoreCase("true");
        Model.dbName = dbInfo.get("dbName");
        Model.url = dbInfo.get("host");
        Model.port = dbInfo.get("port");
        Model.dbUserName = dbInfo.get("userName");
        Model.dbPassword = dbInfo.get("password");
        
        System.out.println("[Database Info Updated] embedded: " + Model.isDBEmbedded + ", dbName: " + 
            Model.dbName + ", url: " + Model.url + ":" + Model.port + ", userName: " + 
            Model.dbUserName + ", password: " + Model.dbPassword);
    }
    
    public static void updateDBInfo(String embedded, String dbName, String url, String port, String userName, String password) {
        ConfigSection newDBInfo = Model.config.getSection("databaseConnection");
        
        newDBInfo.set("embedded", embedded.equalsIgnoreCase("true") ? "true" : 
            embedded.equalsIgnoreCase("false") ? "false" : 
            Model.config.get("databaseConnection", "embedded"));
        
        newDBInfo.set("dbName", dbName);
        newDBInfo.set("host", url);
        newDBInfo.set("port", port);
        newDBInfo.set("userName", userName);
        newDBInfo.set("password", password.charAt(0) == '*' ? "" : password);
        
        Model.config.replaceSection("databaseConnection", newDBInfo);
        
        Controller.updateModelInfo();
        
        Controller.updateConfig();
    }
    
    public static void updateConfig() {
        Model.config.writeConfig();
    }
    
    public static boolean attemptLogIn(String u, String p) {
        for (AbstractUser user : Model.users) {
            if (AbstractUser.authUser(user, u, p)) {
                Model.loggedInUser = user;
                return true;
            }
        }
        return false;
    }
    
    public static void loadUsers() {
        AbstractUser.userIDCounter = 1;
        Model.users = new ArrayList<>();
        try {
            Student.readStudentsFromDatabase();
            StaffMember.readStaffMemebersFromDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateUserData(String[] data) {
        Model.loggedInUser.setFirstName(data[0]);
        Model.loggedInUser.setLastName(data[1]);
        
        int index = 2;
        for (Map.Entry<String, String> e : Model.loggedInUser.userData.entrySet()) {
            Model.loggedInUser.userData.replace(e.getKey(), data[index]);
            index++;
        }
        System.out.println("[USER INFO] User " + Model.loggedInUser.getUserName() + " details updated!");
    }
    
}
