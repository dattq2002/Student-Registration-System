package user.controller.topic;

import admin.DAO.ClassDAO;
import admin.DAO.TopicDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.ClassInformation;
import system.main.DTO.TopicAssign;

public class ListTopicUser extends HttpServlet {
   
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "topicAssignUser.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int subID = Integer.parseInt(request.getParameter("subID"));
//            request.setAttribute("SUBJECT_ID", subID);
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            int courseID = Integer.parseInt(request.getParameter("courseID"));
//            request.setAttribute("SEMESTER_ID", sesID);
            TopicDAO topDao = new TopicDAO();
            List<TopicAssign> list = topDao.getListTopicAssign(subID, sesID);
            if(list != null){
                if(!list.isEmpty()){
                    request.setAttribute("LIST_TOPIC", list);
                    url = SUCCESS;
                }else{
                    request.setAttribute("LIST_TOPIC", list);
                    request.setAttribute("MESSGAE_LIST_TOPIC", "Don't have Topic !!");
                    url = SUCCESS;
                }
            }
            ClassDAO clDao = new ClassDAO();
            ClassInformation clInfo = clDao.getInforClass(courseID, subID);
            if(clInfo != null){
                request.setAttribute("INFOR_CLASS", clInfo);
                String lecName = clDao.getLecName(courseID, subID);
                request.setAttribute("LECTURE_NAME", lecName);
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
