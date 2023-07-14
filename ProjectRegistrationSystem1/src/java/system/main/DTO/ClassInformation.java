/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.main.DTO;

/**
 *
 * @author Nam An
 */
public class ClassInformation extends Class{
    //props
    private int ID;
    private String SubjectCode;
    private int SubjectID;
    private String lecName;
    private boolean Status;
    private int lecID;
    //contructor

    public ClassInformation(int ID, String SubjectCode, int SubjectID, String lecName, boolean Status, int lecID, int CourseID, String CourseName, int CourseCode, int SemesterID, String StartDate, String EndDate) {
        super(CourseID, CourseName, CourseCode, SemesterID, StartDate, EndDate);
        this.ID = ID;
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
        this.lecName = lecName;
        this.Status = Status;
        this.lecID = lecID;
    }
    
    
}
