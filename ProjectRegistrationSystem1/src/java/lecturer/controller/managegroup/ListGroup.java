package lecturer.controller.managegroup;

import lecturer.DAO.GroupDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lecturer.DAO.TopicDAO;
import system.main.DTO.GroupProject;
import system.main.DTO.TopicAssign;
import system.main.DTO.UserAccountDTO;

public class ListGroup extends HttpServlet {

    private static final String ERROR = "group.jsp";
    private static final String SUCCESS = "group.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            GroupDAO grDao = new GroupDAO();
            List<GroupProject> list = grDao.getListGroup(courseID, subID);
            TopicDAO topDao = new TopicDAO();
            List<TopicAssign> list1 = topDao.getListTopicAssign(subID, sesID);
            if (list != null) {
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_GROUP", list);
                    session.setAttribute("LIST_TOPIC", list1);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "NO GROUP IN CLASS!!!");
                }
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
