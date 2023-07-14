package lecturer.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Subject;

public class SubjectDAO {

    public List<Subject> getListSubject(int courseID, String Email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Subject> list = new ArrayList<>();
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "SELECT DISTINCT S.*, I.LecturerID "
                        + "FROM Subject S LEFT JOIN SubjectInClass I "
                        + "ON S.ID = I.SubjectID "
                        + "WHERE I.CourseID = ? AND I.LecturerID = (SELECT ID FROM Lecturer WHERE Email = ?)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, courseID);
                stm.setString(2, Email);
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
}
