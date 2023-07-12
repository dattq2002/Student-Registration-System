package DAO;

import DBUtil.Util;
import DTO.Topic;
import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    //checkLogin
    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
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
                    user = new UserDTO(email, password, roleID, fullName);
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
    
    public List<UserDTO> getListAccount() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select * "
                        + " from account ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String email = rs.getString("Email");
                    String fullName = rs.getString("FullName");
                    String roleID = rs.getString("Role");
                    String password = rs.getString("Password");
                    list.add(new UserDTO(email, password, roleID, fullName));
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
    
    public List<UserDTO> searchAccountByEmail(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select * "
                        + " from account "
                        + " where Email =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, search);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String email = rs.getString("Email");
                    String fullName = rs.getString("FullName");
                    String roleID = rs.getString("Role");
                    String password = rs.getString("Password");
                    list.add(new UserDTO(email, password, roleID, fullName));
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
    
    public boolean checkDuplicateEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Select Email"
                        + " from Account"
                        + " where Email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
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
    
    public boolean createAccount(UserDTO account) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Insert into Account(Email, Password, Role, FullName)"
                        + " values(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account.getEmail());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getRoleID());
                stm.setString(4, account.getFullName());
                check = stm.executeUpdate() > 0;     
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return check;
    }
  
    public boolean deleteAccount(String email) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Delete Account"
                        + " where Email= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
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
    
    public boolean updateAccount(UserDTO account) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null) {
                String sql = "Update Account"
                        + " set Password= ?, FullName= ?"
                        + " where Email= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, account.getPassword());
                stm.setString(2, account.getFullName());
                stm.setString(3, account.getEmail());
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
    
}
