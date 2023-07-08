package managerDAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.Class;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassMNGDAO {

    public List<ClassInformation> getListClassMNG(int lecID) throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select s.Code as SubjectCode, s.ID as SubjectID, na.CourseName, na.CourseCode, na.CourseID "
                        + "from (select sic.*, c.Code as CourseCode, c.Name as CourseName "
                        + "from SubjectInClass sic LEFT JOIN Course c "
                        + "ON sic.CourseID = c.ID "
                        + "where c.SemesterID = 11114 and sic.LecturerID = ? and sic.Status = 1) "
                        + "as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    int CourseID = rs.getInt("CourseID");
                    list.add(new ClassInformation(SubjectCode, SubjectID, CourseCode, CourseName, CourseID));
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

    public int getLecturerID(String email) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Select ID"
                        + " from Lecturer"
                        + " where email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int lecID = rs.getInt("ID");
                    result = lecID;
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
        return result;
    }

    public List<StudentProfile> getListStudentByID(int courseID, int subjectID) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select s.ID, s.Code, s.Name, s.Birthday, s.PhoneNumber, s.Gender, s.Address, "
                        + "s.City, e.CourseID, e.SubjectID "
                        + "from Enrollment e LEFT JOIN Student s "
                        + "ON e.StudentID = s.ID "
                        + "where e.CourseID = (select ID "
                        + "from Course "
                        + "where ID = ? and SemesterID = 11114) and e.SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int studentID = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String studentName = rs.getString("Name");
                    String birthday = rs.getString("Birthday");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String gender = rs.getString("Gender");
                    String address = rs.getString("Address");
                    String city = rs.getString("City");
                    list.add(new StudentProfile(studentID, code, studentName, 
                            birthday, phoneNumber, gender, address, city));
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
