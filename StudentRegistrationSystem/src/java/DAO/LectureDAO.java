/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtil.Util;
import DTO.LectureProfile;
import DTO.StudentProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meryc
 */
public class LectureDAO {
    //search student profile
    public List<LectureProfile> getListLecture(int search) throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * "
                        + "FROM Lecturer "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, search);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String Code = rs.getString("Code");
                    String Name = rs.getString("Name");
                    String Birthday = rs.getString("Birthday");
                    String Email = rs.getString("Email");
                    list.add(new LectureProfile(id, Code, Name, Birthday, Email));
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
    public boolean AddLecture(LectureProfile profile) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Lecturer(ID, Code, Name, Birthday, Email) "
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
            System.err.println("Error at AddLecture!!");
        }finally{
            if(conn != null) conn.close();
            if(stm != null) stm.close();
        }
        return check;
    }
    
    //delete Lecture profile
    public boolean DeleteLecture(int id) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "DELETE Lecturer "
                        +"WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                int value = stm.executeUpdate();
                check = (value > 0) ? true : false;
            }
        } catch (ClassNotFoundException | SQLException e) {
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
    public boolean UpdateLecture(int id, String name, String code) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql ="UPDATE Lecturer "
                        +"SET code = ?, name = ? WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, name);
                stm.setInt(3, id);
                int value = stm.executeUpdate();
                check = value > 0? true: false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at UpdateLecturer!!");
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
}
