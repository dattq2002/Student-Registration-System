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
public class Topic {
    private int topicID;
    private String topicCode;
    private String topicName;
    private int lecturerID;
    private String shortDescription;
    private String fullDescription;
    private int semesterID;
    private String lecturerName;

    
    public Topic() {
    }

    public Topic(int topicID, String topicCode, String topicName, String shortDescription, String fullDescription) {
        this.topicID = topicID;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    public Topic(int topicID, String topicCode, String topicName, String shortDescription, String fullDescription, String lecturerName, int lecturerID) {
        this.topicID = topicID;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.lecturerName = lecturerName;
        this.lecturerID = lecturerID;
    }
    
    
    
    public Topic(int topicID, String topicCode, String topicName, int lecturerID, String shortDescription, String fullDescription) {
        this.topicID = topicID;
        this.topicCode = topicCode;
        this.topicName = topicName;
        this.lecturerID = lecturerID;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }
    
    
}
