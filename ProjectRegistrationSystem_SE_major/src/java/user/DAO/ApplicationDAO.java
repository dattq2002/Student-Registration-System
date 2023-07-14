package user.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Application;

public class ApplicationDAO {

    //xử lý đơn
    public boolean processApplication(Application form) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO "
                        + "Form(LecID, Type, StudentID, Reason, CreateDate, "
                        + "Status,CourseID, subjectID, GroupID) "
                        + "VALUES (?,?,?,?,GETDATE(),?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, form.getLecID());
                stm.setString(2, form.getType());
                stm.setInt(3, form.getStuID());
                stm.setString(4, form.getReason());
                stm.setString(5, "Processing");
                stm.setInt(6, form.getCourseID());
                stm.setInt(7, form.getSubID());
                stm.setInt(8, form.getGrID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at processApplication!!");
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    //get ID lec
    public int getLectureID(String Name) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int ID = 0;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Lecturer WHERE Name =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ID = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getIDLecture!!!");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return ID;
    }

    //getListForm
    public List<Application> getListForm(String email) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT f.ID, f.StudentID, f.CreateDate, f.Type, "
                        + "f.Reason, f.LecID, f.ProcessDate, f.Note, f.Status, "
                        + "f.CourseID, s.Email, f.Room, f.PresentDate, f.Time "
                        + "FROM Form f LEFT JOIN  Student s "
                        + "ON s.ID = f.StudentID WHERE s.Email = (SELECT Email "
                        + "FROM Account WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int StID = rs.getInt("StudentID");
                    String Stcode = getStudentCode(StID);
                    String CreDate = rs.getString("CreateDate");
                    String type = rs.getString("Type");
                    String reason = rs.getString("Reason");
                    int lecID = rs.getInt("lecID");
                    String lecName = getNameLecture(lecID);
                    String processDate = rs.getString("ProcessDate");
                    String lecNote = rs.getString("Note");
                    String status = rs.getString("Status");
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = getCourseName(CourseID);
                    String room = rs.getString("Room");
                    String preDate = rs.getString("PresentDate");
                    String time = rs.getString("Time");
                    list.add(new Application(id, StID, Stcode, CreDate, type,
                            lecID, lecName, reason, lecNote, status, CourseID,
                            CourseName, processDate, email, room, preDate, time));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListSubject!!!");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    //getGroupID
    public int getGroupID(String team, int courseID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int GroupID = 0;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Groupp "
                        + "WHERE Name = ? AND CourseID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, team);
                stm.setInt(2, courseID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    GroupID = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return GroupID;
    }

    //getnameCourse
    public String getCourseName(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Name FROM Course WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getCourseName!!!");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return name;
    }

    //getnameLec
    public String getNameLecture(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Name FROM Lecturer WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("Name");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getNameLecture!!!");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return name;
    }

    //getcodeStudent
    public String getStudentCode(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String Code = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Code FROM Student WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Code = rs.getString("Code");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getStudentCode!!!");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return Code;
    }

    //checkStudentID
    public int checkStuID(String email) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Student "
                        + "WHERE Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if(rs.next()){
                    id = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return id;
    }

