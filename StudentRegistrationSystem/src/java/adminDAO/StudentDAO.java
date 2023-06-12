package adminDAO;

import DBUtil.Util;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    //search student profile
    public List<StudentProfile> getListStudent(int search) throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Student "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, search );
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String Code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(id, Code, Name, Birthday, Email));
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

    //add student profile
    public boolean AddStudent(StudentProfile profile) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Student(ID, Code, Name, Birthday, Email) "
                        + "VALUES (?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, profile.getID());
                stm.setString(2, profile.getCode());
                stm.setString(3, profile.getName());
                stm.setString(4, profile.getBirthday());
                stm.setString(5, profile.getEmail());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at AddStudent!!");
        }finally{
            if(conn != null) conn.close();
            if(stm != null) stm.close();
        }
        return check;
    }
    
    //delete Student profile
    public boolean DeleteStudent(int id) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "DELETE Student "
                        +"WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                int value = stm.executeUpdate();
                check = (value > 0) ? true : false;
            }
        } catch (Exception e) {
            System.err.println("Err at DeleteStudent!!");
        }finally{
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }
    
    //update Profile
    public boolean UpdateStudent(int id, String name, String code) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql ="UPDATE Student "
                        +"SET code = ?, name = ? WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, name);
                stm.setInt(3, id);
                int value = stm.executeUpdate();
                check = value > 0? true: false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at UpdateStudent!!");
        }finally{
            if (conn != null) {
                conn.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return check;
    }
    
    //showListStudent
    public List<StudentProfile> getListStudent() throws SQLException {
        List<StudentProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT * "
                        + " FROM Student ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("ID");
                    String code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String Email = rs.getString("Email");
                    list.add(new StudentProfile(id, code, Name, Birthday, Email));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListStudent!");
        } finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
}
