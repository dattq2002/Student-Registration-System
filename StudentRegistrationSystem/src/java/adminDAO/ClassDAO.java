package adminDAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.StudentProfile;
import DTO.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.Class;

public class ClassDAO {

    //getListSubject
    public List<Subject> getListSubject() throws SQLException {
        List<Subject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Subject";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    int credit = rs.getInt("CreditID");
                    list.add(new Subject(id, code, name, description, credit));
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

    //getListClass
    public List<ClassInformation> getListClass() throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, b.CourseID, "
                        + "c.StartDate, c.EndDate, c.Status FROM (SELECT a.ID, a.SubjectCode, "
                        + "a.SubjectID, lec.Name AS [LecName], a.CourseID FROM (SELECT s.ID, "
                        + "su.Code as [SubjectCode], su.ID as [SubjectID], s.CourseID, s.LecturerID "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String subCode = rs.getString("SubjectCode");
                    int subID = rs.getInt("SubjectID");
                    String lecName = rs.getString("LecName");
                    String Coursecode = rs.getString("Name");
                    int CourseID = rs.getInt("CourseID");
                    String start = rs.getString("StartDate");
                    String end = rs.getString("EndDate");
                    boolean status = rs.getBoolean("Status");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID, start, end, status));
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
    
    //getListClass
    public List<ClassInformation> getListClass(String search) throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, b.CourseID, "
                        + "c.StartDate, c.EndDate, c.Status FROM (SELECT a.ID, a.SubjectCode, "
                        + "a.SubjectID, lec.Name AS [LecName], a.CourseID FROM (SELECT s.ID, "
                        + "su.Code as [SubjectCode], su.ID as [SubjectID], s.CourseID, s.LecturerID "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID WHERE b.SubjectCode LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String subCode = rs.getString("SubjectCode");
                    int subID = rs.getInt("SubjectID");
                    String lecName = rs.getString("LecName");
                    String Coursecode = rs.getString("Name");
                    String start = rs.getString("StartDate");
                    String end = rs.getString("EndDate");
                    boolean status = rs.getBoolean("Status");
                    int CourseID = rs.getInt("CourseID");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID, start, end, status));
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

    public List<StudentProfile> getDetailEnrollment(int id) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student WHERE ID IN (SELECT StudentID "
                        + "FROM Enrollment WHERE CourseID = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Major = rs.getString("Major");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(ID, code, Name, Birthday, PhoneNum,
                            Gender, Address, City, Major, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getDetailEnrollment !!");
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
