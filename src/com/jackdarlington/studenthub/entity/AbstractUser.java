/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import com.jackdarlington.studenthub.utils.PasswordEncryption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

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

    public LinkedHashMap<String, String> userData;
    
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

    public String getLastName() {
        return lastName;
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
    }

    abstract protected String generateUserName();
    
    public void writeUserToDatabase() throws SQLException {
        PreparedStatement preSt;
        if (this instanceof Student) {
            String insertUserQuery = "INSERT INTO STUDENTS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preSt = Model.conn.prepareCall(insertUserQuery);
            preSt.setString(1, ((Student) this).getStudentID().toString());
        } else {
            String insertUserQuery = "INSERT INTO STAFFMEMBER VALUES (?, ?, ?, ?)";
            preSt = Model.conn.prepareCall(insertUserQuery);
            preSt.setString(1, ((StaffMember) this).getUserID().toString());
        }
        
        preSt.setString(2, this.getUserName());
        preSt.setString(3, this.getFirstName());
        preSt.setString(4, this.getLastName());
        
        preSt = userTypeWriteSpecificData(preSt, 5);
        
        preSt.executeQuery();
    }
    
    abstract protected PreparedStatement userTypeWriteSpecificData(PreparedStatement preSt, int index) throws SQLException;

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
