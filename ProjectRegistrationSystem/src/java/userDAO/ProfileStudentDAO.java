package userDAO;

import DBUtil.Util;
import DTO.StudentProfile;
import DTO.UserAccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileStudentDAO {

    public StudentProfile profileStudent(String email) throws SQLException {
        StudentProfile profile = null;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            conn = Util.getConnection();
//            if (conn != null) {
//                String sql = "SELECT * FROM Student "
//                        + "WHERE Email = (SELECT Email FROM Account "
//                        + "			WHERE Email = ?)";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, email);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    int ID = rs.getInt("ID");
//                    String code = rs.getString("Code");
//                    String name = rs.getString("Name");
//                    String birth = rs.getString("Birthday");
//                    String email1 = rs.getString("Email");
//                    profile = new StudentProfile(ID, code, name, birth, email1);
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Err at profileStudent!!!");
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//        }
        return profile;
    }
    
    //updateProfile
    public boolean updateProfileStudent(StudentProfile st) throws SQLException {
        boolean check = false;
//        Connection conn = null;
//        PreparedStatement stm = null;
//        try {
//            conn = Util.getConnection();
//            if(conn != null) {
//                String sql = "Update Student"
//                        + " set Name= ?, Birthday= ?"
//                        + " where Email= ?";
//                stm = conn.prepareStatement(sql);
//                stm.setString(1, st.getName());
//                stm.setString(2, st.getBirthday());
//                stm.setString(3, st.getEmail());
//                check = stm.executeUpdate()>0;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Error at updateProfileStudent");
//        } finally {
//            if(stm != null) stm.close();
//            if(conn != null) conn.close();
//        }
        return check;
    }
}
