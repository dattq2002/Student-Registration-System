package adminDAO;

import DBUtil.Util;
import DTO.LectureProfile;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {

    //-----------------SEARCH------------------------
    //search student profile
    public List<StudentProfile> SearchStudent(String name) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Student "
                        + "WHERE Name LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Major = rs.getString("Major");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(id, code, Name, Birthday, PhoneNum, Gender, Address, City, Major,Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListStudent!!");
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

    //showListStudent
    public List<StudentProfile> getListStudent() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + " FROM Student ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Major = rs.getString("Major");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(id, code, Name, Birthday, 
                            PhoneNum, Gender, Address, City, Major, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListStudent!");
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
    
    //search Lecture profile
    public List<LectureProfile> getListLecture(String name) throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Lecturer "
                        + "WHERE Name LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday, 
                            PhoneNum, Gender, Address, City,Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListStudent!!");
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
    
    //showListLecture
    public List<LectureProfile> getListLecture() throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + " FROM Lecturer ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String PhoneNum = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");
                    String Address = rs.getString("Address");
                    String City = rs.getString("City");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, code, Name, Birthday, 
                            PhoneNum, Gender, Address, City, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListLecture!");
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    //-------------------------------------------------------
    //update Profile
    public boolean UpdateProfile(int id, String code, String name) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                if (code.equals("LT")) {
                    String sql = "UPDATE Lecturer SET Name =? "
                            + "WHERE ID =?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                } else {
                    String sql = "UPDATE Student SET Name =? "
                            + "WHERE ID =?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at UpdateStudent!!");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }  
}
