/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import com.jackdarlington.studenthub.utils.PasswordEncryption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 17/05/2023
 */

abstract public class AbstractUser implements Comparable<AbstractUser> {

    private final Integer userID;
    private final String userName;
    private final String userPassword;
    private final Integer userPasswordLen;
    private final String userEmail;

    protected String firstName;
    protected String lastName;

    public Map<String, String> userData;
    
    protected boolean isRecievingEmail;
    protected boolean isAttending;
    
    public static Integer userIDCounter = 1;

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordCovered() {
        String coveredPassword = "";
        for (int i = 0; i < this.userPasswordLen; i++) {
            coveredPassword += "*";
        }
        return coveredPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    abstract public String getUserType();

    public AbstractUser(String firstName, String lastName, String password) {
        this.userID = AbstractUser.userIDCounter++;
        
        this.firstName = firstName;
        this.lastName = lastName;
        
        this.userName = generateUserName();
        this.userEmail = this.userName + "@aouniversity.ac.nz";
        
        this.userPassword = PasswordEncryption.encryptPassword(password);
        this.userPasswordLen = password.length();
        
        this.userData = new LinkedHashMap<>();
        this.initialiseUserDataMap();
    }
    
    public AbstractUser(String userID, String userName, String userEmail, String firstName, String lastName, String password, Map<String, String> data) {
        if (Objects.equals(Integer.valueOf(userID), userIDCounter)) {
            userIDCounter++;
        } else if (Integer.valueOf(userID) > userIDCounter) {
            userIDCounter = Integer.parseInt(userID) + 1;
        }
        this.userID = Integer.valueOf(userID);
        this.userName = userName;
        this.userEmail = userEmail;
        
        this.firstName = firstName;
        this.lastName = lastName;
        
        this.userPassword = PasswordEncryption.encryptPassword(password);
        this.userPasswordLen = password.length();
        
        this.userData = new LinkedHashMap<>();
        this.initialiseUserDataMap();
        
        for (Map.Entry<String, String> e : data.entrySet()) {
            this.userData.put(e.getKey(), e.getValue());
        }
    }

    abstract protected String generateUserName();
    
    private void initialiseUserDataMap() {
        this.userData.put("Personal Email", "");
        this.userData.put("Phone Number", "");
        this.userData.put("Street Address 1", "");
        this.userData.put("Street Address 2", "");
        this.userData.put("Suburb", "");
        this.userData.put("City", "");
        this.userData.put("Post Code", "");
    }
    
    public static void writeCurrentUserToDatabase() throws SQLException {
        PreparedStatement preSt;
        
        String table, idType, id;
        if (Model.loggedInUser instanceof Student) {
            table = "STUDENTS";
            idType = "STUDENTID"; 
            id = ((Student) Model.loggedInUser).getStudentID().toString();
        } else {
            table = "STAFFMEMBERS";
            idType = "USERID";
            id = ((StaffMember) Model.loggedInUser).getUserID().toString();
        }
        
        String checkRecord = "SELECT COUNT(*) FROM " + table + " WHERE " + idType + " = ?";
        
        preSt = Model.conn.prepareCall(checkRecord);
        preSt.setString(1, id);
        
        ResultSet rsCheckRecord = preSt.executeQuery();
        if (rsCheckRecord.next()) {
            int count = rsCheckRecord.getInt(1);
            if (count > 0) {
                System.out.println("[DATABASE] Record " + Model.loggedInUser.getUserName() + " exists!");
                String sqlUpdate = "UPDATE " + table + 
                        " SET PERSONALEMAIL = ?, PHONENO = ?, STREETADDRESS1 = ?,"
                        + " STREETADDRESS2 = ?, SUBURB = ?, CITY = ?, POSTCODE = ?"
                        + " WHERE " + idType + " = ?";
                
                PreparedStatement updatePreSt = Model.conn.prepareStatement(sqlUpdate);
                
                int index = 1;
                for (Map.Entry<String, String> e : Model.loggedInUser.userData.entrySet()) {
                    updatePreSt.setString(index++, e.getValue());
                }
                updatePreSt.setString(index++, id);
                
                updatePreSt.executeUpdate();
                
                System.out.println("[DATABASE] Record: " + Model.loggedInUser.getUserName() + " updated!");
            } else {
                System.out.println("[DATABASE] Record " + Model.loggedInUser.getUserName() + " not found! Adding record...");
                String sqlInsert = "INSERT INTO " + table + " VALUES " + 
                        (Model.loggedInUser instanceof Student ? "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" : 
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                PreparedStatement insertPreSt = Model.conn.prepareStatement(sqlInsert);
                
                insertPreSt.setString(1, id);
                insertPreSt.setString(2, Model.loggedInUser.getUserName());
                insertPreSt.setString(3, Model.loggedInUser.getUserEmail());
                insertPreSt.setString(4, Model.loggedInUser.getFirstName());
                insertPreSt.setString(5, Model.loggedInUser.getLastName());
                
                int index = 6;
                for (Map.Entry<String, String> e : Model.loggedInUser.userData.entrySet()) {
                    insertPreSt.setString(index++, e.getValue());
                }
                
                if (Model.loggedInUser instanceof Student) {
                    insertPreSt.setBoolean(index++, Model.loggedInUser.isAttending);
                }
                
                insertPreSt.executeUpdate();
                
                System.out.println("[DATABASE] Record " + Model.loggedInUser.getUserName() + " inserted!");
            }
        }
    }

    public static boolean authUser(AbstractUser user, String name, String password) {
        return user.userName.equals(name) && user.userPassword.equals(PasswordEncryption.encryptPassword(password));
    }

    @Override
    public int compareTo(AbstractUser o) {
        if (o.userID.equals(this.userID) && o.userName.equals(this.userName) && o.userPassword.equals(this.userPassword)) {
            return 0;
        } else if (o.userID > this.userID) {
            return 1;
        } else if (o.userID < this.userID) {
            return -1;
        }
        return 0;
    }

}
