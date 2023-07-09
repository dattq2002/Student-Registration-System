package user.controller.group;

import DTO.GroupProject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.DAO.GroupStudentDAO;

public class UpdateProjectGroupController extends HttpServlet {
    private static final String ERROR = "GetListGroupStudentEnrollment";
    private static final String SUCCESS = "GetListGroupStudentEnrollment";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int CourseID = Integer.parseInt(request.getParameter("CourseID"));
            int SubID = Integer.parseInt(request.getParameter("SubID"));
            request.setAttribute("COURSEID", CourseID);
            request.setAttribute("SUBID", SubID);
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            String context = request.getParameter("context");
            String actor = request.getParameter("actor");
            String func = request.getParameter("func");
            String noteproject = request.getParameter("noteproject");
            int prjID = Integer.parseInt(request.getParameter("prjID"));
            GroupStudentDAO dao = new GroupStudentDAO();
            GroupProject gp = new GroupProject(context, actor, func, 
                    noteproject, prjID, topicID);
            boolean check = dao.UpdateProject(gp);
            if(check){
                request.setAttribute("MESSAGE_PRJ", 
                        "Update Project Information successfully !!");
                url = SUCCESS;
            }
        } catch (Exception e) {
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
