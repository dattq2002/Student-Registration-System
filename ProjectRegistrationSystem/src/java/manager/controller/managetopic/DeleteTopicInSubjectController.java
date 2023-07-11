package manager.controller.managetopic;

import DTO.UserAccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managerDAO.TopicMNGDAO;

public class DeleteTopicInSubjectController extends HttpServlet {
   
    private static final String ERROR = "TopicInSubjectMNGController";
    private static final String SUCCESS = "TopicInSubjectMNGController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));;
            int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO)session.getAttribute("LOGIN_USER");
            TopicMNGDAO dao = new TopicMNGDAO();
            if (!(lecturerID == dao.getLecturerID(loginUser.getEmail()))) {
                request.setAttribute("MESSAGE", "Do not delete Topics of other Lecturer!!!");
                return;
            }
            boolean check = dao.deleteTopicAssign(topicID, subjectID);
            if(check) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Delete Successfully !!");
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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
