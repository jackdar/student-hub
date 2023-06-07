/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

import com.jackdarlington.studenthub.main.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 17/05/2023
 */

public class Paper {

    String paperCode;
    String paperName;

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
    
    public Paper() {
        this("", "");
    }
    
    public Paper(String paperCode, String paperName) {
        this.paperCode = paperCode;
        this.paperName = paperName;
    }
    
    // Initialise Papers using data from Java DB
    public static HashMap<String, Paper> initialisePapers() {
        HashMap<String, Paper> papers = new HashMap<>();
        try {
            Statement st = Model.conn.createStatement();

            String selectPapers = "SELECT PAPERCODE, PAPERNAME FROM PAPERS";
            ResultSet rs = st.executeQuery(selectPapers);

            while (rs.next()) {
                String paperCode = rs.getString("PAPERCODE");
                String paperName = rs.getString("PAPERNAME");
                papers.put(paperCode, new Paper(paperCode, paperName));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while initialising Papers. See stack trace for more info.");
            //e.printStackTrace();
        }
        return papers;
    }
    
    @Override
    public String toString() {
        return this.paperCode;
    }

    @Override
    public boolean equals(Object o) {
        return this.paperCode.equals(((Paper) o).paperCode);
    }
    
}
