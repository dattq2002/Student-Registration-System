package user.controller;

import DTO.Group;
import DTO.UserAccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userDAO.ProfileStudentDAO;

public class GroupEnrollmentController extends HttpServlet {
    private static final String ERROR = "joinGroup.jsp";
    private static final String SUCCESS = "GetListClassGroupController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int subID = Integer.parseInt(request.getParameter("subID"));
            int grID = Integer.parseInt(request.getParameter("groupID"));
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            ProfileStudentDAO dao = new ProfileStudentDAO();
            int StudentID = dao.findStudentID(loginUser.getEmail());
            boolean check = dao.GroupEnrollment(subID, grID, StudentID);
            if(check){
                request.setAttribute("SUCCESS", "Join Group successfully!!");
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
