package adminDAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.Semester;
import DTO.StudentProfile;
import DTO.Subject;
import DTO.TopicAssign;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, c.Code, b.CourseID, "
                        + "c.StartDate, c.EndDate,c.SemesterID, b.Status FROM (SELECT a.*, "
                        + "lec.Name AS [LecName] FROM (SELECT s.*, "
                        + "su.Code as [SubjectCode] "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec "
                        + "ON a.LecturerID = lec.ID) AS b "
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
                    int semeID = rs.getInt("SemesterID");
                    int CourseCode = rs.getInt("Code");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID,CourseCode, start, end, status, semeID));
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
    
    //getListClassSP2022
    public List<ClassInformation> getListClassSP2022() throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, c.Code, b.CourseID,"
                        + "c.StartDate, c.EndDate,c.SemesterID, b.Status FROM (SELECT a.*, "
                        + "lec.Name AS [LecName] FROM (SELECT s.*, "
                        + "su.Code as [SubjectCode] "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec "
                        + "ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID WHERE SemesterID = 11111";
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
                    int semeID = rs.getInt("SemesterID");
                    int CourseCode = rs.getInt("Code");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID,CourseCode, start, end, status, semeID));
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

    //getListClassFA2022
    public List<ClassInformation> getListClassFA2022() throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, c.Code, b.CourseID,"
                        + "c.StartDate, c.EndDate,c.SemesterID, b.Status FROM (SELECT a.*, "
                        + "lec.Name AS [LecName] FROM (SELECT s.*, "
                        + "su.Code as [SubjectCode] "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec "
                        + "ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID WHERE SemesterID = 11112";
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
                    int semeID = rs.getInt("SemesterID");
                    int CourseCode = rs.getInt("Code");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID,CourseCode, start, end, status, semeID));
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
    
    //getListClassSP2023
    public List<ClassInformation> getListClassSP2023() throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, c.Code, b.CourseID,"
                        + "c.StartDate, c.EndDate,c.SemesterID, b.Status FROM (SELECT a.*, "
                        + "lec.Name AS [LecName] FROM (SELECT s.*, "
                        + "su.Code as [SubjectCode] "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec "
                        + "ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID WHERE SemesterID = 11113";
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
                    int semeID = rs.getInt("SemesterID");
                    int CourseCode = rs.getInt("Code");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            Coursecode, CourseID,CourseCode, start, end, status, semeID));
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
                String sql = "SELECT b.ID, b.SubjectCode, b.SubjectID, b.LecName, c.Name, b.Code, b.CourseID, "
                        + "c.StartDate, c.EndDate,c.SemesterID, b.Status FROM (SELECT a.*, "
                        + "lec.Name AS [LecName] FROM (SELECT s.*, "
                        + "su.Code as [SubjectCode] "
                        + "FROM SubjectInClass s LEFT JOIN Subject su "
                        + "ON s.SubjectID = su.ID) AS a LEFT JOIN Lecturer lec "
                        + "ON a.LecturerID = lec.ID) AS b "
                        + "LEFT JOIN Course c ON b.CourseID = c.ID WHERE b.SubjectCode LIKE ? ";
                stm = conn.prepareStatement(sql);
                String tmp = search.substring(0, 3);
                stm.setString(1, "%" + tmp + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    String subCode = rs.getString("SubjectCode");
                    int subID = rs.getInt("SubjectID");
                    String lecName = rs.getString("LecName");
                    String CourseName = rs.getString("Name");
                    String start = rs.getString("StartDate");
                    String end = rs.getString("EndDate");
                    boolean status = rs.getBoolean("Status");
                    int CourseID = rs.getInt("CourseID");
                    int semeID = rs.getInt("SemesterID");
                    int CourseCode = rs.getInt("Code");
                    list.add(new ClassInformation(ID, subCode, subID, lecName,
                            CourseName, CourseID,CourseCode, start, end, status, semeID));
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

    //UpdateClass
    public boolean DeleteClass(int id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE SubjectInClass SET Status = ? "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setInt(2, id);
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

    //getListTopicAssign
    public List<TopicAssign> getListTopicAssign() throws SQLException {
        List<TopicAssign> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT a.TopicAssignID, a.TopicCode, a.TopicID, "
                        + "a.SubjectCode, a.SubjectID, a.StartDate, a.ModifyDate, "
                        + "s.Name, s.Year, a.Status "
                        + "FROM (SELECT  a.TopicAssignID, a.TopicCode, a.TopicID, "
                        + "s.Code as [SubjectCode], a.SubjectID, a.StartDate, a.ModifyDate, "
                        + "a.SemesterID, a.Status "
                        + "FROM (SELECT t.ID as [TopicAssignID], a.Code as [TopicCode], "
                        + "t.TopicID, t.SubjectID,t.StartDate,t.ModifyDate,"
                        + "t.SemesterID, t.Status "
                        + "FROM TopicAssign t LEFT JOIN Topic a "
                        + "ON t.TopicID = a.ID) as a "
                        + "LEFT JOIN Subject s ON a.SubjectID = s.ID) as a "
                        + "LEFT JOIN Semester s ON a.SemesterID = s.ID "
                        + "WHERE a.Status = 0";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int TopicAss = rs.getInt("TopicAssignID");
                    String TopicCode = rs.getString("TopicCode").trim();
                    int TopicID = rs.getInt("TopicID");
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    String stDate = rs.getString("StartDate");
                    String mdDate = rs.getString("ModifyDate");
                    String SName = rs.getString("Name");
                    int year = rs.getInt("Year");
                    String Semester = SName + year;
                    boolean Status = rs.getBoolean("Status");
                    list.add(new TopicAssign(TopicID, TopicCode, TopicAss,
                            SubjectCode, SubjectID, stDate, mdDate, Semester, Status));
                }
            }
        } catch (Exception e) {
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

    //approveTopic
    public boolean approveTopic(int topicID, int SubjectID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE TopicAssign SET Status = ? "
                        + "WHERE TopicID = ? AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, true);
                stm.setInt(2, topicID);
                stm.setInt(3, SubjectID);
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

    //----------check----------
    public boolean checkSemester(int semester) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Semester "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, semester);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        return check;
    }
    
    public boolean checkCourse(int id, String name, int semester, 
            String stDate, String endDate) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Course "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }
            }
        } catch (Exception e) {
        }finally {
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
        return check;
    }
    
    public boolean CreateNewCourse(int id, String name,String code, int semester, 
            String stDate, String endDate) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql2 = "INSERT INTO Course "
                            + "VALUES(?,?,?,?,?,?)";
                    stm = conn.prepareStatement(sql2);
                    stm.setInt(1, id);
                    stm.setString(2, name);
                    stm.setString(3, code);
                    stm.setInt(4, semester);
                    stm.setString(4, stDate);
                    stm.setString(5, endDate);
                    check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public int checkLectureName (String name) throws SQLException{
        int id = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Lecturer "
                        + "WHERE Name = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if(rs.next()){
                    id = rs.getInt("ID");
                }
            }
        } catch (Exception e) {
        }finally {
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
        return id;
    }
    
    public boolean checkSubject(int id, String code, String name, int credit) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Subject "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    check = true;
                }else{
                    String sql2 = "INSERT INTO Subject "
                            + "VALUES(?,?,?,?,?)";
                    stm = conn.prepareStatement(sql2);
                    stm.setInt(1, id);
                    stm.setString(2, code);
                    stm.setString(2, name);
                    stm.setString(3, null);
                    stm.setInt(4, credit);
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
        return check;
    }
    
    public boolean createNewSubject(int id, String code, String name, int credit) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn= Util.getConnection();
            if(conn != null){
                String sql2 = "INSERT INTO Subject "
                            + "VALUES(?,?,?,?,?)";
                    stm = conn.prepareStatement(sql2);
                    stm.setInt(1, id);
                    stm.setString(2, code);
                    stm.setString(2, name);
                    stm.setString(3, null);
                    stm.setInt(4, credit);
                    check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        }finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    //-------------------------
    //CreateCourseSubjectInClass
    public boolean CreateCourse(int lecID, int subID, int CourseID) throws SQLException{
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "INSERT INTO SubjectInClass(LecturerID, SubjectID, "
                        + "CourseID, Status) VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                stm.setInt(2, subID);
                stm.setInt(3, CourseID);
                stm.setBoolean(4, false);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    //UpdateSubjectInClass
    public boolean UpdateSubjectInClass(int id,int subID, int CourseID, int lecID, boolean status) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "UPDATE SubjectInClass SET LecturerID = ?,"
                        + "SubjectID = ?, CourseID = ?, Status = ? "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                stm.setInt(2, subID);
                stm.setInt(3, CourseID);
                stm.setBoolean(4, status);
                stm.setInt(5, id);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    //UpdateCourse
    public boolean UpdateCourse(int CourseID,String stDate, String endDate) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "UPDATE Course SET StartDate = ?, "
                        + "EndDate = ? "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, stDate);
                stm.setString(2, endDate);
                stm.setInt(3, CourseID);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }finally{
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    //getDataFromFile
    public boolean getDataFromFile(double col2, double col3, String col4, String col5) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Enrollment(StudentID, CourseID, StartDate, Note) "
                        + "VALUES(?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setDouble(1, col2);
                stm.setDouble(2, col3);
                stm.setString(3, col4);
                stm.setString(4, col5);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            if(conn != null) conn.close();
            if(stm != null) stm.close();
        }
        return check;
    }
    //List Semester
    public List<Semester> getSemester() throws SQLException{
        List<Semester> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn= Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Semester "
                        + "WHERE Status = 'Unavailable'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int ID = rs.getInt("ID");
                    int year = rs.getInt("Year");
                    String name = rs.getString("Name");
                    String status = rs.getString("Status");
                    String stDate = rs.getString("StartDate");
                    String eDate = rs.getString("EndDate");
                    list.add(new Semester(ID, year, name, status, stDate, eDate));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
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
