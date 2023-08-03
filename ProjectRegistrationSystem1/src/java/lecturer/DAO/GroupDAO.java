package lecturer.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
                String sql = "SELECT NA.*, T.Code AS TopicCode "
                        + "FROM (SELECT G.*, TI.TopicID "
                        + "FROM Groupp G LEFT JOIN TopicInGroup TI "
                        + "ON G.ID = TI.GroupID) AS NA LEFT JOIN Topic T "
                        + "ON NA.TopicID = T.ID "
                        + "WHERE NA.CourseID = ? AND SubjectID = ?";
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

    public List<Group> getListGroupDetail(int grID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT M.*, S.Code AS StudentCode, S.Name AS StudentName "
                        + "FROM Member M LEFT JOIN Student S "
                        + "ON M.StudentID = S.ID "
                        + "WHERE M.GroupID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
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
                String sql = "SELECT TI.*, TP.Code AS TopicCode, TP.Name AS TopicName, "
                        + "TP.Context, TP.Actor, TP.[Function] "
                        + "FROM TopicInGroup TI LEFT JOIN Topic TP "
                        + "ON TI.TopicID = TP.ID "
                        + "WHERE TI.GroupID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int projectID = rs.getInt("ID");
                    int topicID = rs.getInt("TopicID");
                    String topicCode = rs.getString("TopicCode");
                    String topicName = rs.getString("TopicName");
                    String context = rs.getString("Context");
                    String actors = rs.getString("Actor");
                    String function = rs.getString("Function");
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

    public boolean updateProjectTopic(int topicID, int projectID, String note) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update TopicInGroup"
                        + " set TopicID= ?, Note= ?"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, topicID);
                stm.setString(2, note);
                stm.setInt(3, projectID);
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
                String sql = "SELECT * FROM TopicAssign "
                        + "WHERE SubjectID = ? AND SemesterID = ? "
                        + "AND TopicID = ? AND Status = 'Processed'";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, subjectID);
                stm.setInt(2, semesterID);
                stm.setInt(3, topicID);
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
    
    public boolean checkLeader(int grID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Member WHERE GroupID = ? "
                        + "AND isLeader = 'LD'";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
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
                        + " where ID = ? ";
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
                String sql = "DELETE Member WHERE GroupID = ? "
                        + "DELETE TopicInGroup WHERE GroupID = ? "
                        + "DELETE Groupp WHERE ID = ? AND CourseID = ? AND SubjectID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
                stm.setInt(2, groupID);
                stm.setInt(3, groupID);
                stm.setInt(4, courseID);
                stm.setInt(5, subjectID);
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
                String sql = "SELECT * FROM Groupp "
                        + "WHERE CourseID = ? AND SubjectID = ? AND Name = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subjectID);
                stm.setString(3, name);
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

    public boolean insertGroup(int courseID, int subjectID, String grName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        int length = 5;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            randomString.append(randomChar);
        }
        String code = randomString + "" + randomNumber;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Groupp "
                        + "VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, grName);
                stm.setInt(3, courseID);
                stm.setInt(4, subjectID);
                check = stm.executeUpdate() > 0;
//                if (check1) {
//                    String sql1 = "SELECT ID FROM Groupp "
//                            + "WHERE Name = ? AND CourseID = ? AND SubjectID = ?";
//                    stm = conn.prepareStatement(sql1);
//                    stm.setString(1, grName);
//                    stm.setInt(2, courseID);
//                    stm.setInt(3, subjectID);
//                    rs = stm.executeQuery();
//                    if (rs.next()) {
//                        int grID = rs.getInt("ID");
//                        String sql2 = "INSERT INTO TopicInGroup "
//                                + "VALUES(?,?,GETDATE(),N'Có thể cập nhật')";
//                        stm = conn.prepareStatement(sql2);
//                        stm.setInt(1, grID);
//                        stm.setInt(2, topicID);
//                        check = stm.executeUpdate() > 0;
//                    }
//                }
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

    public boolean insertTopicInGroup(int grID, int topicID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO TopicInGroup "
                        + "VALUES(?,?,GETDATE(),N'Có thể cập nhật')";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, grID);
                stm.setInt(2, topicID);
                check = stm.executeUpdate() > 0;
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
                String sql = "SELECT * "
                        + "FROM Groupp G LEFT JOIN TopicInGroup T "
                        + "ON G.ID = T.GroupID "
                        + "WHERE TopicID = ? AND CourseID = ? AND SubjectID = ?";
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

    public boolean addStudent(int studentID, int grID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Insert into Member(StudentID, GroupID, StartDate, isLeader) "
                        + "values(?,?,GETDATE(),'MB')";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, studentID);
                stm.setInt(2, grID);
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
