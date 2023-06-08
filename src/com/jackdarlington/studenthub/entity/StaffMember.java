/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 19/05/2023
 */

public class StaffMember extends AbstractUser {

    public StaffMember(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }
    
    public StaffMember(String userID, String userName, String userEmail, String firstName, String lastName, String password, Map<String, String> data) {
        super(userID, userName, userEmail, firstName, lastName, password, data);
    }
    
    public static void readStaffMemebersFromDatabase() throws SQLException {
        Statement sqlReadStaff = Model.conn.createStatement();
        ResultSet staff = sqlReadStaff.executeQuery("SELECT * FROM STAFFMEMBERS");
        while (staff.next()) {
            Map<String, String> data = new LinkedHashMap<>();
            data.put("Personal Email", staff.getString("PERSONALEMAIL"));
            data.put("Phone Number", staff.getString("PHONENO"));
            data.put("Street Address 1", staff.getString("STREETADDRESS1"));
            data.put("Street Address 2", staff.getString("STREETADDRESS2"));
            data.put("Suburb", staff.getString("SUBURB"));
            data.put("City", staff.getString("CITY"));
            data.put("Post Code", staff.getString("POSTCODE"));
            
            Model.users.add(new StaffMember(
                            staff.getString("USERID"),
                            staff.getString("USERNAME"),
                            staff.getString("USEREMAIL"),
                            staff.getString("FIRSTNAME"), 
                            staff.getString("LASTNAME"), 
                            staff.getString("PASSWORD"),
                            data
                    )
            );
            
            System.out.println("[DATABASE] Staff Member record " + staff.getString("USERNAME") + " added from database!");
        }
    }
    
    @Override
    protected String generateUserName() {
        return this.firstName.substring(0, 1).toLowerCase() + this.lastName.toLowerCase();
    }

    @Override
    public String getUserType() {
        return "Staff Member";
    }
    
}