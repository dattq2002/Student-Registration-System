package adminDAO;

import DBUtil.Util;
import DTO.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAO {

    //----------------Login-----------------
    //checkLoginAdmin
    public UserAccountDTO checkLogin(String email, String password) throws SQLException {
        UserAccountDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT AccountID, FullName, Role, "
                        + "Code, Status FROM Account "
                        + "WHERE Email = ? AND Password =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("AccountID");
                    String code = rs.getString("Code");
                    String role = rs.getString("Role");
                    String FullName = rs.getString("FullName");
                    String Status = rs.getString("Status");
                    user = new UserAccountDTO(id, code, email, password, role, FullName, Status);
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

    //-----------------------------------------------------
    //-------------------ForgotPassword--------------------
    //checkEmail 
    public String checkEmail(String email) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT Email FROM Account "
                        + "WHERE Email =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    email = rs.getString("Email");
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
        return email;
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

    //------------------------------------------------
    //showListAccount
    public List<UserAccountDTO> getListAccount() throws SQLException {
        List<UserAccountDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Account";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("AccountID");
                    String code = rs.getString("Code");
                    String role = rs.getString("Role");
                    String FullName = rs.getString("FullName");
                    String Status = rs.getString("Status");
                    String email = rs.getString("Email");
                    String pass = rs.getString("Password");
                    list.add(new UserAccountDTO(id, code, email, pass, role, FullName, Status));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at showListAccount!");
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

    //searchAccountByEmail
    public List<UserAccountDTO> searchAccountByEmail(String search) throws SQLException {
        List<UserAccountDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Account "
                        + "WHERE FullName LIKE ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("AccountID");
                    String code = rs.getString("Code");
                    String role = rs.getString("Role");
                    String FullName = rs.getString("FullName");
                    String Status = rs.getString("Status");
                    String email = rs.getString("Email");
                    String pass = rs.getString("Password");
                    list.add(new UserAccountDTO(id, code, email, pass, role, FullName, Status));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at searchAccountByEmail");
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

    //checkDuplicateEmail
    public boolean checkDuplicateEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "Select Email"
                        + " from Account"
                        + " where Email=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at checkDuplicateEmail");
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

    //deleteAccount
    public boolean deleteAccount(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "DELETE Account "
                        + "WHERE AccountID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, id);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at deleteAccount");
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

    //updateAccount
    public boolean updateAccount(int id, String name, UserAccountDTO acc) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String email = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Account SET Role = ?, FullName = ?, Status =? "
                        + "WHERE AccountID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, acc.getRoleID());
                stm.setString(2, acc.getFullName());
                stm.setString(3, acc.getStatus());
                stm.setInt(4, id);
                check = stm.executeUpdate() > 0;
                String sql2 = "SELECT Email FROM Account WHERE AccountID = ?";
                stm = conn.prepareStatement(sql2);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if(rs.next()){
                    email = rs.getString("Email");
                }
                if (email.contains("@fe.edu.vn")) {
                    String sql3 = "UPDATE Lecturer SET Name = ? "
                            + "WHERE Email = (SELECT Email FROM Account "
                            + "WHERE AccountID = ?)";
                    stm = conn.prepareStatement(sql3);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                } else {
                    String sql4 = "UPDATE Student SET Name = ? "
                            + "WHERE Email = (SELECT Email FROM Account "
                            + "WHERE AccountID = ?)";
                    stm = conn.prepareStatement(sql4);
                    stm.setString(1, name);
                    stm.setInt(2, id);
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at updateAccount");
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
    
    //AddAccountFromFile
    public boolean addAccountFromFile(UserAccountDTO acc) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "INSERT INTO Account(Email, Password, FullName, "
                        + "Role, Code, Status) "
                        + "VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, acc.getEmail());
                stm.setString(2, acc.getPassword());
                stm.setString(3, acc.getFullName());
                stm.setString(4, acc.getRoleID());
                stm.setString(5, acc.getCode());
                stm.setString(6, acc.getStatus());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
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
