package DAO;

import DBUtil.Util;
import DTO.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDAO {
    
    //checkLogin
    public UserAccountDTO checkLogin(String email, String password) throws SQLException {
        UserAccountDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT FullName, Role FROM Account "
                        + "WHERE Email =? AND Password = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {       
                    String fullName = rs.getString("FullName");
                    String roleID = rs.getString("Role");
                    user = new UserAccountDTO(email, password, fullName, roleID);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at Check Login !!");
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
        return user;
    }
    
    //checkEmail 
    public String checkEmail(String email) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Email " + "FROM Account "
                        + "WHERE Email =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return email;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error at Check Email!!");
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
        return null;
    }
    
    //resetPassword
    public boolean resetPassword(String password, String email) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Account " + "SET password =? "
                        + "WHERE email = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, email);
                int value = stm.executeUpdate();
                check = value > 0;
            }
        } catch (SQLException e) {
            System.err.println("Err at ResetPassword!!");
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
