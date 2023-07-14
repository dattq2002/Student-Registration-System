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
}
