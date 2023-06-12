package admin.controller;

import adminDAO.StudentDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateStudentProfile extends HttpServlet {

    private static final String SUCCESS = "SearchStudentProfile";
    private static final String ERROR = "viewStudent.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int id = Integer.parseInt(request.getParameter("ID"));
            String name = request.getParameter("Name");
            String code = request.getParameter("code").toUpperCase();
            HttpSession session = request.getSession();
            StudentDAO dao = new StudentDAO();
            if (code.length() < 2 || code.contains(" ")) {
                session.setAttribute("ERROR_DU", "Your code must be more than 2 or cannot contain space!!!");
            }else{
                boolean check = dao.UpdateStudent(id,name, code);
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("ERROR_DU", "Update successfully!!");
                    Thread.sleep(2000);
                }else{
                    request.setAttribute("ERROR_DU", "Cannot Update profile!!! May be some Problem in database");
                    //fail may be database connect with other table, check table and try again
                }
            }
        } catch (InterruptedException | NumberFormatException | SQLException e) {
            log("Error at UpdateStudentProfile: " + e.toString());
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
