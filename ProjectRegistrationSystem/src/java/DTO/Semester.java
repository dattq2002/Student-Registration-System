package DTO;

public class Semester {
    //props
    private int ID;
    private int year;
    private String Name;
    private String Status;
    private String StartDate;
    private String EndDate;
    //constructor

    public Semester(int ID, int year, String Name, String Status, String StartDate, String EndDate) {
        this.ID = ID;
        this.year = year;
        this.Name = Name;
        this.Status = Status;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }
    
    //getter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }
    
}
