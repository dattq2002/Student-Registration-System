package managerDAO;

import DBUtil.Util;
import DTO.ApplicationMNG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam An
 */
public class ApplicationMNGDAO {
    public List<ApplicationMNG> getListApplicationMNG(String search) throws SQLException {
        List<ApplicationMNG> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT na.*, tv.Email "
                        + "FROM (SELECT na.ID, na.Type, tv.SubjectID, na.CourseID, "
                        + "na.GroupID, na.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status "
                        + "FROM (SELECT na.ID, na.Type, na.SubjectID, na.CourseID, "
                        + "na.GroupID, tv.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status "
                        + "FROM (SELECT f.ID, f.Type, f.SubjectID, na.CourseID, "
                        + "f.GroupID, f.StudentID, f.Reason, f.CreateDate, f.Note, f.LecID, f.Status "
                        + "FROM Form f LEFT JOIN (SELECT ID, CONCAT(Name, CAST(ID AS VARCHAR)) as CourseID "
                        + "FROM Course) as na ON f.CourseID = na.ID) AS na LEFT JOIN "
                        + "(SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as StudentID "
                        + "FROM Student) as tv ON na.StudentID = tv.ID) as na LEFT JOIN "
                        + "(SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as SubjectID "
                        + "FROM Subject) as tv ON na.SubjectID = tv.ID) as na LEFT JOIN "
                        + "(SELECT ID, Email FROM Lecturer) as tv "
                        + "ON na.LecID = tv.ID "
                        + "WHERE tv.Email =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int formID = rs.getInt("ID");  
                    String type = rs.getString("Type");
                    String subjectID = rs.getString("SubjectID");
                    String courseID = rs.getString("CourseID");
                    int groupID = rs.getInt("GroupID");
                    String studentID = rs.getString("StudentID");
                    String reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String note = rs.getString("Note");
                    list.add(new ApplicationMNG(formID, type, subjectID, 
                            courseID, groupID, studentID, reason, createDate, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public List<ApplicationMNG> searchReportApplicationByID(String search) throws SQLException {
        List<ApplicationMNG> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT na.*, tv.Email "
                        + "FROM (SELECT na.ID, na.Type, tv.SubjectID, na.CourseID, na.GroupID, na.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status "
                        + "FROM (SELECT na.ID, na.Type, na.SubjectID, na.CourseID, na.GroupID, tv.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status "
                        + "FROM (SELECT f.ID, f.Type, f.SubjectID, na.CourseID, f.GroupID, f.StudentID, f.Reason, f.CreateDate, f.Note, f.LecID, f.Status "
                        + "FROM Form f LEFT JOIN (SELECT ID, CONCAT(Name, CAST(ID AS VARCHAR)) as CourseID "
                        + "FROM Course) as na ON f.CourseID = na.ID) AS na LEFT JOIN (SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as StudentID "
                        + "FROM Student) as tv ON na.StudentID = tv.ID) as na LEFT JOIN (SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as SubjectID "
                        + "FROM Subject) as tv ON na.SubjectID = tv.ID) as na LEFT JOIN (SELECT ID, Email FROM Lecturer) as tv "
                        + "ON na.LecID = tv.ID "
                        + "WHERE na.ID =? and na.Status = 'Processing' and na.Type = 'Report'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int formID = rs.getInt("ID");  
                    String type = rs.getString("Type");
                    String subjectID = rs.getString("SubjectID");
                    String courseID = rs.getString("CourseID");
                    int groupID = rs.getInt("GroupID");
                    String studentID = rs.getString("StudentID");
                    String reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String note = rs.getString("Note");
                    list.add(new ApplicationMNG(formID, type, subjectID, courseID, groupID, studentID, reason, createDate, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public List<ApplicationMNG> searchPresenApplicationByID(String search) throws SQLException {
        List<ApplicationMNG> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT na.*, tv.Email "
                        + "FROM (SELECT na.ID, na.Type, tv.SubjectID, na.CourseID, na.GroupID, na.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status, na.Room, na.PresentDate, na.Time "
                        + "FROM (SELECT na.ID, na.Type, na.SubjectID, na.CourseID, na.GroupID, tv.StudentID, na.Reason, na.CreateDate, na.Note, na.LecID, na.Status, na.Room, na.PresentDate, na.Time "
                        + "FROM (SELECT f.ID, f.Type, f.SubjectID, na.CourseID, f.GroupID, f.StudentID, f.Reason, f.CreateDate, f.Note, f.LecID, f.Status, f.Room, f.PresentDate, f.Time "
                        + "FROM Form f LEFT JOIN (SELECT ID, CONCAT(Name, CAST(ID AS VARCHAR)) as CourseID "
                        + "FROM Course) as na ON f.CourseID = na.ID) AS na LEFT JOIN (SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as StudentID "
                        + "FROM Student) as tv ON na.StudentID = tv.ID) as na LEFT JOIN (SELECT ID, CONCAT(Code, CAST(ID AS VARCHAR)) as SubjectID "
                        + "FROM Subject) as tv ON na.SubjectID = tv.ID) as na LEFT JOIN (SELECT ID, Email FROM Lecturer) as tv "
                        + "ON na.LecID = tv.ID "
                        + "WHERE na.ID =? and na.Status = 'Processing' and na.Type = 'Presentation'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int formID = rs.getInt("ID");  
                    String type = rs.getString("Type");
                    String subjectID = rs.getString("SubjectID");
                    String courseID = rs.getString("CourseID");
                    int groupID = rs.getInt("GroupID");
                    String studentID = rs.getString("StudentID");
                    String reason = rs.getString("Reason");
                    String createDate = rs.getString("CreateDate");
                    String note = rs.getString("Note");
                    int room = rs.getInt("Room");
                    String presentDate = rs.getString("PresentDate");
                    String time = rs.getString("Time");
                    list.add(new ApplicationMNG(formID, type, subjectID, courseID, groupID, studentID, reason, createDate, note, room, presentDate, time));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public boolean approveReportApplication(ApplicationMNG application) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Form"
                        + " set Note= ?, Status='Processed'  "
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, application.getNote());
                stm.setInt(2, application.getID());
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean approvePresentApplication(ApplicationMNG application) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Form"
                        + " set Note= ?, Room= ?, PresentDate= ?, Time= ?, Status='Processed'  "
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, application.getNote());
                stm.setInt(2, application.getRoom());
                stm.setString(3, application.getPresentDate());
                stm.setString(4, application.getTime());
                stm.setInt(5, application.getID());  
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean refuseReportApplication(ApplicationMNG application) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Form"
                        + " set Note= ?, Status='Not Processed'  "
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, application.getNote());
                stm.setInt(2, application.getID());
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean refusePresentApplication(ApplicationMNG application) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Form"
                        + " set Note= ?, Room= ?, PresentDate= ?, Time= ?, Status='Not Processed'  "
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, application.getNote());
                stm.setInt(2, application.getRoom());
                stm.setString(3, application.getPresentDate());
                stm.setString(4, application.getTime());
                stm.setInt(5, application.getID());  
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
}
