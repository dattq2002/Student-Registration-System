package admin.DAO;

import DBUtil.Util;
import DTO.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationAdminDAO {
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
                        + "FROM Form WHERE Type = 'Presentation' AND Status = 'Processed' "
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
}
