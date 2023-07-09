package user.DAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassStudentDAO {
    public List<ClassInformation> getListClassEnrollment(String email) throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, S.Code "
                        + "FROM (SELECT E.SubjectID, C.Code AS [CourseCode], "
                        + "C.Name AS [CourseName], E.CourseID "
                        + "FROM Enrollment E LEFT JOIN Course C "
                        + "ON E.CourseID = C.ID "
                        + "WHERE E.StudentID = (SELECT ID FROM Student WHERE "
                        + "Email = ?) AND C.SemesterID = 11114) AS A "
                        + "LEFT JOIN Subject S ON A.SubjectID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String subCode = rs.getString("Code");
                    int subID = rs.getInt("SubjectID");
                    String courseName = rs.getString("CourseName");
                    int courseCode = rs.getInt("CourseCode");
                    int courseID = rs.getInt("CourseID");
                    list.add(new ClassInformation(subCode, subID, courseID,
                            courseName, courseCode));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }
    
    //getDetailEnrollment
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
