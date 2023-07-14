package admin.controller.managetopic;

import admin.DAO.TopicDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.TopicAssign;

public class AddTopicToClass extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AddTopicLink";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int subID = Integer.parseInt(request.getParameter("subID"));
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            TopicDAO topDao = new TopicDAO();
            boolean check = topDao.checkTopicAssign(subID, sesID, topicID);
            if(check){
                request.setAttribute("MESSAGE_ADD_TOPIC", "The Topic is exsit !! Can't add");
                url = SUCCESS;
            }else{
                TopicAssign topic = new TopicAssign(subID, sesID, topicID);
                boolean result = topDao.InsertTopicAssign(topic);
                if(result){
                    request.setAttribute("MESSAGE_ADD_TOPIC", "Topic added successfully !!");
                    url = SUCCESS;
                }else{
                    request.setAttribute("MESSAGE_ADD_TOPIC", "Topic is adding Fail !!");
                    url = SUCCESS;
                }
            }
        } catch (NumberFormatException | SQLException e) {
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
