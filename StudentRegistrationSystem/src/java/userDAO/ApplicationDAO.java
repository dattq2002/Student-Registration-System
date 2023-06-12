package userDAO;

import DBUtil.Util;
import DTO.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    //xử lý đơn
    public boolean processApplication(Application form) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = Util.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO "
                        + "Form(LecID, Type, StudentID, Reason, CreateDate, Status,CourseID) "
                        + "VALUES (?,?,?,?,GETDATE(),?,?)";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, form.getLecID());
            stm.setString(2, form.getType());
            stm.setInt(3, form.getStuID());
            stm.setString(4, form.getReason());
            stm.setString(5, "processing");
            stm.setInt(6, form.getCourseID());
            check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error at processApplication!!");
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
    //get ID lec
    public int getLectureID(String Name) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int ID = 0;
        
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT ID FROM Lecturer WHERE Name =?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, Name);
                rs = stm.executeQuery();
                if(rs.next()){
                    ID = rs.getInt("ID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getIDLecture!!!");
        }finally{
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
        return ID;
    }
    
    //getListForm
    
    public List<Application> getListForm() throws SQLException{
        List<Application> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT * FROM Form";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("ID");
                    int StID = rs.getInt("StudentID");
                    String Stcode = getStudentCode(StID);
                    String CreDate = rs.getString("CreateDate");
                    String type = rs.getString("Type");
                    String reason = rs.getString("Reason");
                    int lecID = rs.getInt("lecID");
                    String lecName = getNameLecture(lecID);
                    String processDate =rs.getString("ProcessDate");
                    String lecNote = rs.getString("Note");
                    String status = rs.getString("Status");
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = getCourseName(CourseID);
                    list.add(new Application(id, StID, Stcode, CreDate, type, 
                        lecID, lecName, reason, lecNote, status, CourseID,CourseName, processDate));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Err at getListSubject!!!");
        }finally {
            if(rs != null) rs.close();
            if(stm != null) stm.close();
            if(conn != null) conn.close();
        }
        return list;
    }
    
    //getnameCourse
    public String getCourseName(int ID) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null ;
        
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT Name FROM Course WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if(rs.next()){
                    name = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getCourseName!!!");
        }finally{
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
        return name;
    }
    
    //getnameLec
    public String getNameLecture(int ID) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String name = null ;
        
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT Name FROM Lecturer WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if(rs.next()){
                    name = rs.getString("Name");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getNameLecture!!!");
        }finally{
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
        return name;
    }
    
    //getcodeStudent
    public String getStudentCode(int ID) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String Code = null ;
        
        try {
            conn = Util.getConnection();
            if(conn != null){
                String sql = "SELECT Code FROM Student WHERE ID =?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, ID);
                rs = stm.executeQuery();
                if(rs.next()){
                    Code = rs.getString("Code");
                }
            }
        } catch (Exception e) {
            System.err.println("Err at getStudentCode!!!");
        }finally{
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
        return Code;
    }
}
