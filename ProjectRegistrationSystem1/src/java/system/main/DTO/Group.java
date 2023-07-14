package system.main.DTO;

public class Group {

    //props
    protected int GroupID;
    protected String GroupName;
    protected int CourseID;
    protected String CourseName;
    protected String GroupCode;
    protected String Status;
    protected int StudentID;
    protected String StudentCode;
    protected String StudentName;
    protected int MemberID;
    protected String StartDate;
    protected String isLeader;
    //constructor 

    public Group(int GroupID, String GroupName, int CourseID, String CourseName,
            String GroupCode, String Status) {
        this.GroupID = GroupID;
        this.GroupName = GroupName;
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.GroupCode = GroupCode;
        this.Status = Status;
    }

    public Group(int GroupID, String GroupName, int CourseID,
            String GroupCode) {
        this.GroupID = GroupID;
        this.GroupName = GroupName;
        this.CourseID = CourseID;
        this.GroupCode = GroupCode;
    }

    public Group(int StudentID, String StudentCode, String StudentName, 
            int MemberID, String StartDate, String isLeader) {
        this.StudentID = StudentID;
        this.StudentCode = StudentCode;
        this.StudentName = StudentName;
        this.MemberID = MemberID;
        this.StartDate = StartDate;
        this.isLeader = isLeader;
    }

    public Group(int GroupID) {
        this.GroupID = GroupID;
    }

    //--------------------------------------------------------------------------
    //getter
    public int getGroupID() {
        return GroupID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getGroupCode() {
        return GroupCode;
    }

    public String getStatus() {
        return Status;
    }

    public int getStudentID() {
        return StudentID;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public String getStudentName() {
        return StudentName;
    }

    public int getMemberID() {
        return MemberID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getIsLeader() {
        return isLeader;
    }

}
