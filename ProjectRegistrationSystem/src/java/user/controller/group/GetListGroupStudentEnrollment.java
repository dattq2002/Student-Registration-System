package user.controller.group;

import DTO.Group;
import DTO.UserAccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.DAO.ProfileStudentDAO;

public class GetListGroupStudentEnrollment extends HttpServlet {
    private static final String SUCCESS = "memberInGroup.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            int CourseID = Integer.parseInt(request.getParameter("CourseID"));
            int subID = Integer.parseInt(request.getParameter("SubID"));
            request.setAttribute("CourseID", CourseID);
            request.setAttribute("subID", subID);
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            ProfileStudentDAO dao = new ProfileStudentDAO();
            List<Group> list = dao.getListMember(loginUser.getEmail(), CourseID, subID);
            int count = list.size();
            if(list != null){
                if(!list.isEmpty()){
                    request.setAttribute("LIST_MEMBER", list);
                    request.setAttribute("AMOUNT", count);
                    url = SUCCESS;
                }
            }
        } catch (SQLException e) {
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
