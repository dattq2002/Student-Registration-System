/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturer.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lecturer.controller.manage.profile.Profile;
import system.main.DTO.LectureProfile;

/**
 *
 * @author Nam An
 */
public class ProfileDAO {
    public List<LectureProfile> searchProfileByEmail(String search) throws SQLException {
        List<LectureProfile> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "SELECT * "
                        + "FROM Lecturer "
                        + "WHERE Email =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    int lecturerID = rs.getInt("ID");
                    String lecturerCode = rs.getString("Code");
                    String name = rs.getString("Name");
                    String gender = rs.getString("Gender");
                    String birthday = rs.getString("Birthday");
                    String address = rs.getString("Address");
                    String city = rs.getString("City");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String email = rs.getString("Email");
                    String image = rs.getString("Image");
                    list.add(new LectureProfile(lecturerID, lecturerCode, name, 
                            birthday, phoneNumber, gender, address, city, 
                            email, image));
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
    
    public boolean updateProfile(String name, String gender, String birthday, 
            String address, String city, String phoneNumber, String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Lecturer"
                        + " set Name= ?, Gender= ?, Birthday= ?, Address= ?, City= ?, PhoneNumber= ?"
                        + " where Email= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, gender);
                stm.setString(3, birthday);
                stm.setString(4, address);
                stm.setString(5, city);
                stm.setString(6, phoneNumber);
                stm.setString(7, email);
                check = stm.executeUpdate()>0;
                
                if(check) {
                    String sql2 = "Update Account"
                        + " set FullName= ?"
                        + " where Email= ?";
                    stm = conn.prepareStatement(sql2);
                    stm.setString(1, name);
                    stm.setString(2, email);
                    check = stm.executeUpdate()>0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
}
