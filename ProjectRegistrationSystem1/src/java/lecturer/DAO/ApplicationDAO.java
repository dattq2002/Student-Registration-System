/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturer.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Application;

/**
 *
 * @author Nam An
 */
public class ApplicationDAO {

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
