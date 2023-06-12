package admin.controller;

import adminDAO.UserAccountDAO;
import DTO.UserAccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateAccountController extends HttpServlet {
   private static final String LOGOUT = "LogoutController";
    private static final String ERROR = "SearchAccountController";
    private static final String SUCCESS = "SearchAccountController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String email = request.getParameter("email");
            String fullName = request.getParameter("fullName");
            String role = request.getParameter("role");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            UserAccountDTO loginAccount = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            boolean check = true;
            if (password.length() > 15 || password.length() < 3) {
                check = false;
            }

            if (fullName.length() > 50 || fullName.length() < 5) {
                check = false;
            }

            if (check) {
                UserAccountDAO dao = new UserAccountDAO();
                UserAccountDTO account = new UserAccountDTO(email, password, role, fullName);
                boolean run = dao.updateAccount(account);
                if (run) {
                    if (email.equals(loginAccount.getEmail())) {
                        url = LOGOUT;
                    } else {
                        url = SUCCESS;
                    }
                }
            }

        } catch (SQLException e) {
            log("Error at UpdateController: " + e.toString());
        } finally {
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
