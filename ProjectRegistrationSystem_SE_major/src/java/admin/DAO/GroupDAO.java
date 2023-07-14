package admin.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Group;
import system.main.DTO.GroupProject;

public class GroupDAO {

    public List<GroupProject> getListGroup(int courseID, int subID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, T.Code AS TopicCode "
                        + "FROM (SELECT A.*, P.TopicID "
                        + "FROM (SELECT DISTINCT G.*, M.SubjectID "
                        + "FROM Groupp G LEFT JOIN Member M "
                        + "ON G.ID = M.GroupID "
                        + "WHERE G.CourseID = ? AND M.SubjectID = ?) AS A LEFT JOIN Project P "
                        + "ON A.ID = P.GroupID) AS B LEFT JOIN Topic T "
                        + "ON B.TopicID = T.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int grID = rs.getInt("ID");
                    String grCode = rs.getString("Code");
                    String grName = rs.getString("Name");
                    String TopicCode = rs.getString("TopicCode");
                    int TopicID = rs.getInt("TopicID");
                    list.add(new GroupProject(TopicID, TopicCode, grID, grName,
                            courseID, grCode));
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

    public List<Group> getListGroupDetail(int grID, int subID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, S.Code AS StudentCode, S.Name AS StudentName "
                        + "FROM (SELECT * FROM Member "
                        + "WHERE GroupID = ? AND SubjectID = ?) AS A LEFT JOIN Student S ON A.StudentID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int memID = rs.getInt("ID");
                    int StudentID = rs.getInt("StudentID");
                    String StartDate = rs.getString("StartDate");
                    String isLeader = rs.getString("isLeader");
                    String StudentCode = rs.getString("StudentCode");
                    String StudentName = rs.getString("StudentName");
                    list.add(new Group(StudentID, StudentCode, StudentName,
                            memID, StartDate, isLeader));
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

    public List<GroupProject> getListProject(int grID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, T.Name AS TopicName "
                        + "FROM (SELECT * FROM Project WHERE GroupID = ?) AS "
                        + "A LEFT JOIN Topic T ON A.TopicID = T.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int projectID = rs.getInt("ID");
                    int topicID = rs.getInt("TopicID");
                    String topicName = rs.getString("TopicName");
                    String context = rs.getString("Context");
                    String actors = rs.getString("Actors");
                    String function = rs.getString("Func-Requirement");
                    String note = rs.getString("Note");
                    list.add(new GroupProject(projectID, context, actors, function,
                            note, topicID, grID, topicName));
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
    
    public List<GroupProject> getListGroupMNG(int courseID, int subID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, T.Code AS TopicCode "
                        + "FROM (SELECT A.*, P.TopicID "
                        + "FROM (SELECT DISTINCT G.*, M.SubjectID "
                        + "FROM Groupp G LEFT JOIN Member M "
                        + "ON G.ID = M.GroupID "
                        + "WHERE G.CourseID = ? AND M.SubjectID = ?) AS A LEFT JOIN Project P "
                        + "ON A.ID = P.GroupID) AS B LEFT JOIN Topic T "
                        + "ON B.TopicID = T.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subID);
//                stm.setString(3, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int grID = rs.getInt("ID");
                    String grCode = rs.getString("Code");
                    String grName = rs.getString("Name");
                    String TopicCode = rs.getString("TopicCode");
                    int TopicID = rs.getInt("TopicID");
                    list.add(new GroupProject(TopicID, TopicCode, grID, grName,
                            courseID, grCode));
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

    public List<Group> getListGroupDetailMNG(int grID, int subID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, S.Code AS StudentCode, S.Name AS StudentName "
                        + "FROM (SELECT * FROM Member "
                        + "WHERE GroupID = ? AND SubjectID = ?) AS A LEFT JOIN Student S ON A.StudentID = S.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int memID = rs.getInt("ID");
                    int StudentID = rs.getInt("StudentID");
                    String StartDate = rs.getString("StartDate");
                    String isLeader = rs.getString("isLeader");
                    String StudentCode = rs.getString("StudentCode");
                    String StudentName = rs.getString("StudentName");
                    list.add(new Group(StudentID, StudentCode, StudentName,
                            memID, StartDate, isLeader));
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

    public List<GroupProject> getListProjectMNG(int grID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, T.Code AS TopicCode ,T.Name AS TopicName "
                        + "FROM (SELECT * FROM Project WHERE GroupID = ?) AS "
                        + "A LEFT JOIN Topic T ON A.TopicID = T.ID";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int projectID = rs.getInt("ID");
                    int topicID = rs.getInt("TopicID");
                    String topicCode = rs.getString("TopicCode");
                    String topicName = rs.getString("TopicName");
                    String context = rs.getString("Context");
                    String actors = rs.getString("Actors");
                    String function = rs.getString("Func-Requirement");
                    String note = rs.getString("Note");
                    list.add(new GroupProject(projectID, context, topicCode, actors, function, note, topicID, grID, topicName));
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

    public boolean updateProjectTopic(int topicID, int projectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update Project"
                        + " set TopicID= ?"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setInt(2, projectID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
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

    public boolean checkTopicID(int subjectID, int semesterID, int topicID, String topicCode) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, L.Name AS LecName "
                        + "FROM (SELECT A.ID AS TopicAssign, A.TopicID, A.SubjectID, A.StartDate, "
                        + "A.ModifyDate, A.SemesterID, A.Status, T.* "
                        + "FROM (SELECT * FROM TopicAssign WHERE SubjectID = ? AND SemesterID = ?) AS A "
                        + "LEFT JOIN Topic T ON A.TopicID = T.ID) AS B LEFT JOIN Lecturer L "
                        + "ON B.LecturerID = L.ID "
                        + "WHERE B.TopicID = ? AND B.Code = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subjectID);
                stm.setInt(2, semesterID);
                stm.setInt(3, topicID);
                stm.setString(4, topicCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean updateGroupMember(String isLeader, int memberID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update Member"
                        + " set isLeader= ?"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, isLeader);
                stm.setInt(2, memberID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
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

    public boolean deleteGroupMember(int memberID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Delete Member"
                        + " where ID=? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, memberID);
                int value = stm.executeUpdate();
                result = value > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean deleteGroup(int groupID, int subjectID, int courseID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "DELETE Member WHERE GroupID = ? AND SubjectID = ? "
                        + "DELETE Project WHERE GroupID = ? "
                        + "DELETE Groupp WHERE ID = ? AND CourseID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
                stm.setInt(2, subjectID);
                stm.setInt(3, groupID);
                stm.setInt(4, groupID);
                stm.setInt(5, courseID);
                int value = stm.executeUpdate();
                result = value > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean checkGroupName(String name, int courseID, int subjectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Groupp G LEFT JOIN MEMBER M "
                        + "ON G.ID = M.GroupID "
                        + "WHERE G.CourseID = ? AND G.Name = ? AND M.SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, name);
                stm.setInt(3, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean insertGroup(String gName, int courseID, int studentID,
            int subjectID, int semesterID) throws SQLException {
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
                stm.setString(1, gName);
                stm.setInt(2, courseID);
                check1 = stm.executeUpdate() > 0;
                if (check1) {
                    String sql1 = "SELECT * FROM (SELECT g.* "
                            + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                            + "WHERE c.SemesterID = ?) AS ld "
                            + "WHERE Name = ? AND CourseID = ?";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, semesterID);
                    stm.setString(2, gName);
                    stm.setInt(3, courseID);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        int grID = rs.getInt("ID");
                        String sql2 = "INSERT INTO Member "
                                + "VALUES (?,?,GETDATE(),'SE','LD',?)";
                        stm = conn.prepareStatement(sql2);
                        stm.setInt(1, studentID);
                        stm.setInt(2, grID);
                        stm.setInt(3, subjectID);
                        check = stm.executeUpdate() > 0;
                    }
                }
            }
        } catch (Exception e) {
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

    public boolean insertProject(String gName, int courseID, int semesterID, int topicID, String context,
            String actors, String funcR, String note) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM (SELECT g.* "
                        + "FROM Groupp g LEFT JOIN Course c ON g.CourseID = c.ID "
                        + "WHERE c.SemesterID = ? ) AS ld "
                        + "WHERE Name = ? AND CourseID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, semesterID);
                stm.setString(2, gName);
                stm.setInt(3, courseID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int grID = rs.getInt("ID");
                    String sql1 = "INSERT INTO Project "
                            + "VALUES('PRJ',?,?,?,?,?,GETDATE(),?)";
                    stm = conn.prepareStatement(sql1);
                    stm.setInt(1, grID);
                    stm.setInt(2, topicID);
                    stm.setString(3, context);
                    stm.setString(4, actors);
                    stm.setString(5, funcR);
                    stm.setString(6, note);
                    check = stm.executeUpdate() > 0;
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
        return check;
    }

    public boolean checkStudentID(int courseID, int subjectID, int studentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Enrollment "
                        + "WHERE CourseID = ? AND SubjectID = ? AND StudentID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subjectID);
                stm.setInt(3, studentID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean checkDuplicateTopicID(int topicID, int courseID, int subjectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT A.*, M.SubjectID "
                        + "FROM (SELECT P.*, G.CourseID "
                        + "FROM Project P LEFT JOIN Groupp G "
                        + "ON P.GroupID = G.ID "
                        + "WHERE P.TopicID = ? AND G.CourseID = ?) AS A LEFT JOIN Member M "
                        + "ON A.GroupID = M.GroupID "
                        + "WHERE M.SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setInt(2, courseID);
                stm.setInt(3, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean checkStudentInGroup(int studentID, int subjectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Member "
                        + "WHERE StudentID = ? AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, studentID);
                stm.setInt(2, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }
}
