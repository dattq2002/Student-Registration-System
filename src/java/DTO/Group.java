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
public class Group {    
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
    private String SubjectCode;
    private String CourseName;
    private int CourseCode;
    private int MemberID;
    private int TopicID;
    private String TopicName;

    public Group(String GroupName, int SubjectID, String SubjectCode, String CourseName, int CourseCode, int GroupID) {
        this.GroupName = GroupName;
        this.SubjectID = SubjectID;
        this.SubjectCode = SubjectCode;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
        this.GroupID = GroupID;
    }
    
    public Group(String StudentName, String GroupName, String isLeader, String StudentCode, int StudentID, int MemberID) {
        this.StudentName = StudentName;
        this.GroupName = GroupName;
        this.isLeader = isLeader;
        this.StudentCode = StudentCode;
        this.StudentID = StudentID;
        this.MemberID = MemberID;
    }    

    public Group(int GroupID, String StudentName, String GroupName, String isLeader, String StudentCode, int StudentID, int SubjectID, String SubjectCode, String CourseName, int CourseCode) {
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

    
    
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int GroupID) {
        this.GroupID = GroupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String StudentCode) {
        this.StudentCode = StudentCode;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
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

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int TopicID) {
        this.TopicID = TopicID;
    }

    public String getTopicName() {
        return TopicName;
    }

    public void setTopicName(String TopicName) {
        this.TopicName = TopicName;
    }



    
    
}
