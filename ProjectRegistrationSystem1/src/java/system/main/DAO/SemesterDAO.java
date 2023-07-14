package system.main.DAO;

import DBUtil.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.main.DTO.Semester;

public class SemesterDAO {
    public List<Semester> getListSemester(int SemesterID) throws SQLException{
        List<Semester> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Semester "
                        + "WHERE ID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, SemesterID);
                rs = stm.executeQuery();
                if(rs.next()){
                    int SesYear = rs.getInt("Year");
                    String SesName = rs.getString("Name");
                    String Status = rs.getString("Status");
                    String StartDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    list.add(new Semester(SemesterID, SesName, SesYear, Status, 
                            StartDate, EndDate));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally{
            if(conn != null) conn.close();
            if(stm != null) stm.close();
            if(rs != null) rs.close();
        }
        return list;
    }
}
