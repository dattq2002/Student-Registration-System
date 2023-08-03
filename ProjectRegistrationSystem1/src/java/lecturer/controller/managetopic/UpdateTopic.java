/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturer.controller.managetopic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lecturer.DAO.TopicDAO;
import system.main.DTO.TopicAssign;
import system.main.DTO.UserAccountDTO;

/**
 *
 * @author Nam An
 */
public class UpdateTopic extends HttpServlet {

    private static final String ERROR = "DetailTopic";
    private static final String SUCCESS = "DetailTopic";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            int lecID = Integer.parseInt(request.getParameter("lecID"));
            String topicName = request.getParameter("topicName");
            String context = request.getParameter("context");
            String actor = request.getParameter("actor");
            String function = request.getParameter("function");
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO)session.getAttribute("LOGIN_USER");
            TopicDAO topDao = new TopicDAO();
            if (!(lecID == topDao.getLecturerID(loginUser.getEmail()))) {
                request.setAttribute("MESSAGE", "Do not update Topics of other Lecturer!!!");
                return;
            }
            if (topicName.length() < 0 || topicName.length() >= 100) {
                request.setAttribute("MESSAGE", "Topic Name has length between 0 - 100!!!");
                return;
            }
            if (context.length() < 0 || context.length() >= 250) {
                request.setAttribute("MESSAGE", "Context has length between 0 - 250!!!");
                return;
            }
            if (actor.length() < 0 || actor.length() >= 50) {
                request.setAttribute("MESSAGE", "Actor has length between 0 - 50!!!");
                return;
            }
            if (function.length() < 0 || function.length() >= 250) {
                request.setAttribute("MESSAGE", "Function has length between 0 - 250!!!");
                return;
            }
            boolean check = topDao.updateTopic(topicID, topicName, context, actor, function);
            if(check) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Update Successfully !!");
            }
        } catch (Exception e) {
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
