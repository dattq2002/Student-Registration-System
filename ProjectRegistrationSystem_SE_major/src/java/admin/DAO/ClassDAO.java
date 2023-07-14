package admin.DAO;

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
import system.main.DTO.StudentProfile;

public class ClassDAO {

    //getListClass in semester
    public List<Class> getListClass(int SemesterID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Class> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Course "
                        + "WHERE SemesterID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SemesterID);
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
    public List<StudentProfile> getListStudentInClass(int courseID, int subID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<StudentProfile> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student WHERE ID IN (SELECT StudentID "
                        + "FROM Enrollment "
                        + "WHERE CourseID = ? AND SubjectID = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
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

    //getDataFromFile
    public boolean getDataFromFile(double col2, double col3, String col4, double col5, String col6) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Enrollment(StudentID, CourseID, StartDate,SubjectID, Note) "
                        + "VALUES(?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setDouble(1, col2);
                stm.setDouble(2, col3);
                stm.setString(3, col4);
                stm.setDouble(4, col5);
                stm.setString(5, col6);
                check = stm.executeUpdate() > 0;
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
        }
        return check;
    }

    public boolean DeleteStudentClass(int courseID, int subID, int StuID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "DELETE Enrollment "
                        + "WHERE StudentID = ? AND CourseID = ? AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, StuID);
                stm.setInt(2, courseID);
                stm.setInt(3, subID);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
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
    
    public int getIDSemester(String Name, int year) throws SQLException {
        int sesID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Semester "
                        + "WHERE Name = ? AND Year = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Name);
                stm.setInt(2, year);
                rs = stm.executeQuery();
                if (rs.next()) {
                    sesID = rs.getInt("ID");
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
        return sesID;
    }
    
    public boolean checkCourse(int courseID) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Course WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = false;
                }
            }
         } catch (ClassNotFoundException | SQLException e) {
        }finally {
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
        return check;
    }
    
    public boolean InsertCourse(Class cl) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Course "
                        + "VALUES (?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, cl.getCourseID());
                stm.setString(2, cl.getCourseName());
                stm.setInt(3, cl.getCourseCode());
                stm.setInt(4, cl.getSemesterID());
                stm.setString(5, cl.getStartDate());
                stm.setString(6, cl.getEndDate());
                check = stm.executeUpdate() > 0;
            }
         } catch (ClassNotFoundException | SQLException e) {
        }finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }
    
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

}
