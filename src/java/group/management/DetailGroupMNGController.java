/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.management;

import DAO.GroupMNGDAO;
import DTO.Group;
import DTO.GroupProject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nam An
 */
public class DetailGroupMNGController extends HttpServlet {
    private static final String ERROR = "detailGroupMNG.jsp";
    private static final String SUCCESS = "detailGroupMNG.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subjectID = Integer.parseInt(request.getParameter("subjectID"));
            String groupName = request.getParameter("groupName");
            request.setAttribute("COURSE_ID", courseID);
            request.setAttribute("SUBJECT_ID", subjectID);
            request.setAttribute("GROUP_NAME", groupName);
            request.setAttribute("GROUP_ID", groupID);
            GroupMNGDAO dao = new GroupMNGDAO();
            List<GroupProject> list = dao.listProjectGroupMNG(groupID);
            List<Group> list1 = dao.detailGroupMNG(courseID, subjectID, groupName);
            if (!list.isEmpty()) {
                request.setAttribute("PROJECT_GROUP", list);
                url = SUCCESS;
            }
            if (!list1.isEmpty()) {
                request.setAttribute("DETAIL_GROUP", list1);
                url = SUCCESS;
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
