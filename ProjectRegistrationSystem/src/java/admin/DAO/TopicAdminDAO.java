package admin.DAO;

import DBUtil.Util;
import DTO.Topic;
import DTO.TopicAssign;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicAdminDAO {
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
    
    //declineTopic
    public boolean declineTopic(int topicID, int SubjectID, boolean status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE TopicAssign SET Status = ? "
                        + "WHERE TopicID = ? AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setBoolean(1, false);
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
                        + "LEFT JOIN Semester s ON a.SemesterID = s.ID ";

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
    
    public Topic getTopicDetail(int topID, int subID) throws SQLException {
        Topic top = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Topic WHERE ID = (SELECT TopicID "
                        + "FROM TopicAssign WHERE TopicID = ? "
                        + "AND SubjectID = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topID);
                stm.setInt(2, subID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String topCode = rs.getString("Code");
                    String topName = rs.getString("Name");
                    String shortDes = rs.getString("ShortDescription");
                    String fulltDes = rs.getString("FullDescription");
                    top = new Topic(topID, topCode, topName, null, shortDes, fulltDes, 0);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        return top;
    }
}
