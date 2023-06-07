/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 19/05/2023
 */

public class StaffMember extends AbstractUser {

    public StaffMember(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }
    
    @Override
    protected String generateUserName() {
        return this.firstName.substring(0, 1).toLowerCase() + this.lastName.toLowerCase();
    }

    @Override
    public String getUserType() {
        return "Staff Member";
    }

    @Override
    protected PreparedStatement userTypeWriteSpecificData(PreparedStatement preSt, int index) throws SQLException {
        return preSt;
    }
    
}