package system.maincontroller;

import admin.DAO.UserAccountDAO;
import DTO.UserAccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String LOGIN_ADMIN = "admin.jsp";
    private static final String LOGIN_MANAGER = "manager.jsp";
    private static final String LOGIN_USER = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            HttpSession session = request.getSession();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //proccess Account
            UserAccountDAO dao = new UserAccountDAO();
            UserAccountDTO user = dao.checkLogin(email, password);
            if (user != null && user.getStatus().equals("Active")) {
                session.setAttribute("LOGIN_USER", user);
                session.setAttribute("EMAIL", email);
                if ("ADMIN".equals(user.getRoleID())) {
                    url = LOGIN_ADMIN;
                } else if ("MNG".equals(user.getRoleID())) {
                    url = LOGIN_MANAGER;
                } else {
                    url = LOGIN_USER;
                }
            } else {
                session.setAttribute("LOGIN", "Incorret email or password!!!");
            }
        } catch (SQLException e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            response.sendRedirect(url);
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
