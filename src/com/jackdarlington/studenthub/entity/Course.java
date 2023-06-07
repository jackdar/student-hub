/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 17/05/2023
 */

public class Course {

    String courseCode;
    String courseName;
    
    public ArrayList<Paper> includedPapers;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public Course() {
        this("", "");
    }
    
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.includedPapers = initialisePapersInCourse();
    }
    
    // Initialise Courses using data from Java DB
    public static HashMap<String, Course> initialiseCourses() {
        HashMap<String, Course> courses = new HashMap<>();
        try {
            Statement st = Model.conn.createStatement();

            String selectCourses = "SELECT COURSECODE, COURSENAME FROM COURSES";
            ResultSet rs = st.executeQuery(selectCourses);

            while (rs.next()) {
                String courseCode = rs.getString("COURSECODE");
                String courseName = rs.getString("COURSENAME");
                courses.put(courseCode, new Course(courseCode, courseName));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while initialising Courses. See stack trace for more info.");
            //e.printStackTrace();
        }
        return courses;
    }
    
    // Initialise Papers In Courses
    // NOTE: PAPERS MUST BE INITIALISED BEFORE COURSES IN MODEL
    private ArrayList<Paper> initialisePapersInCourse() {
        ArrayList<Paper> papersInCourse = new ArrayList<>();
        try {
            Statement st = Model.conn.createStatement();
            String selectPapersInCourses = "SELECT COURSECODE, PAPERCODE FROM PAPERSINCOURSES WHERE COURSECODE = ?";
            PreparedStatement preSt = Model.conn.prepareStatement(selectPapersInCourses);
            preSt.setString(1, this.courseCode.toUpperCase());
            ResultSet rs = preSt.executeQuery();
            
            while (rs.next()) {
                String paperCode = rs.getString("PAPERCODE");
                papersInCourse.add(Model.papers.get(paperCode));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while initialising Papers in Course. See stack trace for more info.");
            //e.printStackTrace();
        }
        return papersInCourse;
    }
    
}
