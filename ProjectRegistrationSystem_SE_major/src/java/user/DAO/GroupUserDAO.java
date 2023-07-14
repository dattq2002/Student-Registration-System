package user.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Group;
import system.main.DTO.GroupProject;

public class GroupUserDAO {

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

    public List<GroupProject> joinGroup(int subID, int courseID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
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
                    list.add(new GroupProject(StudentName, GroupID, grName, StartDate,
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

    public boolean GroupEnrollment(int subID, int grID, int StudentID, int presentGr) throws SQLException {
        boolean check = false;
        boolean check1 = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "DELETE Member WHERE StudentID = ? AND GroupID = ? "
                        + "AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, StudentID);
                stm.setInt(2, presentGr);
                stm.setInt(3, subID);
                check1 = stm.executeUpdate() > 0;
                if (check1) {
                    String sql1 = "INSERT INTO Member "
                            + "VALUES(?,?,GETDATE(),'SE','MB',?)";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, StudentID);
                    stm.setInt(2, grID);
                    stm.setInt(3, subID);
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
        }
        return check;
    }

    public boolean checkGrCode(String gCode, int sCode) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Groupp "
                        + "WHERE ID = ? AND Code = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, sCode);
                stm.setString(2, gCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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

    public int getGrID(String grName, int courseID) throws SQLException {
        int grID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Groupp WHERE CourseID = ? "
                        + "AND Name = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, grName);
                rs = stm.executeQuery();
                if (rs.next()) {
                    grID = rs.getInt("ID");
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
        return grID;
    }
}
