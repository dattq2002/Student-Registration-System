package DTO;

public class Group {
    //props
    private String StudentName;
    private int GroupID;
    private String GroupName;
    private String StartDate;
    private String Major;
    private String isLeader;
    private String StudentCode;
    private int StudentID;
    private int CourseID;
    private int SubjectID;
    //constructor

    public Group(String StudentName, int GroupID, String GroupName, String StartDate, String Major, 
            String isLeader, String StudentCode, int StudentID, int CourseID, int SubjectID) {
        this.StudentName = StudentName;
        this.GroupID = GroupID;
        this.StartDate = StartDate;
        this.Major = Major;
        this.isLeader = isLeader;
        this.StudentCode = StudentCode;
        this.StudentID = StudentID;
        this.CourseID = CourseID;
        this.GroupName = GroupName;
        this.SubjectID = SubjectID;
    }
    //getter

    public String getStudentName() {
        return StudentName;
    }

    public int getGroupID() {
        return GroupID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getMajor() {
        return Major;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public int getStudentID() {
        return StudentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public int getSubjectID() {
        return SubjectID;
    }
    
}
