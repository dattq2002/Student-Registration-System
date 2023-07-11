package managerDAO;

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

public class GroupMNGDAO {

    public List<Group> getListGroupMNG(String email) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select na.*, s.Code as SubjectCode "
                        + "from (select na.*, sic.LecturerID, sic.SubjectID "
                        + "from (select g.ID as GroupID, g.Name as GroupName, g.CourseID, g.Status, c.Name as CourseName, c.Code as CourseCode "
                        + "from Groupp g LEFT JOIN Course c "
                        + "ON g.CourseID = c.ID "
                        + "where c.SemesterID = 11114 ) as na LEFT JOIN SubjectInClass sic "
                        + "ON na.CourseID = sic.CourseID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID "
                        + "where na.LecturerID = (select ID from Lecturer where Email = ?) and na.Status = 'True'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupName = rs.getString("GroupName");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    int GroupID = rs.getInt("GroupID");
                    list.add(new Group(groupName, SubjectID, SubjectCode, CourseName, CourseCode, GroupID));
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

    public List<Group> detailGroupMNG(int courseID, int subjectID, String groupName) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select s.Code, s.ID, s.Name, na.CourseID, na.SubjectID, na.GroupName, na.isLeader, "
                        + "na.Status, na.ID as MemberID "
                        + "from (select m.ID, m.StudentID, m.SubjectID, g.CourseID, g.Name as GroupName, m.isLeader, g.Status "
                        + "from Member m LEFT JOIN Groupp g "
                        + "ON m.GroupID = g.ID) as na LEFT JOIN Student s "
                        + "ON na.StudentID = s.ID "
                        + "where na.CourseID in (select ID "
                        + "from Course "
                        + "where SemesterID = 11114 and ID = ?)  and na.SubjectID = ? and na.GroupName = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setInt(2, subjectID);
                stm.setString(3, groupName);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int studentID = rs.getInt("ID");
                    String studentCode = rs.getString("Code");
                    String studentName = rs.getString("Name");
                    String gName = rs.getString("Code");
                    String isLeader = rs.getString("isLeader");
                    int memberID = rs.getInt("MemberID");
                    list.add(new Group(studentName, gName, isLeader, studentCode, studentID, memberID));
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

    public boolean updateMember(String isLeader, int memberID) throws SQLException {
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

    public boolean deleteMember(int memberID) throws SQLException {
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

    public List<Group> falseGroupMNG(String email) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select na.*,  s.Code as StudentCode, s.Name as StudentName "
                        + "from (select na.GroupID, na.GroupName, na.CourseName, na.CourseCode, na.SubjectCode, na.SubjectID, "
                        + "m.StudentID, m.isLeader "
                        + "from (select na.*, s.Code as SubjectCode "
                        + "from (select na.*, sic.LecturerID, sic.SubjectID "
                        + "from (select g.ID as GroupID, g.Name as GroupName, g.CourseID, g.Status, "
                        + "c.Name as CourseName, c.Code as CourseCode "
                        + "from Groupp g LEFT JOIN Course c "
                        + "ON g.CourseID = c.ID "
                        + "where c.SemesterID = 11114 ) as na LEFT JOIN SubjectInClass sic "
                        + "ON na.CourseID = sic.CourseID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID "
                        + "where na.LecturerID = (select ID from Lecturer where Email = ?) "
                        + "and na.Status = 'False') as na LEFT JOIN Member m "
                        + "ON na.GroupID = m.GroupID) as na LEFT JOIN Student s "
                        + "ON na.StudentID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int GroupID = rs.getInt("GroupID");
                    String groupName = rs.getString("GroupName");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    int StudentID = rs.getInt("StudentID");
                    String StudentCode = rs.getString("StudentCode");
                    String StudentName = rs.getString("StudentName");
                    String isLeader = rs.getString("isLeader");

                    list.add(new Group(GroupID, StudentName, groupName, isLeader, StudentCode, StudentID, SubjectID, SubjectCode, CourseName, CourseCode));
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

    public boolean approveGroup(int groupID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Update Groupp"
                        + " set Status= 'True'"
                        + " where ID= ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
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

    public List<GroupProject> listProjectGroupMNG(int groupID) throws SQLException {
        List<GroupProject> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select p.*, t.Name as TopicName "
                        + "from Project p LEFT JOIN Topic t "
                        + "ON p.TopicID = t.ID "
                        + "where p.GroupID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int projectID = rs.getInt("ID");
                    int topicID = rs.getInt("TopicID");
                    String topicName = rs.getString("TopicName");
                    String context = rs.getString("Context");
                    String actors = rs.getString("Actors");
                    String function = rs.getString("Func-Requirement");
                    String note = rs.getString("Note");
                    list.add(new GroupProject(context, actors, function, note, projectID, topicID, topicName));
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
    
    public boolean updateTopicProject(int topicID, int projectID) throws SQLException {
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
    
    public boolean deleteGroup(int groupID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Delete Member" + " where GroupID=? "
                        + "Delete Project" + " where GroupID=? " 
                        + "Delete Groupp" + " where ID=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
                stm.setInt(2, groupID);
                stm.setInt(3, groupID);
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
    
    public List<ClassInformation> getListClassMNG(String email) throws SQLException {
        List<ClassInformation> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select s.Code as SubjectCode, s.ID as SubjectID, "
                        + "na.CourseName, na.CourseCode, na.CourseID "
                        + "from (select sic.*, c.Code as CourseCode, c.Name as CourseName "
                        + "from SubjectInClass sic LEFT JOIN Course c "
                        + "ON sic.CourseID = c.ID "
                        + "where c.SemesterID = 11114 and sic.LecturerID = "
                        + "(select ID from Lecturer where Email = ?) and sic.Status = 1) "
                        + "as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    int CourseID = rs.getInt("CourseID");
                    list.add(new ClassInformation(SubjectCode, SubjectID, CourseCode, CourseName, CourseID));
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
    
    public List<Group> getListGroupMNG(String email, int cID, int sID) throws SQLException {
        List<Group> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "select na.*, s.Code as SubjectCode "
                        + "from (select na.*, sic.LecturerID, sic.SubjectID "
                        + "from (select g.ID as GroupID, g.Name as GroupName, "
                        + "g.CourseID, g.Status, c.Name as CourseName, c.Code as CourseCode "
                        + "from Groupp g LEFT JOIN Course c "
                        + "ON g.CourseID = c.ID "
                        + "where c.SemesterID = 11114 ) as na LEFT JOIN SubjectInClass sic "
                        + "ON na.CourseID = sic.CourseID) as na LEFT JOIN Subject s "
                        + "ON na.SubjectID = s.ID "
                        + "where na.LecturerID = (select ID from Lecturer where Email = ?) "
                        + "and na.Status = 'True' and na.CourseCode = ? and s.ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setInt(2, cID);
                stm.setInt(3, sID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupName = rs.getString("GroupName");
                    String CourseName = rs.getString("CourseName");
                    int CourseCode = rs.getInt("CourseCode");
                    String SubjectCode = rs.getString("SubjectCode");
                    int SubjectID = rs.getInt("SubjectID");
                    int GroupID = rs.getInt("GroupID");
                    list.add(new Group(groupName, SubjectID, SubjectCode, CourseName, CourseCode, GroupID));
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
}
