package DTO;

public class Group {
    //props
    protected String StudentName;
    protected int GroupID;
    protected String GroupName;
    protected String StartDate;
    protected String Major;
    protected String isLeader;
    protected String StudentCode;
    protected int StudentID;
    protected int CourseID;
    protected int SubjectID;
    protected String SubjectCode;
    protected String CourseName;
    protected int CourseCode;
    protected int MemberID;
    protected int TopicID;
    protected String TopicName;
    //constructor

    public Group(String StudentName, int GroupID, String GroupName, 
            String StartDate, String Major, String isLeader, String StudentCode, 
            int StudentID, int CourseID, int SubjectID, String subCode) {
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
        this.SubjectCode = subCode;
    }
    
    public Group(String StudentName, int GroupID, String GroupName, 
            String StartDate, String Major, String isLeader, String StudentCode, 
            int StudentID, int CourseID, int SubjectID, String subCode, 
            int TopicID, String topicName) {
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
        this.SubjectCode = subCode;
        this.TopicID = TopicID;
        this.TopicName = topicName;
    }

    public Group(String GroupName, int CourseID, int TopicID) {
        this.GroupName = GroupName;
        this.CourseID = CourseID;
        this.TopicID = TopicID;
    }
    //--------------------------------------------------------------------------
    //constructor Lecture
    public Group(String GroupName, int SubjectID, String SubjectCode, 
            String CourseName, int CourseCode, int GroupID) {
        this.GroupName = GroupName;
        this.SubjectID = SubjectID;
        this.SubjectCode = SubjectCode;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
        this.GroupID = GroupID;
    }
    
    public Group(String StudentName, String GroupName, String isLeader, 
            String StudentCode, int StudentID, int MemberID) {
        this.StudentName = StudentName;
        this.GroupName = GroupName;
        this.isLeader = isLeader;
        this.StudentCode = StudentCode;
        this.StudentID = StudentID;
        this.MemberID = MemberID;
    }    

    public Group(int GroupID, String StudentName, String GroupName, 
            String isLeader, String StudentCode, int StudentID, int SubjectID, 
            String SubjectCode, String CourseName, int CourseCode) {
        this.GroupID = GroupID;
        this.StudentName = StudentName;
        this.GroupName = GroupName;
        this.isLeader = isLeader;
        this.StudentCode = StudentCode;
        this.StudentID = StudentID;
        this.SubjectID = SubjectID;
        this.SubjectCode = SubjectCode;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
    }

    public Group(int TopicID, String TopicName) {
        this.TopicID = TopicID;
        this.TopicName = TopicName;
    }

    public Group(int TopicID) {
        this.TopicID = TopicID;
    }
    
    
    //--------------------------------------------------------------------------
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

    public String getSubjectName() {
        return SubjectCode;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public int getTopicID() {
        return TopicID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(int CourseCode) {
        this.CourseCode = CourseCode;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int MemberID) {
        this.MemberID = MemberID;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String TopicName) {
        this.TopicName = TopicName;
    }
    
}
