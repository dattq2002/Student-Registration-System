package managerDAO;

import DBUtil.Util;
import DTO.Topic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicMNGDAO {
    public List<Topic> getListTopicMNG() throws SQLException {
        List<Topic> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select * "
                        + " from topic ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int topicID = rs.getInt("ID");
                    String topicCode = rs.getString("Code");
                    String topicName = rs.getString("Name");
                    int lecturerID = rs.getInt("LecturerID");
                    String topicShort = rs.getString("ShortDescription");
                    String topicFull = rs.getString("FullDescription");
                    list.add(new Topic(topicID, topicCode, topicName, lecturerID, 
                            topicShort, topicFull));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public List<Topic> searchTopicByID(String search) throws SQLException {
        List<Topic> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select * "
                        + " from topic "
                        + " where CAST(ID AS VARCHAR) =? or Code =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                stm.setString(2, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int topicID = rs.getInt("ID");
                    String topicCode = rs.getString("Code");
                    String topicName = rs.getString("Name");
                    String topicShort = rs.getString("ShortDescription");
                    String topicFull = rs.getString("FullDescription");
                    list.add(new Topic(topicID, topicCode, topicName, topicShort, topicFull));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    public boolean deleteTopic(int topicID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Delete TopicAssign" + " where TopicID=? " 
                        + "Delete Topic" + " where ID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setInt(2, topicID);
                int value = stm.executeUpdate();
                result = value > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return result;
    }
    
    public boolean updateTopic(int topicID, String topicCode, String topicName, String shortDescription, String fullDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Topic"
                        + " set Code= ?, Name= ?, ShortDescription= ?, FullDescription= ?"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, topicCode);
                stm.setString(2, topicName);
                stm.setString(3, shortDescription);
                stm.setString(4, fullDescription);
                stm.setInt(5, topicID);
                check = stm.executeUpdate()>0;
                
                String sql2 = "Update TopicAssign"
                        + " set ModifyDate= GETDATE(), Status= 0"
                        + " where TopicID= ?";
                stm = conn.prepareStatement(sql2);       
                stm.setInt(1, topicID);
                check = stm.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean createTopic(int topicID, String topicCode, String topicName, int LecturerID, String shorDescription, String fullDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Insert into Topic(ID, Code, Name, LecturerID, ShortDescription, FullDescription)"
                        + " values(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setString(2, topicCode);
                stm.setString(3, topicName);
                stm.setInt(4, LecturerID);
                stm.setString(5, shorDescription);
                stm.setString(6, fullDescription);
                check = stm.executeUpdate()>0;  
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean assignTopic(int topicID, int subjectID, int semesterID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Insert into TopicAssign(TopicID, SubjectID, SemesterID, StartDate, Status)"
                        + " values(?,?,?,GETDATE(),0)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setInt(2, subjectID);
                stm.setInt(3, semesterID);
                check = stm.executeUpdate()>0;  
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateTopicID(int id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Topic"
                        + " where ID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
        
    public boolean checkDuplicateTopicCode(String topicCode) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Topic"
                        + " where Code=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, topicCode);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateSubjectID(int id, String code) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID, Code"
                        + " from Subject"
                        + " where ID=? and Code=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, code);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean checkDuplicateSemesterID(int year, String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select Year, Name"
                        + " from Semester"
                        + " where Year=? and Name=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setString(2, name);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public boolean findSubjectID(int subjectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select SubjectID"
                        + " from TopicAssign"
                        + " where SubjectID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subjectID);
                rs = stm.executeQuery();
                if(rs.next()) {
                    check = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
    
    public int getSemesterID(int year, String name) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Semester"
                        + " where Year=? and Name=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, year);
                stm.setString(2, name);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int semesterID = rs.getInt("ID");
                    result = semesterID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return result;
    }
    
    public int getLecturerID(String email) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select ID"
                        + " from Lecturer"
                        + " where email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int lecID = rs.getInt("ID");
                    result = lecID;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return result;
    }
    
}
