package admin.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Topic;
import system.main.DTO.TopicAssign;

public class TopicDAO {

    public List<TopicAssign> getListTopicAssign(int subID, int sesID) throws SQLException {
        List<TopicAssign> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, L.Name AS LecName FROM (SELECT A.ID AS "
                        + "TopicAssign, A.TopicID, A.SubjectID, A.StartDate, "
                        + "A.ModifyDate, A.SemesterID, A.Status, T.* "
                        + "FROM (SELECT * FROM TopicAssign "
                        + "WHERE SubjectID = ? AND SemesterID = ? AND "
                        + "Status = 'Processed') AS A LEFT JOIN Topic T "
                        + "ON A.TopicID = T.ID) AS B LEFT JOIN Lecturer L ON B.LecturerID = L.ID ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subID);
                stm.setInt(2, sesID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int topicAss = rs.getInt("TopicAssign");
                    int topicID = rs.getInt("TopicID");
                    String StartDate = rs.getString("StartDate");
                    String ModifyDate = rs.getString("ModifyDate");
                    String Status = rs.getString("Status");
                    String TopicCode = rs.getString("Code");
                    String TopicName = rs.getString("Name");
                    int lecID = rs.getInt("LecturerID");
                    String ShortDescription = rs.getString("ShortDescription");
                    String FullDescription = rs.getString("FullDescription");
                    String lecName = rs.getString("lecName");
                    list.add(new TopicAssign(topicAss, StartDate, ModifyDate,
                            sesID, Status, topicID, TopicCode, TopicName, lecName,
                            ShortDescription, FullDescription, lecID));
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

    public List<TopicAssign> getTopicAssign() throws SQLException {
        List<TopicAssign> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Topic";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("ID");
                    String TopicCode = rs.getString("Code");
                    String TopicName = rs.getString("Name");
                    int lecID = rs.getInt("LecturerID");
                    String lecName = getLecName(lecID);
                    String ShortDescription = rs.getString("ShortDescription");
                    String FullDescription = rs.getString("FullDescription");
                    list.add(new TopicAssign(lecName, lecID, topicID, TopicCode, 
                            TopicName, ShortDescription, FullDescription));
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

    public String getLecName(int lecID) throws SQLException {
        String lecName = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT Name FROM Lecturer WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                rs = stm.executeQuery();
                if(rs.next()){
                    lecName = rs.getString("Name");
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
        return lecName;
    }
    
    public boolean InsertTopicAssign(TopicAssign topic) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "INSERT INTO TopicAssign "
                        + "VALUES (?,?,GETDATE(),NULL,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topic.getTopicID());
                stm.setInt(2, topic.getSubjectID());
                stm.setInt(3, topic.getSemesterID());
                stm.setString(4, "Processed");
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }finally{
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
    
    public boolean DeleteTopicAssign(int topicID, int subID, int sesID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "DELETE TopicAssign "
                        + "WHERE TopicID = ? AND SubjectID = ? AND SemesterID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setInt(2, subID);
                stm.setInt(3, sesID);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }finally{
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
    
    public boolean checkTopicAssign(int subID, int sesID, int TopicID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT B.*, L.Name AS LecName FROM (SELECT A.ID AS "
                        + "TopicAssign, A.TopicID, A.SubjectID, A.StartDate, "
                        + "A.ModifyDate, A.SemesterID, A.Status, T.* "
                        + "FROM (SELECT * FROM TopicAssign "
                        + "WHERE SubjectID = ? AND SemesterID = ? AND "
                        + "Status = 'Processed') AS A LEFT JOIN Topic T "
                        + "ON A.TopicID = T.ID WHERE T.ID = ?) AS B LEFT JOIN Lecturer L ON B.LecturerID = L.ID ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subID);
                stm.setInt(2, sesID);
                stm.setInt(3, TopicID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }
}
