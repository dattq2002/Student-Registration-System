package lecturer.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Class;
import system.main.DTO.StudentProfile;

public class ClassDAO {

    //getListClass in semester
    public List<Class> getListClass(int SemesterID, String Email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Class> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT C.*, S.LecturerID "
                        + "FROM Course C LEFT JOIN SubjectInClass S "
                        + "ON C.ID = S.CourseID "
                        + "WHERE C.SemesterID = ? "
                        + "AND S.LecturerID = (SELECT ID FROM Lecturer WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SemesterID);
                stm.setString(2, Email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int CourseID = rs.getInt("ID");
                    String CourseName = rs.getString("Name");
                    int CourseCode = rs.getInt("Code");
                    String StartDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    list.add(new Class(CourseID, CourseName,
                            CourseCode, SemesterID, StartDate, EndDate));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
        return list;
    }

    //getListStudentInClass
    public List<StudentProfile> getListStudentInClass(int courseID, int subID, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<StudentProfile> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM (SELECT E.StudentID "
                        + "FROM Enrollment E LEFT JOIN SubjectInClass S "
                        + "ON E.CourseID = S.CourseID AND E.SubjectID = S.SubjectID "
                        + "WHERE E.CourseID = ? AND E.SubjectID = ? "
                        + "AND S.LecturerID = (SELECT ID FROM Lecturer WHERE Email = ?)) AS A "
                        + "LEFT JOIN Student S "
                        + "ON A.StudentID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
                stm.setString(3, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int stuID = rs.getInt("ID");
                    String stuCode = rs.getString("Code");
                    String stuName = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(stuID, stuCode, stuName, Birthday,
                            PhoneNumber, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
        return list;
    }

    public String getLecName(int courseID, int subID) throws SQLException {
        String lecName = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Name FROM Lecturer "
                        + "WHERE ID = (SELECT LecturerID FROM SubjectInClass "
                        + "WHERE CourseID = ? AND SubjectID = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    lecName = rs.getString("Name");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
        return lecName;
    }

    public List<Class> getInformationClass(int courseID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Class> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Course WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String CourseName = rs.getString("Name");
                    int CourseCode = rs.getInt("Code");
                    String StartDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    int SesID = rs.getInt("SemesterID");
                    list.add(new Class(courseID, CourseName, CourseCode, SesID,
                            StartDate, EndDate));
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
        return list;
    }
}
