package userDAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.Group;
import DTO.StudentProfile;
import DTO.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileStudentDAO {

    public List<StudentProfile> profileStudent(String email) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "			WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Major = rs.getString("Major");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Major, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at profileStudent!!!");
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

    //updateProfile
    public boolean updateProfileStudent(StudentProfile st, UserAccountDTO acc) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Student SET Name= ?, Birthday= ?, PhoneNumber = ?, "
                        + "Gender = ?, Address = ?, City = ? "
                        + "WHERE Email= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, st.getName());
                stm.setString(2, st.getBirthday());
                stm.setString(3, st.getPhoneNumber());
                stm.setString(4, st.getGender());
                stm.setString(5, st.getAddress());
                stm.setString(6, st.getCity());
                stm.setString(7, st.getEmail());
                check = stm.executeUpdate() > 0;
                if (check) {
                    String sql1 = "UPDATE Account SET FullName = ? "
                            + "WHERE Email = ?";
                    stm = conn.prepareStatement(sql1);
                    stm.setString(1, acc.getFullName());
                    stm.setString(2, acc.getEmail());
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at updateProfileStudent");
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

    public List<StudentProfile> getInformationStudent(String email) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Image, Email FROM Student "
                        + "WHERE Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String image = rs.getString("Image");
                    String email1 = rs.getString("Email");
                    list.add(new StudentProfile(email1, image));
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

    public boolean updateImageStudent(String email, String photo) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Student SET Image = ? "
                        + "WHERE Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, photo);
                stm.setString(2, email);
                check = stm.executeUpdate() > 0;
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
        }
        return check;
    }

    public List<ClassInformation> getListClassEnrollment(String email) throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT S.Code, Q.SubjectID, Q.CourseName, Q.CourseCode, Q.CourseID "
                        + "FROM (SELECT distinct a.*, e.StudentID "
                        + "FROM (SELECT s.*, c.Name AS [CourseName], c.Code AS [CourseCode] "
                        + "FROM SubjectInClass s LEFT JOIN Course c "
                        + "ON s.CourseID = c.ID WHERE c.SemesterID = 11114) AS a "
                        + "LEFT JOIN Enrollment e ON a.CourseID = e.CourseID "
                        + "WHERE e.StudentID = (SELECT ID FROM Student WHERE Email = ?)) AS Q "
                        + "LEFT JOIN Subject S ON Q.SubjectID = S.ID";
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

    public boolean checkGroup(String groupName) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM (SELECT g.* "
                        + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                        + "WHERE c.SemesterID = 11114) AS ld "
                        + "WHERE Name = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, groupName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = false;
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
        return check;
    }

    public boolean CreateGroup(int courseID, String Group) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Groupp "
                        + "VALUES('GR',?,?,'Deactive')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Group);
                stm.setInt(2, courseID);
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

    public int checkCourse(int CourseCode) throws SQLException {
        int ID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Course "
                        + "WHERE SemesterID = 11114 AND Code = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, CourseCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ID = rs.getInt("ID");
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
        return ID;
    }

    public List<Group> getListMember(String email, int courseID, int subjectID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*, s.Code AS [StudentCode], s.Name "
                        + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                        + "FROM Groupp g LEFT JOIN Member m "
                        + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11114 AND CourseID = ?) "
                        + "AND m.GroupID IN (SELECT DISTINCT GroupID FROM Member "
                        + "WHERE StudentID = (SELECT ID FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "WHERE Email = ?))) AND m.StudentID IN (SELECT ID FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "WHERE Email = ?)) AND m.SubjectID IN (?)) AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, email);
                stm.setString(3, email);
                stm.setInt(4, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int grID = rs.getInt("GroupID");
                    String sql1 = "SELECT a.*, s.Code AS [StudentCode], s.Name "
                            + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                            + "FROM Groupp g LEFT JOIN Member m "
                            + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                            + "WHERE SemesterID = 11114 AND ID = ?) "
                            + "AND m.GroupID = (SELECT DISTINCT GroupID FROM Member "
                            + "WHERE StudentID = (SELECT ID FROM Student "
                            + "WHERE Email = (SELECT Email FROM Account "
                            + "WHERE Email = ?)) AND GroupID = ?) AND m.SubjectID IN (?)) AS a "
                            + "LEFT JOIN Student s ON a.StudentID = s.ID";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, courseID);
                    stm.setString(2, email);
                    stm.setInt(3, grID);
                    stm.setInt(4, subjectID);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int StuID = rs.getInt("StudentID");
                        int GroupID = rs.getInt("GroupID");
                        String StartDate = rs.getString("StartDate");
                        String Major = rs.getString("Major");
                        String isLeader = rs.getString("isLeader");
                        String StudentCode = rs.getString("StudentCode");
                        String StudentName = rs.getString("Name");
                        String grName = rs.getString("GroupName");
                        int CourseID = rs.getInt("CourseID");
                        int SubID = rs.getInt("SubjectID");
                        count++;
                        list.add(new Group(StudentName, GroupID, grName, StartDate,
                                Major, isLeader, StudentCode, StuID, CourseID, SubID));
                    }
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

    public List<Group> joinGroup(int subID, int courseID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT a.*, s.Code AS [StudentCode], s.Name "
                        + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                        + "FROM Groupp g LEFT JOIN Member m "
                        + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11114 AND CourseID = ?) "
                        + "AND m.SubjectID IN (?)) AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID WHERE a.isLeader = 'LD'";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int StuID = rs.getInt("StudentID");
                    int GroupID = rs.getInt("GroupID");
                    String StartDate = rs.getString("StartDate");
                    String Major = rs.getString("Major");
                    String isLeader = rs.getString("isLeader");
                    String StudentCode = rs.getString("StudentCode");
                    String StudentName = rs.getString("Name");
                    String grName = rs.getString("GroupName");
                    int CourseID = rs.getInt("CourseID");
                    int SubID = rs.getInt("SubjectID");
                    list.add(new Group(StudentName, GroupID, grName, StartDate,
                            Major, isLeader, StudentCode, StuID, CourseID, SubID));
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

    public boolean GroupEnrollment(int subID, int grID, int StudentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql1 = "INSERT INTO Member "
                        + "VALUES(?,?,GETDATE(),'SE','MB',?)";
                stm = conn.prepareStatement(sql1);
                stm.setInt(1, StudentID);
                stm.setInt(2, grID);
                stm.setInt(3, subID);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
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

    public int findStudentID(String email) throws SQLException {
        int ID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ID = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        return ID;
    }
}
