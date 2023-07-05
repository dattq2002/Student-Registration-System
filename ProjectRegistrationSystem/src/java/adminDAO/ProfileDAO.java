package adminDAO;

import DBUtil.Util;
import DTO.Group;
import DTO.LectureProfile;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {

    //-----------------SEARCH------------------------
    //search student profile
    public List<StudentProfile> SearchStudent(String name) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Student "
                        + "WHERE Name LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
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
                    list.add(new StudentProfile(id, code, Name, Birthday, PhoneNum, Gender, Address, City, Major, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListStudent!!");
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

    //showListStudent
    public List<StudentProfile> getListStudent() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student "
                        + "WHERE ID IN (SELECT StudentID FROM Enrollment "
                        + "WHERE CourseID IN (SELECT ID FROM Course WHERE SemesterID = 11114))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
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
            System.err.println("Err at showListStudent!");
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

    //getListStudentSpring2022
    public List<StudentProfile> getListStudentSpring2022() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student WHERE ID in (SELECT StudentID FROM Enrollment "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11111))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
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
            System.err.println("Err at showListStudent!");
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

    //getListStudentFall2022
    public List<StudentProfile> getListStudentFall2022() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student WHERE ID in (SELECT StudentID FROM Enrollment "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11112))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
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
            System.err.println("Err at showListStudent!");
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

    //getListStudentSpring2023
    public List<StudentProfile> getListStudentSpring2023() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student WHERE ID in (SELECT StudentID FROM Enrollment "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11113))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
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
            System.err.println("Err at showListStudent!");
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

    //search Lecture profile
    public List<LectureProfile> getListLecture(String name) throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Lecturer "
                        + "WHERE Name LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListStudent!!");
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

    //showListLecture
    public List<LectureProfile> getListLecture() throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Lecturer "
                        + "WHERE ID IN (SELECT LecturerID FROM SubjectInClass "
                        + "WHERE CourseID IN (SELECT ID FROM Course WHERE SemesterID = 11114))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListLecture!");
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

    //getListLectureSP2022
    public List<LectureProfile> getListLectureSP2022() throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Lecturer WHERE ID in "
                        + "(SELECT DISTINCT LecturerID FROM SubjectInClass "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11111)) ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListLecture!");
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

    //getListLectureSP2022
    public List<LectureProfile> getListLectureFA2022() throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Lecturer WHERE ID in "
                        + "(SELECT DISTINCT LecturerID FROM SubjectInClass "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11112)) ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListLecture!");
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

    //getListLectureSP2023
    public List<LectureProfile> getListLectureSP2023() throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Lecturer WHERE ID in "
                        + "(SELECT DISTINCT LecturerID FROM SubjectInClass "
                        + "WHERE CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11113)) ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday,
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListLecture!");
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

    //-------------------------------------------------------
    //update Profile
    public boolean UpdateProfile(int id, String code, String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                if (code.equals("LT")) {
                    String sql = "UPDATE Lecturer SET Name =? "
                            + "WHERE ID =?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                    String sql4 = "SELECT Email FROM Lecturer "
                            + "WHERE ID = ?";
                    stm = conn.prepareStatement(sql4);
                    stm.setInt(1, id);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        String sql5 = "UPDATE Account SET FullName = ? "
                                + "WHERE Email = (SELECT Email FROM Lecturer "
                                + "WHERE ID = ?)";
                        stm = conn.prepareStatement(sql5);
                        stm.setString(1, name);
                        stm.setInt(2, id);
                        check = stm.executeUpdate() > 0;
                    }
                } else {
                    String sql = "UPDATE Student SET Name =? "
                            + "WHERE ID =?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                    String sql2 = "SELECT Email FROM Student WHERE ID = ?";
                    stm = conn.prepareStatement(sql2);
                    stm.setInt(1, id);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        String sql3 = "UPDATE Account SET FullName = ? "
                                + "WHERE Email = (SELECT Email FROM Student "
                                + "WHERE ID = ?)";
                        stm = conn.prepareStatement(sql3);
                        stm.setString(1, name);
                        stm.setInt(2, id);
                        check = stm.executeUpdate() > 0;
                    }

                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at UpdateStudent!!");
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
        return check;
    }

    //add Profile Lecturer
    public boolean AddLectureProfile(LectureProfile lec) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Lecturer(ID, Code, Name, Birthday,"
                        + "PhoneNumber, Gender, Address, City, Email) "
                        + "VALUES(?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lec.getID());
                stm.setString(2, lec.getCode());
                stm.setString(3, lec.getName());
                stm.setString(4, lec.getBirthday());
                stm.setString(5, lec.getPhoneNumber());
                stm.setString(6, lec.getGender());
                stm.setString(7, lec.getAddress());
                stm.setString(8, lec.getCity());
                stm.setString(9, lec.getEmail());
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

    //AddStudentProfile
    public boolean AddStudentProfile(StudentProfile stu) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Student(ID, Code, Name, Birthday, "
                        + "PhoneNumber, Gender, Address, City, Major, Email) "
                        + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, stu.getID());
                stm.setString(2, stu.getCode());
                stm.setString(3, stu.getName());
                stm.setString(4, stu.getBirthday());
                stm.setString(5, stu.getPhoneNumber());
                stm.setString(6, stu.getGender());
                stm.setString(7, stu.getAddress());
                stm.setString(8, stu.getCity());
                stm.setString(9, stu.getMajor());
                stm.setString(10, stu.getEmail());
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

    public boolean AddLectureToClass(int lecID, int subID, int CourseID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO SubjectInClass "
                        + "VALUES(?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                stm.setInt(2, subID);
                stm.setInt(3, CourseID);
                stm.setBoolean(4, status);
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

    //check Email
    public boolean checkEmailExistInAccount(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Account "
                        + "WHERE Email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    //check duplicate StudentID
    public boolean checkStudentID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = true;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Student "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = false;
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
        return check;
    }

    //check duplicate LectureID
    public boolean checkLectureID(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = true;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Lecturer "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = false;
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
        return check;
    }
}
