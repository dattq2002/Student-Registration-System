package user.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userDAO.ProfileStudentDAO;

public class CreateGroupController extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //getParameter
            String sub = request.getParameter("sub");
            String couse = request.getParameter("course");
            String group = request.getParameter("group");
            String topic = request.getParameter("topic");
            //------------------------------
            //check form
            String regexCourse = "^(NJS|SE)-\\d+$";
            String regex = "^[A-Za-z]+-\\d+$";
            String regexGroup = "^Team [1-9][0-9]*$";
            ProfileStudentDAO dao = new ProfileStudentDAO();
            //-------------------------------
            if(!couse.matches(regexCourse)){
                request.setAttribute("WRONG_FORM", "Course is wrong form!!!");
                return;
            }else if(!topic.matches(regex)){
                request.setAttribute("WRONG_FORM", "Topic is wrong form!!!");
                return;
            }else if(!group.matches(regexGroup)){
                request.setAttribute("WRONG_FORM", "Group is wrong form!!!");
                return;
            }else{
                String tmpTopic[] = topic.split("-");
                int TopicID = Integer.parseInt(tmpTopic[1]);
                String tmpCourse[] = couse.split("-");
                int CourseCode = Integer.parseInt(tmpCourse[1]);
                int CourseID = dao.checkCourse(CourseCode);
                if(CourseID == 0){
                    request.setAttribute("WRONG_FORM", "Your course is not exsit !!!");
                }else{
                    boolean check = dao.checkGroup(group);
                    if(check){
                        boolean result = dao.CreateGroup(CourseID, group);
                        if(result){
                            request.setAttribute("SUCCESS", "Create successfully !!");
                        }
                    }else{
                        request.setAttribute("WRONG_FORM", "Your group is exsit !!!");
                    }
                }  
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("GetListGroupController").forward(request, response);
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
