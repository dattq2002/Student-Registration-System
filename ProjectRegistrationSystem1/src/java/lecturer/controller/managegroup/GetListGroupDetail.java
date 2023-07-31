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
import system.main.DTO.Group;
import system.main.DTO.GroupProject;
import system.main.DTO.TopicAssign;

public class GetListGroupDetail extends HttpServlet {

    private static final String ERROR = "groupDetail.jsp";
    private static final String SUCCESS = "groupDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int grID = Integer.parseInt(request.getParameter("grID"));
            String grName = request.getParameter("grName");
            session.setAttribute("GROUP_ID", grID);
            session.setAttribute("GROUP_NAME", grName);
            GroupDAO grDao = new GroupDAO();
            List<GroupProject> list = grDao.getListProject(grID);
            if (list != null) {
                if (!list.isEmpty()) {
                    request.setAttribute("PROJECT_GROUP", list);
                }
            }
            List<Group> list1 = grDao.getListGroupDetail(grID);
            if (list1 != null) {
                if (!list1.isEmpty()) {
                    request.setAttribute("GROUP_DETAIL", list1);
                    url = SUCCESS;
                }
            }
//            List<TopicAssign> list2 = grDao.getListTopicAssign(subID, sesID);
//            if (list2 != null) {
//                if (!list2.isEmpty()) {
//                    request.setAttribute("LIST_TOPIC", list2);
//                    url = SUCCESS;
//                }
//            }
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
