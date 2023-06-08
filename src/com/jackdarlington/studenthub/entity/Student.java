/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 03/05/2023
 */

public class Student extends AbstractUser {
    
    private final Integer studentID;
    
    public boolean isEnrolled;
    public Course course;
    public int points;
    public HashMap<Paper, Grade> papers;
    
    public Integer getStudentID() {
        return studentID;
    }
    
    public Student(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.studentID = generateStudentID();
        this.isEnrolled = false;
    }
    
    public Student(String firstName, String lastName, String password, String courseCode, int points) {
        super(firstName, lastName, password);
        this.studentID = generateStudentID();
        this.points = points;
        this.course = Model.courses.get(courseCode);
        this.isEnrolled = false;
    }
    
    public Student(String studentID, String studentName, String studentEmail, String firstName, String lastName, String password, Map<String, String> data) {
        super(String.valueOf(AbstractUser.userIDCounter++), studentName, studentEmail, firstName, lastName, password, data);
        this.studentID = Integer.valueOf(studentID);
        this.isEnrolled = false;
    }

    private Integer generateStudentID() {
        Random rand = new Random(this.getUserID());
        return rand.nextInt(89000000) + 10000000;
    }
    
    public static void readStudentsFromDatabase() throws SQLException {
        Statement sqlReadStudents = Model.conn.createStatement();
        ResultSet student = sqlReadStudents.executeQuery("SELECT * FROM STUDENTS");
        while (student.next()) {
            Map<String, String> data = new LinkedHashMap<>();
            data.put("Personal Email", student.getString("PERSONALEMAIL"));
            data.put("Phone Number", student.getString("PHONENO"));
            data.put("Street Address 1", student.getString("STREETADDRESS1"));
            data.put("Street Address 2", student.getString("STREETADDRESS2"));
            data.put("Suburb", student.getString("SUBURB"));
            data.put("City", student.getString("CITY"));
            data.put("Post Code", student.getString("POSTCODE"));
            
            Model.users.add(
                    new Student(
                            student.getString("STUDENTID"),
                            student.getString("STUDENTNAME"),
                            student.getString("STUDENTEMAIL"),
                            student.getString("FIRSTNAME"), 
                            student.getString("LASTNAME"), 
                            student.getString("PASSWORD"),
                            data
                    )
            );
            System.out.println("[DATABASE] Student record " + student.getString("STUDENTNAME") + " added from database!");
        }
    }
    
    @Override
    protected String generateUserName() {
        Random rand = new Random(this.getUserID());
        String name = "";
        for (int i = 0; i < 3; i++) {
            name += (char)(rand.nextInt(25) + 97);
        }
        for (int i = 0; i < 4; i++) {
            name += Integer.toString(rand.nextInt(10));
        }
        return name;
    }

    @Override
    public String getUserType() {
        return "Student";
    }
    
}
