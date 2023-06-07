/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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
    
    public Student(String firstName, String lastName, String password, String courseCode, int points) {
        super(firstName, lastName, password);
        this.studentID = generateStudentID();
        this.points = points;
        this.course = Model.courses.get(courseCode);
        for (int i = 0; i < userData.size(); i++) {
            userData.put("null", "null");
        }
    }

    private Integer generateStudentID() {
        Random rand = new Random(this.getUserID());
        return rand.nextInt(89000000) + 10000000;
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

    @Override
    protected PreparedStatement userTypeWriteSpecificData(PreparedStatement preSt, int index) throws SQLException {
        if (!this.userData.isEmpty()) {
            for (Map.Entry<String, String> e : this.userData.entrySet()) {
                preSt.setString(index++, e.getValue());
            }
        }
        
        if (course != null) {
            preSt.setString(index++, course.getCourseCode());
            
            String userPapers = "";
            String userGrades = "";
            for (Map.Entry<Paper, Grade> e : this.papers.entrySet()) {
                userPapers += e.getKey() + ",";
                userGrades += e.getValue() + ",";
            }

            preSt.setString(index++, userPapers.substring(0, userPapers.length() - 1));
            preSt.setString(index++, userGrades.substring(0, userGrades.length() - 1));
        }
        
        return preSt;
    }

    
    
}