    public List<Application> getListPresentationCapstone(String email) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT c.CourseID, c.[CourseCode], c.SubjectID, "
                        + " c.[SubjectCode], c.GroupID, g.Code, c.Room, c.Time, "
                        + " c.PresentDate, c.Type FROM (SELECT b.CourseID, "
                        + " b.[CourseCode], b.SubjectID, s.Code as[SubjectCode], "
                        + " b.GroupID, b.Room, b.Time, b.PresentDate, b.Type "
                        + " FROM (SELECT a.CourseID, c.Name as [CourseCode], "
                        + " a.SubjectID, a.GroupID, a.Room, a.Time, a.PresentDate, "
                        + " a.Type FROM (SELECT f.CourseID, f.SubjectID, f.GroupID, "
                        + " f.Room, f.Time, f.PresentDate, f.Type, f.StudentID, s.Email "
                        + " FROM Form f LEFT JOIN Student s ON f.StudentID = s.ID "
                        + " WHERE f.Type IN('Presentation') "
                        + " AND f.Publish = 'published' "
                        + " AND s.Email = (SELECT Email FROM Account WHERE Email = ?)) AS a "
                        + " LEFT JOIN Course c ON a.CourseID = c.ID) AS b "
                        + " LEFT JOIN Subject s ON b.SubjectID = s.ID) as c "
                        + " LEFT JOIN Groupp g ON c.GroupID = g.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    String CourseCode = rs.getString("CourseCode");
                    int SubID = rs.getInt("SubjectID");
                    String SubCode = rs.getString("SubjectCode");
                    int GrID = rs.getInt("GroupID");
                    String GrCode = rs.getString("Code");
                    String room = rs.getString("Room");
                    String time = rs.getString("Time");
                    String preDate = rs.getString("PresentDate");
                    String type = rs.getString("Type");
                    list.add(new Application(type, CourseID, CourseCode, SubID,
                            SubCode, GrID, GrCode, room, preDate, time));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<Application> getListPresentationMNG(String search) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT S.Code AS SubjectCode, S.ID AS SubjectID, C.Name AS CourseName, "
                        + "C.Code AS CourseCode, G.Name AS GroupName, F.Room, F.PresentDate, F.Time "
                        + "FROM (SELECT * FROM Form "
                        + "WHERE LecID = (SELECT ID FROM Lecturer WHERE Email = ?) "
                        + "AND Type = 'Presentation' AND Publish = 'published') AS F LEFT JOIN Course C "
                        + "ON F.CourseID = C.ID LEFT JOIN Subject S "
                        + "ON F.SubjectID = S.ID LEFT JOIN Groupp G "
                        + "ON F.GroupID = G.ID	";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    String GroupName = rs.getString("GroupName");
                    String Room = rs.getString("Room");
                    String PresentDate = rs.getString("PresentDate");
                    String Time = rs.getString("Time");
                    list.add(new Application(CourseCode, CourseName, SubjectID, 
                            SubjectCode, GroupName, Room, PresentDate, Time));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<Application> processingApplication(String email) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select ID, LecID, LecturerName, LecturerEmail, Type, StudentID, StudentCode, Reason, "
                        + "CreateDate, ProcessDate, Status, CourseID, CourseName, Note, SubjectID, "
                        + "SubjectCode, GroupID, GroupName, Room, PresentDate, Time, Publish, TopicID, TopicCode "
                        + "from (select na.*, t.Code as TopicCode "
                        + "from (select na.*, p.TopicID as TopicID "
                        + "from (select na.*, g.Name as GroupName "
                        + "from (select na.*, s.Code as SubjectCode "
                        + "from (select na.*, c.Name as CourseName "
                        + "from (select na.*, s.Code as StudentCode "
                        + "from (select f.*, l.Name as LecturerName, l.Email as LecturerEmail "
                        + "from Form f LEFT JOIN Lecturer l "
                        + "ON f.LecID = l.ID) as na LEFT JOIN Student s "
                        + "ON na.StudentID = s.ID) as na LEFT JOIN Course c "
                        + "ON na.CourseID = c.ID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID) as na LEFT JOIN Groupp g "
                        + "ON na.GroupID = g.ID) as na LEFT JOIN Project p "
                        + "ON na.GroupID = p.GroupID) as na LEFT JOIN Topic t "
                        + "ON na.TopicID = t.ID) as na "
                        + "where LecturerEmail = ? and Status = 'Processing'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int formID = rs.getInt("ID");
                    int lecID = rs.getInt("LecID");
                    String lecturerName = rs.getString("LecturerName");
                    String type = rs.getString("Type");
                    int studentID = rs.getInt("StudentID");
                    String StudentCode = rs.getString("StudentCode");
                    String Reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String ProcessDate = rs.getString("ProcessDate");
                    String Status = rs.getString("Status");
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = rs.getString("CourseName");
                    String note = rs.getString("Note");
                    int SubjectID = rs.getInt("SubjectID");
                    String SubjectCode = rs.getString("SubjectCode");
                    int GroupID = rs.getInt("GroupID");
                    String GroupName = rs.getString("GroupName");
                    int TopicID = rs.getInt("TopicID");
                    String TopicCode = rs.getString("TopicCode");
                    list.add(new Application(type, formID, lecID, lecturerName,
                            studentID, StudentCode, Reason, note, createDate,
                            ProcessDate, Status, CourseID, CourseName, SubjectID,
                            SubjectCode, GroupID, GroupName, TopicID, TopicCode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public List<Application> detailtApplication(int id) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select ID, LecID, LecturerName, LecturerEmail, Type, StudentID, StudentCode, Reason, "
                        + "CreateDate, ProcessDate, Status, CourseID, CourseName, Note, SubjectID, "
                        + "SubjectCode, GroupID, GroupName, Room, PresentDate, Time, Publish, TopicID, TopicCode "
                        + "from (select na.*, t.Code as TopicCode "
                        + "from (select na.*, p.TopicID as TopicID "
                        + "from (select na.*, g.Name as GroupName "
                        + "from (select na.*, s.Code as SubjectCode "
                        + "from (select na.*, c.Name as CourseName "
                        + "from (select na.*, s.Code as StudentCode "
                        + "from (select f.*, l.Name as LecturerName, l.Email as LecturerEmail "
                        + "from Form f LEFT JOIN Lecturer l "
                        + "ON f.LecID = l.ID) as na LEFT JOIN Student s "
                        + "ON na.StudentID = s.ID) as na LEFT JOIN Course c "
                        + "ON na.CourseID = c.ID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID) as na LEFT JOIN Groupp g "
                        + "ON na.GroupID = g.ID) as na LEFT JOIN Project p "
                        + "ON na.GroupID = p.GroupID) as na LEFT JOIN Topic t "
                        + "ON na.TopicID = t.ID) as na "
                        + "where ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int formID = rs.getInt("ID");
                    int lecID = rs.getInt("LecID");
                    String lecturerName = rs.getString("LecturerName");
                    String type = rs.getString("Type");
                    int studentID = rs.getInt("StudentID");
                    String StudentCode = rs.getString("StudentCode");
                    String Reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String ProcessDate = rs.getString("ProcessDate");
                    String Status = rs.getString("Status");
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = rs.getString("CourseName");
                    String note = rs.getString("Note");
                    int SubjectID = rs.getInt("SubjectID");
                    String SubjectCode = rs.getString("SubjectCode");
                    int GroupID = rs.getInt("GroupID");
                    String GroupName = rs.getString("GroupName");
                    int TopicID = rs.getInt("TopicID");
                    String TopicCode = rs.getString("TopicCode");
                    list.add(new Application(type, formID, lecID, lecturerName,
                            studentID, StudentCode, Reason, note, createDate,
                            ProcessDate, Status, CourseID, CourseName, SubjectID,
                            SubjectCode, GroupID, GroupName, TopicID, TopicCode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public boolean approveApplication(Application application) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update Form"
                        + " set Note= ?, Status=?  "
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, application.getLecNote());
                stm.setString(2, application.getStatus());
                stm.setInt(3, application.getID());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public List<Application> processedApplication(String email) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select ID, LecID, LecturerName, LecturerEmail, Type, StudentID, StudentCode, Reason, "
                        + "CreateDate, ProcessDate, Status, CourseID, CourseName, Note, SubjectID, "
                        + "SubjectCode, GroupID, GroupName, Room, PresentDate, Time, Publish, TopicID, TopicCode "
                        + "from (select na.*, t.Code as TopicCode "
                        + "from (select na.*, p.TopicID as TopicID "
                        + "from (select na.*, g.Name as GroupName "
                        + "from (select na.*, s.Code as SubjectCode "
                        + "from (select na.*, c.Name as CourseName "
                        + "from (select na.*, s.Code as StudentCode "
                        + "from (select f.*, l.Name as LecturerName, l.Email as LecturerEmail "
                        + "from Form f LEFT JOIN Lecturer l "
                        + "ON f.LecID = l.ID) as na LEFT JOIN Student s "
                        + "ON na.StudentID = s.ID) as na LEFT JOIN Course c "
                        + "ON na.CourseID = c.ID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID) as na LEFT JOIN Groupp g "
                        + "ON na.GroupID = g.ID) as na LEFT JOIN Project p "
                        + "ON na.GroupID = p.GroupID) as na LEFT JOIN Topic t "
                        + "ON na.TopicID = t.ID) as na "
                        + "where LecturerEmail = ? and Status = 'Processed' or "
                        + "Status = 'Not Processed'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int formID = rs.getInt("ID");
                    int lecID = rs.getInt("LecID");
                    String lecturerName = rs.getString("LecturerName");
                    String type = rs.getString("Type");
                    int studentID = rs.getInt("StudentID");
                    String StudentCode = rs.getString("StudentCode");
                    String Reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String ProcessDate = rs.getString("ProcessDate");
                    String Status = rs.getString("Status");
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = rs.getString("CourseName");
                    String note = rs.getString("Note");
                    int SubjectID = rs.getInt("SubjectID");
                    String SubjectCode = rs.getString("SubjectCode");
                    int GroupID = rs.getInt("GroupID");
                    String GroupName = rs.getString("GroupName");
                    int TopicID = rs.getInt("TopicID");
                    String TopicCode = rs.getString("TopicCode");
                    list.add(new Application(type, formID, lecID, lecturerName,
                            studentID, StudentCode, Reason, note, createDate,
                            ProcessDate, Status, CourseID, CourseName, SubjectID,
                            SubjectCode, GroupID, GroupName, TopicID, TopicCode));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
