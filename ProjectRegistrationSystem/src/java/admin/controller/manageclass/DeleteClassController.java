package admin.controller.manageclass;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteClassController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String lecName = request.getParameter("lecName");
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            ClassDAO dao = new ClassDAO();
            int lecID = dao.checkLectureName(lecName);
            boolean check = dao.DeleteClass(id,lecID, courseID);
            if(check){
                request.setAttribute("MESSAGE", "Delete Successfully !!");
            }else{
                request.setAttribute("MESSAGE", "Update Fail !!");
            }
        } catch (NumberFormatException | SQLException e) {
        }finally{
            request.getRequestDispatcher("ClassController").forward(request, response);
        }
//    chỉnh status bên course sang SubjectInClass
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
