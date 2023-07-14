package admin.controller.manageclass;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.Class;

public class CreateClassController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "createClass.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            String semester = request.getParameter("semester").trim();
            int courseRoll = Integer.parseInt(request.getParameter("courseRoll"));
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String courseName = request.getParameter("courseName");
            String regex = "^(NJS|SE)-\\d+$";
            if(!courseName.matches(regex)){
                request.setAttribute("MESSAGE", "Course not Match !!!");
                return;
            }
            String tmp[] = courseName.split("-");
            String Name = tmp[0];
            int courseCode = Integer.parseInt(tmp[1]);
            if(courseCode == 0){
                request.setAttribute("MESSAGE", "CourseName not Match !!!");
                return;
            }
            String sesName = null;
            int sesYear = 0;
            if(semester.substring(0,6).equalsIgnoreCase("Spring") ||
                    semester.substring(0,6).equalsIgnoreCase("Summer")){
                sesName = semester.substring(0,6);
                sesYear = Integer.parseInt(semester.substring(7));
                if(sesYear == 23) sesYear = 2023;
                else if(sesYear == 22) sesYear = 2022;
            }else if(semester.substring(0,4).equalsIgnoreCase("Fall")){
                sesName = semester.substring(0,4);
                sesYear = Integer.parseInt(semester.substring(5));
                if(sesYear == 23) sesYear = 2023;
                else if(sesYear == 22) sesYear = 2022;
            }else{
                request.setAttribute("MESSAGE", "Semester not Match !!!");
                return;
            }
            ClassDAO dao = new ClassDAO();
            int sesID = dao.getIDSemester(sesName, sesYear);
            if(sesID == 0){
                request.setAttribute("MESSAGE", "Semester is not Found !!!");
                return; 
            }
            boolean check = dao.checkCourse(courseRoll);
            if(check == false){
                request.setAttribute("MESSAGE", "courseRoll was exsit !!!");
                return;
            }
            Class cl = new Class(courseRoll, Name, courseCode, sesID, startDate, endDate);
            boolean result = dao.InsertCourse(cl);
            if(result){
                request.setAttribute("MESSAGE", "Create successfully !!!");
            }else{
                request.setAttribute("MESSAGE", "Create Fail !!!");
            }
        } catch (NumberFormatException | SQLException e) {
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
