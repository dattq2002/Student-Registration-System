package userDAO;

import DBUtil.Util;
import DTO.Topic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

    public List<Topic> getListTopic() throws SQLException {
        List<Topic> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT t.*, ta.SemesterID "
                        + "FROM Topic t LEFT JOIN TopicAssign ta "
                        + "ON t.ID = ta.TopicID WHERE ta.SemesterID = 11114";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String name = rs.getString("Name");
                    int lecID = rs.getInt("LecturerID");
                    String nameLecture = getNameLecture(lecID);
                    String Shortdescription = rs.getString("ShortDescription");
                    String Fulldescription = rs.getString("FullDescription");
                    int semesterID = rs.getInt("SemesterID");
                    list.add(new Topic(id, code, name, nameLecture, 
                            Shortdescription, Fulldescription, semesterID));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListTopic!!!");
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

    public String getNameLecture(int ID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Name FROM Lecturer WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getNameLecture!!!");
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
        return name;
    }
}
