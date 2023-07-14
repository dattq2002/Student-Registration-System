package admin.controller.manageclass;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStudentInClass extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "GetListStudentInClass";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int StuID = Integer.parseInt(request.getParameter("StuID"));
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            ClassDAO clDao = new ClassDAO();
            boolean check = clDao.DeleteStudentClass(courseID, subID, StuID);
            if (check) {
                request.setAttribute("MESSAGE_STUDENT_CLASS", "Delete Successfully !!");
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE_STUDENT_CLASS", "Delete Fail !!");
                url = SUCCESS;
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
