package admin.controller;

import adminDAO.ProfileDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInformationProfile extends HttpServlet {

    private static final String SUCCESS = "ListStudentInformation";
    private static final String SUCCESS1 = "ListLectureInformation";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "admin.jsp";
        try {
            int id = Integer.parseInt(request.getParameter("ID"));
            String name = request.getParameter("Name");
            String code = request.getParameter("code").toUpperCase();
            ProfileDAO dao = new ProfileDAO();
            boolean check = dao.UpdateProfile(id, code, name);
            if (check && code.equals("LT")) {
                url = SUCCESS1;
                request.setAttribute("MESSAGE_LECTURE", "Update successfully!!");
                Thread.sleep(1000);
            } else {
                url = SUCCESS;
                request.setAttribute("MESSAGE_STUDENT", "Update successfully!!");
                Thread.sleep(1000);
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
