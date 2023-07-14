package lecturer.DAO;

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
                String sql = "SELECT B.*, L.Name AS LecName "
                        + "FROM (SELECT A.ID AS TopicAssign, A.TopicID, A.SubjectID, A.StartDate, "
                        + "A.ModifyDate, A.SemesterID, A.Status, T.*  "
                        + "FROM (SELECT * FROM TopicAssign WHERE SubjectID = ? AND SemesterID = ?) AS A "
                        + "LEFT JOIN Topic T ON A.TopicID = T.ID) AS B LEFT JOIN Lecturer L "
                        + "ON B.LecturerID = L.ID ";
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

    public List<TopicAssign> getListSourceTopic() throws SQLException {
        List<TopicAssign> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT T.*, L.Name AS LecturerName "
                        + "FROM Topic T LEFT JOIN Lecturer L "
                        + "ON T.LecturerID = L.ID";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int topicID = rs.getInt("ID");
                    String topicCode = rs.getString("Code");
                    String topicName = rs.getString("Name");
                    int lecturerID = rs.getInt("LecturerID");
                    String topicShort = rs.getString("ShortDescription");
                    String topicFull = rs.getString("FullDescription");
                    String lecturerName = rs.getString("LecturerName");
                    list.add(new TopicAssign(lecturerName, lecturerID, topicID, topicCode, topicName, topicShort, topicFull));
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
        return list;
    }
    
    public boolean updateTopic(int topicID, String topicName, 
            String shortDescription, String fullDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update Topic"
                        + " set Name= ?, ShortDescription= ?, FullDescription= ?"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, topicName);
                stm.setString(2, shortDescription);
                stm.setString(3, fullDescription);
                stm.setInt(4, topicID);
                check = stm.executeUpdate() > 0;

                String sql2 = "Update TopicAssign"
                        + " set ModifyDate= GETDATE(), Status= 0"
                        + " where TopicID= ?";
                stm = conn.prepareStatement(sql2);
                stm.setInt(1, topicID);
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
    
    public int getLecturerID(String email) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Select ID"
                        + " from Lecturer"
                        + " where email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int lecID = rs.getInt("ID");
                    result = lecID;
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
        return result;
    }
    
    public boolean checkDuplicateTopicID(int id, String code) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Topic "
                        + "WHERE ID = ? AND Code = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, code);
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
    
    public boolean createTopic(int topicID, String topicCode, String topicName, 
            int LecturerID, String shorDescription, String fullDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Insert into Topic(ID, Code, Name, LecturerID, ShortDescription, FullDescription)"
                        + " values(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setString(2, topicCode);
                stm.setString(3, topicName);
                stm.setInt(4, LecturerID);
                stm.setString(5, shorDescription);
                stm.setString(6, fullDescription);
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
}
