package userDAO;

import DBUtil.Util;
import DTO.Application;
import adminDAO.UserAccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                        + "Form(LecID, Type, StudentID, Reason, CreateDate, Status,CourseID) "
                        + "VALUES (?,?,?,?,GETDATE(),?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, form.getLecID());
                stm.setString(2, form.getType());
                stm.setInt(3, form.getStuID());
                stm.setString(4, form.getReason());
                stm.setString(5, "processing");
                stm.setInt(6, form.getCourseID());
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
                        + "f.CourseID, s.Email FROM Form f LEFT JOIN  Student s "
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
                    list.add(new Application(id, StID, Stcode, CreDate, type,
                            lecID, lecName, reason, lecNote, status, CourseID,
                            CourseName, processDate, email));
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

    //getlist Form application
    public List<Application> getListApplication() throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT n.ID, n.LecName, n.Type, n.Publish, n.CourseID,n.CourseName, "
                        + "n.GroupID, n.[GroupCode], n.PresentDate, n.ProcessDate,n.Room, n.CreateDate, "
                        + "n.Status, n.SubjectID,s.Code, n.Time FROM "
                        + "(SELECT qa.ID, qa.Name as [LecName], qa.CourseID,qa.[CourseName], "
                        + "qa.GroupID, g.Code as [GroupCode], qa.PresentDate, qa.ProcessDate,qa.Room, "
                        + "qa.CreateDate, qa.Status, qa.SubjectID, qa.Time, qa.Type, qa.Publish "
                        + "FROM (SELECT f.ID, f.Name, f.CourseID,c.Name as [CourseName], "
                        + "f.GroupID, f.PresentDate, f.ProcessDate, f.Room, f.CreateDate, "
                        + "f.Status, f.SubjectID, f.Time, f.Type, f.Publish FROM (SELECT a.ID, l.Name, "
                        + "a.CourseID, a.GroupID, a.PresentDate, a.ProcessDate, a.Room, a.CreateDate, "
                        + "a.Status, a.SubjectID, a.Time, a.Type, a.Publish "
                        + "FROM (SELECT ID, LecID, Type, StudentID, CreateDate, "
                        + "ProcessDate, Status, CourseID, SubjectID, GroupID, "
                        + "Room, PresentDate, Time, Publish "
                        + "FROM Form WHERE Type = 'Presentation Capstone' AND Status = 'Processed' "
                        + "AND Publish IN ('unpublish','published')) AS a "
                        + "LEFT JOIN Lecturer l ON a.LecID = l.ID) AS f "
                        + "LEFT JOIN Course c ON f.CourseID = c.ID) AS qa "
                        + "LEFT JOIN Groupp g ON g.ID = qa.GroupID) AS n "
                        + "LEFT JOIN Subject s ON n.SubjectID = s.ID";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String lec = rs.getString("LecName");
                    int couID = rs.getInt("CourseID");
                    String couName = rs.getString("CourseName");
                    int grID = rs.getInt("GroupID");
                    String grCode = rs.getString("GroupCode");
                    String preDate = rs.getString("PresentDate");
                    String ProDate = rs.getString("ProcessDate");
                    String room = rs.getString("Room");
                    String creDate = rs.getString("CreateDate");
                    String Status = rs.getString("Status");
                    int SubID = rs.getInt("SubjectID");
                    String subCode = rs.getString("Code");
                    String time = rs.getString("Time");
                    String type = rs.getString("Type");
                    String publish = rs.getString("Publish");
                    list.add(new Application(id, type, lec, creDate, ProDate,
                            Status, couID, couName, SubID, subCode, grID, grCode, room,
                            preDate, time, publish));
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

    //getlist Form application
    public List<Application> getListApplication(String search) throws SQLException {
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT n.ID, n.LecName, n.Type, n.Publish, n.CourseID,n.CourseName, "
                        + "n.GroupID, n.[GroupCode], n.PresentDate, n.ProcessDate,n.Room, n.CreateDate, "
                        + "n.Status, n.SubjectID,s.Code, n.Time FROM "
                        + "(SELECT qa.ID, qa.Name as [LecName], qa.CourseID,qa.[CourseName], "
                        + "qa.GroupID, g.Code as [GroupCode], qa.PresentDate, qa.ProcessDate,qa.Room, "
                        + "qa.CreateDate, qa.Status, qa.SubjectID, qa.Time, qa.Type, qa.Publish "
                        + "FROM (SELECT f.ID, f.Name, f.CourseID,c.Name as [CourseName], "
                        + "f.GroupID, f.PresentDate, f.ProcessDate, f.Room, f.CreateDate, "
                        + "f.Status, f.SubjectID, f.Time, f.Type, f.Publish FROM (SELECT a.ID, l.Name, "
                        + "a.CourseID, a.GroupID, a.PresentDate, a.ProcessDate, a.Room, a.CreateDate, "
                        + "a.Status, a.SubjectID, a.Time, a.Type, a.Publish "
                        + "FROM (SELECT ID, LecID, Type, StudentID, CreateDate, "
                        + "ProcessDate, Status, CourseID, SubjectID, GroupID, "
                        + "Room, PresentDate, Time, Publish "
                        + "FROM Form WHERE Type = 'Presentation Capstone' AND Status = 'Processed' "
                        + "AND Publish IN ('unpublish','published')) AS a "
                        + "LEFT JOIN Lecturer l ON a.LecID = l.ID) AS f "
                        + "LEFT JOIN Course c ON f.CourseID = c.ID) AS qa "
                        + "LEFT JOIN Groupp g ON g.ID = qa.GroupID) AS n "
                        + "LEFT JOIN Subject s ON n.SubjectID = s.ID WHERE n.CreateDate = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String lec = rs.getString("LecName");
                    int couID = rs.getInt("CourseID");
                    String couName = rs.getString("CourseName");
                    int grID = rs.getInt("GroupID");
                    String grCode = rs.getString("GroupCode");
                    String preDate = rs.getString("PresentDate");
                    String ProDate = rs.getString("ProcessDate");
                    String room = rs.getString("Room");
                    String creDate = rs.getString("CreateDate");
                    String Status = rs.getString("Status");
                    int SubID = rs.getInt("SubjectID");
                    String subCode = rs.getString("Code");
                    String time = rs.getString("Time");
                    String type = rs.getString("Type");
                    String publish = rs.getString("Publish");
                    list.add(new Application(id, type, lec, creDate, ProDate,
                            Status, couID, couName, SubID, subCode, grID, grCode, room,
                            preDate, time, publish));
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

    //updateFormPresentation
    public boolean updateForm(Application app) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Form SET Room = ?, PresentDate = ?, Time =?, Publish = ? "
                        + "WHERE CreateDate = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, app.getRoom());
                stm.setString(2, app.getPresentDate());
                stm.setString(3, app.getTime());
                stm.setString(4, "published");
                stm.setString(5, app.getCreateDate());
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
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
                        + " WHERE f.Type IN('Presentation Capstone','Presentation In Class') "
                        + " AND f.Publish = 'published' "
                        + " AND s.Email = (SELECT Email FROM Account WHERE Email = ?)) AS a "
                        + " LEFT JOIN Course c ON a.CourseID = c.ID) AS b "
                        + " LEFT JOIN Subject s ON b.SubjectID = s.ID) as c "
                        + " LEFT JOIN Groupp g ON c.GroupID = g.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while(rs.next()){
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
}
