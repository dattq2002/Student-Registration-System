package system.main.DTO;

public class Semester {
    //props
    private int SemesterID;
    private String SemesterName;
    private int SemesterYear;
    private String Status;
    private String StartDate;
    private String EndDate;
    
    //constructor

    public Semester(int SemesterID, String SemesterName, int SemesterYear, 
            String Status, String StartDate, String EndDate) {
        this.SemesterID = SemesterID;
        this.SemesterName = SemesterName;
        this.SemesterYear = SemesterYear;
        this.Status = Status;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }
    //getter

    public int getSemesterID() {
        return SemesterID;
    }

    public String getSemesterName() {
        return SemesterName;
    }

    public int getSemesterYear() {
        return SemesterYear;
    }

    public String getStatus() {
        return Status;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }
    
}
