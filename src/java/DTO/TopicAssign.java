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
public class TopicAssign extends Topic{
    private int topicAssignID;
    private String subjectCode;
    private int subjectID;
    private String startDate;
    private String modifyDate;
    private String semester;
    private String status;

    public TopicAssign(int subjectID, String status, int topicID, String topicCode, String topicName, String shortDescription, String fullDescription, String lecturerName, int lecturerID) {
        super(topicID, topicCode, topicName, shortDescription, fullDescription, lecturerName, lecturerID);
        this.subjectID = subjectID;
        this.status = status;
    }

    public int getTopicAssignID() {
        return topicAssignID;
    }

    public void setTopicAssignID(int topicAssignID) {
        this.topicAssignID = topicAssignID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
