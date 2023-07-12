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
public class GroupProject extends Group{
    private String Context;
    private String Actor;
    private String Function;
    private String Note;
    private int ProjectID;

    public GroupProject(String Context, String Actor, String Function, String Note, int ProjectID, int TopicID, String TopicName) {
        super(TopicID, TopicName);
        this.Context = Context;
        this.Actor = Actor;
        this.Function = Function;
        this.Note = Note;
        this.ProjectID = ProjectID;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }  

    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String Actor) {
        this.Actor = Actor;
    }

    public String getFunction() {
        return Function;
    }

    public void setFunction(String Function) {
        this.Function = Function;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
