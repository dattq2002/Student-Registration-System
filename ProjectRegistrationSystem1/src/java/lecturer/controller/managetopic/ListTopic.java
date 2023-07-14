package lecturer.controller.managetopic;

import lecturer.DAO.TopicDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.TopicAssign;

public class ListTopic extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "topicAssign.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int subID = Integer.parseInt(request.getParameter("subID"));
//            request.setAttribute("SUBJECT_ID", subID);
            int sesID = Integer.parseInt(request.getParameter("sesID"));
//            request.setAttribute("SEMESTER_ID", sesID);
            TopicDAO topDao = new TopicDAO();
            List<TopicAssign> list = topDao.getListTopicAssign(subID, sesID);
            if(list != null){
                if(!list.isEmpty()){
                    request.setAttribute("LIST_TOPIC", list);
                    url = SUCCESS;
                }
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
