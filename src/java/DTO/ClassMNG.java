/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nam An
 */
public class ClassMNG {

    protected int CourseID;
    protected String CourseName;
    protected int CourseCode;
    protected int SemesterID;
    protected String StartDate;
    protected String EndDate;

    public ClassMNG() {
    }

    
    
    public ClassMNG(int CourseCode, String CourseName, int CourseID) {
        this.CourseCode = CourseCode;
        this.CourseName = CourseName;
        this.CourseID = CourseID;
    }
   
    
    public ClassMNG(int CourseID, String CourseName, int CourseCode, int SemesterID, String StartDate, String EndDate) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
        this.SemesterID = SemesterID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public int getCourseID() {
        return CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public int getSemesterID() {
        return SemesterID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    

}
