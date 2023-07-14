package admin.controller.manage.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DAO.UserAccountDAO;
import system.main.DTO.UserAccountDTO;

public class UpdateAccountController extends HttpServlet {

    private static final String ERROR = "ListAccountController";
    private static final String SUCCESS = "ListAccountController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int id = Integer.parseInt(request.getParameter("id"));
            String Name = request.getParameter("name");
            String role = request.getParameter("role");
            String Status = request.getParameter("status");
            String email = request.getParameter("email");
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (email.equals(loginUser.getEmail()) || "ADMIN".equals(role)) {
                request.setAttribute("MESSAGE", "Account is login cannot update !!!");
            } else {
                UserAccountDAO dao = new UserAccountDAO();
                UserAccountDTO acc = new UserAccountDTO(role, Name, Status);
                boolean check = dao.updateAccount(id, Name, acc);
                if (check) {
                    request.setAttribute("MESSAGE", "Updating Successfully!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Updating fail !!!");
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
