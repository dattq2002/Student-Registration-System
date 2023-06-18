package adminDAO;

import DBUtil.Util;
import DTO.LectureProfile;
import DTO.StudentProfile;
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
                    user = new UserAccountDTO(id, code, email, password, role,FullName, Status);
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
                while(rs.next()){
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
                while(rs.next()){
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

//    //createAccount
//    public boolean createAccount(UserAccountDTO account) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        try {
//            conn = Util.getConnection();
//            if (conn != null) {
//                String sql = "Insert into Account(Email, Password, Role, FullName)"
//                        + " values(?,?,?,?)";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, account.getEmail());
//                stm.setString(2, account.getPassword());
//                stm.setString(3, account.getRoleID());
//                stm.setString(4, account.getFullName());
//                check = stm.executeUpdate() > 0;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Error at createAccount");
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }
//
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
//
    //updateAccount

    public boolean updateAccount(int id, UserAccountDTO acc) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "UPDATE Account SET Role = ?, FullName =?, Status =? "
                        + "WHERE AccountID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, acc.getRoleID());
                stm.setString(2, acc.getFullName());
                stm.setString(3, acc.getStatus());
                stm.setInt(4, id);
                check = stm.executeUpdate() > 0;
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
}
