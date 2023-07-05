/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerDAO;

import DBUtil.Util;
import DTO.PresentationMNG;
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
public class PresentationMNGDAO {
    public List<PresentationMNG> searchPresenApplicationByID(String search) throws SQLException {
        List<PresentationMNG> list = new ArrayList<>();
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
                        + "WHERE na.Status = 'Processed' and tv.Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int formID = rs.getInt("ID");  
                    String type = rs.getString("Type");
                    String subjectID = rs.getString("SubjectID");
                    String courseID = rs.getString("CourseID");
                    int groupID = rs.getInt("GroupID");
                    int room = rs.getInt("Room");
                    String presentDate = rs.getString("PresentDate");
                    String time = rs.getString("Time");
                    list.add(new PresentationMNG(formID, type, subjectID, courseID, groupID, room, presentDate, time));
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
    
    public boolean checkDuplicateSubjectID(int id, String code) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID, Code"
                        + " from Subject"
                        + " where ID=? and Code=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, code);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateCourseID(int code, String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select Code, Name"
                        + " from Course"
                        + " where Code=? and Name=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, code);
                stm.setString(2, name);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public int getCourseID(int code, String name) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Course"
                        + " where Code=? and Name=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, code);
                stm.setString(2, name);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int courseID = rs.getInt("ID");
                    result = courseID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return result;
    }
    
    public int getLecturerID(String email) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Lecturer"
                        + " where email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int lecID = rs.getInt("ID");
                    result = lecID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return result;
    }
    
     public boolean postPresentation(int subjectID, int  courseID, int lecturerID, int room, String presentDate, String time, String note) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "INSERT INTO Form(SubjectID, CourseID, LecID, Room, PresentDate, Time, Note, Type, CreateDate, ProcessDate, Status)"
                        + " values(?,?,?,?,?,?,?,'Presentation',GETDATE(),GETDATE(),'Processed')";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subjectID);
                stm.setInt(2, courseID);
                stm.setInt(3, lecturerID);
                stm.setInt(4, room);
                stm.setString(5, presentDate);
                stm.setString(6, time);
                stm.setString(7, note);
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
