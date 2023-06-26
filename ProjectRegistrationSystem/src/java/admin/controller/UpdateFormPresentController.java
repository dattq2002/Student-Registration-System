package admin.controller;

import DTO.Application;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userDAO.ApplicationDAO;

public class UpdateFormPresentController extends HttpServlet {
    private static final String SUCCESS = "ListFormController";
    private static final String SUCCESS1 = "SearchFormController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String room = request.getParameter("room");
            String time = request.getParameter("time");
            String preDate = request.getParameter("preDate");
            String creDate = request.getParameter("creDate");
            String publish = request.getParameter("publish");
            ApplicationDAO dao = new ApplicationDAO();
            Application app = new Application(creDate, room, preDate, time, publish);
            boolean check = dao.updateForm(app);
            if(check){
                request.setAttribute("MESSAGE", "Update Successfully !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("ListFormController").forward(request, response);
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
