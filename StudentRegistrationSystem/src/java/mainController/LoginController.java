package mainController;

import DAO.UserAccountDAO;
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
            String userName = request.getParameter("email");
            String password = request.getParameter("password");
            UserAccountDAO dao = new UserAccountDAO();
            HttpSession session = request.getSession();
            UserAccountDTO user = dao.checkLogin(userName, password);
            if (user != null) {
                session.setAttribute("LOGIN_USER", user);
                String roleID = user.getRoleID();
                if ("ADMIN".equals(roleID)) {
                    url = LOGIN_ADMIN;
                } else if ("USER".equals(roleID)) {
                    url = LOGIN_USER;
                } else if ("MNG".equals(roleID)) {
                    url = LOGIN_MANAGER;
                } else {
                    session.setAttribute("ERROR_LOGIN", "Your role is not supported");
                }
            } else {
                session.setAttribute("ERROR_LOGIN", "InCorrect userName or Password");
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
