package admin.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Subject;

public class SubjectDAO {

    public List<Subject> getListSubject() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Subject> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Subject";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int subID = rs.getInt("ID");
                    String subCode = rs.getString("Code");
                    String subName = rs.getString("Name");
                    String subDes = rs.getString("Description");
                    int credit = rs.getInt("CreditID");
                    list.add(new Subject(subID, subCode, subName, subDes, credit));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    public boolean InsertSubjectInClass(int lecID, int courseID, int subID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO SubjectInClass "
                        + "VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, lecID);
                stm.setInt(2, subID);
                stm.setInt(3, courseID);
                stm.setInt(4, 1);
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
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
    
    public int getIDLec(String lecName) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int lecID = 0;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT ID FROM Lecturer WHERE Name = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, lecName);
                rs = stm.executeQuery();
                if(rs.next()){
                    lecID = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        }finally {
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
        return lecID;
    }
}
