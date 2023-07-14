package user.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Class;
import system.main.DTO.ClassInformation;
import system.main.DTO.Semester;

public class ClassUserDAO {

    public List<Class> getListClass(int SemesterID, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<system.main.DTO.Class> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT C.*, E.StudentID "
                        + "FROM Course C LEFT JOIN Enrollment E "
                        + "ON C.ID = E.CourseID "
                        + "WHERE C.SemesterID = ? "
                        + "AND E.StudentID = (SELECT ID FROM Student WHERE "
                        + "Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SemesterID);
                stm.setString(2, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int CourseID = rs.getInt("ID");
                    String CourseName = rs.getString("Name");
                    int CourseCode = rs.getInt("Code");
                    String StartDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    list.add(new system.main.DTO.Class(CourseID, CourseName,
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

    public Semester getNameSemester(int sesID) throws SQLException {
        Semester ses = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Semester "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, sesID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String sesName = rs.getString("Name");
                    int sesYear = rs.getInt("Year");
                    ses = new Semester(sesName, sesYear);
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
        return ses;
    }

    public ClassInformation getInforClass(int courseID, int subID) throws SQLException {
        ClassInformation course = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, S.Code as SubjectCode "
                        + "FROM (SELECT C.Name, C.Code, SIC.LecturerID, C.SemesterID, SIC.SubjectID "
                        + "FROM SubjectInClass SIC RIGHT JOIN Course C "
                        + "ON SIC.CourseID = C.ID "
                        + "WHERE C.ID = ? AND SubjectID = ?) AS A LEFT JOIN "
                        + "Subject S ON A.SubjectID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String CourseName = rs.getString("Name");
                    int CourseCode = rs.getInt("Code");
                    int lecID = rs.getInt("LecturerID");
                    int sesID = rs.getInt("SemesterID");
                    String subCode = rs.getString("SubjectCode");
                    course = new ClassInformation(subCode, subID, lecID, CourseName, CourseCode, sesID);
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
        return course;
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

    public int getIDStudent(String email) throws SQLException {
        int ID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Student WHERE Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ID = rs.getInt("ID");
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
        return ID;
    }
}
