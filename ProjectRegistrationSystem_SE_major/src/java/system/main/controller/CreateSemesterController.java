package system.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DAO.SemesterDAO;
import system.main.DTO.Semester;

public class CreateSemesterController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "createSemester.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int year = Integer.parseInt(request.getParameter("year"));
            String name = request.getParameter("name");
            String Status = request.getParameter("Status");
            String StartDate = request.getParameter("StartDate");
            String EndDate = request.getParameter("EndDate");
            Semester ses = new Semester(id, name, year, Status, StartDate, EndDate);
            SemesterDAO dao = new SemesterDAO();
            boolean check = dao.InsertSemester(ses);
            if(check){
                request.setAttribute("MESSAGE", "Create successfully !!");
                url = SUCCESS;
            }else{
                request.setAttribute("MESSAGE", "Create Fail !!");
                url = SUCCESS;
            }
            
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
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
