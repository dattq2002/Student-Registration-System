package user.DAO;

import DBUtil.Util;
import DTO.ClassInformation;
import DTO.Group;
import DTO.GroupProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupStudentDAO {
    public boolean UpdateProject(GroupProject gp) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "UPDATE Project SET Context = ?, Actors = ?, "
                        + "[Func-Requirement] = ?, Note = ? "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gp.getContext());
                stm.setString(2, gp.getActors());
                stm.setString(3, gp.getFunc());
                stm.setString(4, gp.getNote());
                stm.setInt(5, gp.getProjectID());
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
    
    public boolean checkGroup(String groupName, int stuID, int subID) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql1 = "SELECT A.* FROM (SELECT m.*,g.CourseID FROM "
                        + "Member m LEFT JOIN Groupp g ON m.GroupID = g.ID) AS "
                        + "A LEFT JOIN Course C ON A.CourseID = C.ID WHERE "
                        + "C.SemesterID = 11114 AND A.StudentID = ? AND "
                        + "A.SubjectID = ?";
                stm = conn.prepareStatement(sql1);
                stm.setInt(1, stuID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                if (!rs.next()) {
                    String sql = "SELECT * FROM (SELECT g.* "
                            + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                            + "WHERE c.SemesterID = 11114 AND g.Status IN ('True','False') ) AS ld "
                            + "WHERE Name = ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, groupName);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        check = false;
                    }
                } else {
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
    
    public boolean CreateGroup(int courseID, String GroupName, int stuID, int sub) throws SQLException {
        boolean check = false;
        boolean check1 = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Groupp "
                        + "VALUES('GR',?,?,'False')";
                stm = conn.prepareStatement(sql);
                stm.setString(1, GroupName);
                stm.setInt(2, courseID);
                check1 = stm.executeUpdate() > 0;
                if (check1) {
                    String sql1 = "SELECT * FROM (SELECT g.* "
                            + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                            + "WHERE c.SemesterID = 11114 AND g.Status IN ('False') ) AS ld "
                            + "WHERE Name = ? AND CourseID = ?";
                    stm = conn.prepareStatement(sql1);
                    stm.setString(1, GroupName);
                    stm.setInt(2, courseID);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        int grID = rs.getInt("ID");
                        String sql2 = "INSERT INTO Member "
                                + "VALUES (?,?,GETDATE(),'SE','LD',?)";
                        stm = conn.prepareStatement(sql2);
                        stm.setInt(1, stuID);
                        stm.setInt(2, grID);
                        stm.setInt(3, sub);
                        check = stm.executeUpdate() > 0;
                    }
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
        return check;
    }
    
    public boolean InsertGroupProject(GroupProject gp) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM (SELECT g.* "
                        + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                        + "WHERE c.SemesterID = 11114 AND g.Status IN ('False') ) AS ld "
                        + "WHERE Name = ? AND CourseID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gp.getGroupName());
                stm.setInt(2, gp.getCourseID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    int grID = rs.getInt("ID");
                    String sql1 = "INSERT INTO Project "
                            + "VALUES('PRJ',?,?,?,?,?,GETDATE(),?)";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, grID);
                    stm.setInt(2, gp.getTopicID());
                    stm.setString(3, gp.getContext());
                    stm.setString(4, gp.getActors());
                    stm.setString(5, gp.getFunc());
                    stm.setString(6, gp.getNote());
                    check = stm.executeUpdate() > 0;
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
    
    public List<Group> GetListGroupEnRolled(String email) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT EQ.*, S.Code "
                        + "FROM (SELECT B.*, S.Name, S.Code AS [StudentCode] "
                        + "FROM (SELECT A.*, G.Name AS [GroupName], G.CourseID "
                        + "FROM (SELECT * FROM Member "
                        + "WHERE StudentID = (SELECT ID FROM Student "
                        + "WHERE Email = ?)) AS A "
                        + "LEFT JOIN Groupp G ON A.GroupID = G.ID WHERE G.Status = 'True') AS B "
                        + "LEFT JOIN Student S ON B.StudentID = S.ID) AS EQ "
                        + "LEFT JOIN Subject S ON EQ.SubjectID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
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
                    String subCode = rs.getString("Code");
                    list.add(new Group(StudentName, GroupID, grName, StartDate,
                            Major, isLeader, StudentCode, StuID, CourseID, SubID, subCode));
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
    
    public List<GroupProject> listProjectGroup(String email, int courseID, int subjectID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
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
                        + "AND m.GroupID IN (SELECT DISTINCT GroupID FROM Member "
                        + "WHERE StudentID = (SELECT ID FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "WHERE Email = ?))) AND m.StudentID IN (SELECT ID FROM Student "
                        + "WHERE Email = (SELECT Email FROM Account "
                        + "WHERE Email = ?)) AND m.SubjectID IN (?) AND g.Status = 'True') AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, email);
                stm.setString(3, email);
                stm.setInt(4, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int grID = rs.getInt("GroupID");
                    String sql1 = "select p.*, t.Name as TopicName "
                            + "from Project p LEFT JOIN Topic t "
                            + "ON p.TopicID = t.ID "
                            + "where p.GroupID = ?";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, grID);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int projectID = rs.getInt("ID");
                        int topicID = rs.getInt("TopicID");
                        String topicName = rs.getString("TopicName");
                        String context = rs.getString("Context");
                        String actors = rs.getString("Actors");
                        String function = rs.getString("Func-Requirement");
                        String note = rs.getString("Note");
                        list.add(new GroupProject(context, actors, function,
                                note, projectID, topicID, topicName));
                    }
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
        return list;
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
                        + "WHERE Email = ?)) AND m.SubjectID IN (?) AND g.Status = 'True') AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, email);
                stm.setString(3, email);
                stm.setInt(4, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int grID = rs.getInt("GroupID");
                    String sql1 = "SELECT QW.*, S.Code "
                            + "FROM (SELECT a.*, s.Code AS [StudentCode], s.Name "
                            + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                            + "FROM Groupp g LEFT JOIN Member m "
                            + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                            + "WHERE SemesterID = 11114 AND ID = ?) "
                            + "AND m.GroupID = (SELECT DISTINCT GroupID FROM Member "
                            + "WHERE StudentID = (SELECT ID FROM Student "
                            + "WHERE Email = (SELECT Email FROM Account "
                            + "WHERE Email = ?)) AND GroupID = ?) "
                            + "AND m.SubjectID IN (?) AND g.Status = 'True') AS a "
                            + "LEFT JOIN Student s ON a.StudentID = s.ID) AS QW "
                            + "LEFT JOIN Subject S ON QW.SubjectID = S.ID";
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
                        String subCode = rs.getString("Code");
                        count++;
                        list.add(new Group(StudentName, GroupID, grName, StartDate,
                                Major, isLeader, StudentCode, StuID, CourseID, SubID, subCode));
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
    
    public List<Group> joinGroup(int subID, int courseID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, T.Code FROM (SELECT A.*, P.TopicID FROM (SELECT WQ.*, S.Code as SubjectCode "
                        + "FROM (SELECT a.*, s.Code AS [StudentCode], s.Name "
                        + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                        + "FROM Groupp g LEFT JOIN Member m "
                        + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11114 AND CourseID = ?) \n"
                        + "AND m.SubjectID IN (?) AND g.Status = 'True') AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID WHERE a.isLeader IN ('LD',NULL)) AS WQ "
                        + "LEFT JOIN Subject S ON WQ.SubjectID = S.ID) AS A LEFT JOIN Project P "
                        + "ON A.GroupID = P.GroupID) AS B LEFT JOIN Topic T "
                        + "ON B.TopicID = T.ID";
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
                    String subCode = rs.getString("SubjectCode");
                    int topicID = rs.getInt("TopicID");
                    String topicCode = rs.getString("Code");
                    list.add(new Group(StudentName, GroupID, grName, StartDate,
                            Major, isLeader, StudentCode, StuID, CourseID, 
                            SubID, subCode, topicID, topicCode));
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
    
    public boolean OutGroupMember(int subID, String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "DELETE Member WHERE SubjectID = ? "
                        + "AND StudentID = (SELECT ID FROM Student "
                        + "WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subID);
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
    
    public List<Group> getListMember(int courseID, int subjectID, int grID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT QW.*, S.Code "
                        + "FROM (SELECT a.*, s.Code AS [StudentCode], s.Name "
                        + "FROM (SELECT m.*, g.Name AS [GroupName], g.CourseID "
                        + "FROM Groupp g LEFT JOIN Member m "
                        + "ON g.ID = m.GroupID WHERE g.CourseID IN (SELECT ID FROM Course "
                        + "WHERE SemesterID = 11114 AND ID = ?) "
                        + "AND m.GroupID = ? "
                        + "AND m.SubjectID IN (?) AND g.Status = 'True') AS a "
                        + "LEFT JOIN Student s ON a.StudentID = s.ID) AS QW "
                        + "LEFT JOIN Subject S ON QW.SubjectID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, grID);
                stm.setInt(3, subjectID);
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
                    String subCode = rs.getString("Code");
                    list.add(new Group(StudentName, GroupID, grName, StartDate,
                            Major, isLeader, StudentCode, StuID, CourseID, SubID, subCode));
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
        return list;
    }
}
