/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerDAO;

import DBUtil.Util;
import DTO.ClassMNG;
import DTO.StudentMNG;
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
public class ClassMNGDAO {
    public List<ClassMNG> getListClassMNG(String search) throws SQLException {
        List<ClassMNG> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT na.*, tv.Email "
                        + "FROM (SELECT na.ID, na.Class, na.Semseter, tv.Subject, na.StartDate, na.EndDate, tv.CourseID, tv.LecturerID, tv.SubjectID "
                        + "FROM (SELECT c.ID, CONCAT(c.Name, CAST(c.Code AS VARCHAR)) as Class, CONCAT(s.Name, CAST(s.Year AS VARCHAR)) as Semseter, c.StartDate, c.EndDate "
                        + "FROM Course c LEFT JOIN Semester s ON c.SemesterID = s.ID) as na LEFT JOIN (SELECT sic.CourseID, CONCAT(s.Code, CAST(s.ID AS VARCHAR)) as Subject, sic.LecturerID, sic.SubjectID "
                        + "FROM SubjectInClass sic LEFT JOIN Subject s ON sic.SubjectID = s.ID) as tv ON na.ID = tv.CourseID) as na LEFT JOIN (SELECT ID, Email FROM Lecturer) as tv "
                        + "ON na.LecturerID = tv.ID "
                        + "WHERE Email=? AND GETDATE() >= StartDate AND GETDATE() <= EndDate";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("ID");
                    int subjectID = rs.getInt("SubjectID");
                    String classID = rs.getString("Class");
                    String semester = rs.getString("Semseter");
                    String subject = rs.getString("Subject");
                    String startDate = rs.getString("StartDate");
                    String endDate = rs.getString("EndDate");
                    String email = rs.getString("Email");
                    list.add(new ClassMNG(id, subjectID, classID, semester, subject, startDate, endDate, email));
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
 
    public List<StudentMNG> getListStudentByID(String search) throws SQLException {
        List<StudentMNG> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "select distinct na.StudentID, na.Gender, na.Name, na.Birthday, na.Address, na.City, na.PhoneNumber, na.Email, tv.GroupName, na.isLeader, tv.CourseID "
                        + "from (select na.*, tv.GroupID, tv.isLeader "
                        + "from (select ID, CONCAT(Code, CAST(ID AS VARCHAR)) as StudentID, Gender, Name, Birthday, Address, City, PhoneNumber, Email "
                        + "from Student) as na LEFT JOIN (select StudentID, GroupID, isLeader from Member) as tv "
                        + "ON na.ID = tv.StudentID) as na LEFT JOIN (select ID, Name as GroupName, CourseID from Groupp) as tv "
                        + "ON na.GroupID = tv.ID "
                        + "where CourseID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String studentID = rs.getString("StudentID");
                    String gender = rs.getString("Gender");
                    String studentName = rs.getString("Name");
                    String birthday = rs.getString("Birthday");
                    String address = rs.getString("Address");
                    String city = rs.getString("City");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String email = rs.getString("Email");
                    String groupName = rs.getString("GroupName");
                    String member = rs.getString("isLeader");
                    list.add(new StudentMNG(studentID, gender, studentName, birthday, address, city, phoneNumber, email, groupName, member));
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
    
}
