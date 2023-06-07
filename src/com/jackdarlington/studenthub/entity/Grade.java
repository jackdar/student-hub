/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.entity;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 21/05/2023
 */

public enum Grade {

    NOT_GRADED  ("Not Graded",  0.00,   0.00),
    FAIL        ("Fail",        0.00,   49.49),
    C_MINUS     ("C-",          49.50,  54.49),
    C           ("C",           54.50,  59.49),
    C_PLUS      ("C+",          59.50,  64.49),
    B_MINUS     ("B-",          64.50,  69.49),
    B           ("B",           69.50,  74.49),
    B_PLUS      ("B+",          74.50,  79.49),
    A_MINUS     ("A-",          79.50,  84.49),
    A           ("A",           84.50,  89.49),
    A_PLUS      ("A+",          89.50,  100.00);
    
    public final String label;
    public final double upperBound;
    public final double lowerBound;
    
    private Grade(String label, double lowerBound, double upperBound) {
        this.label = label;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    public Grade checkGrade(double score) {
        if (score == 0.0) {
            return Grade.NOT_GRADED;
        }
        for (Grade g : Grade.values()) {
            if (score >= g.lowerBound && score <= g.upperBound) {
                return g;
            }
        }
        return null;
    }
    
    public int compareGrade(Grade grade) {
        if (grade.ordinal() > this.ordinal()) {
            return 1;
        } else if (grade.ordinal() < this.ordinal()) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
