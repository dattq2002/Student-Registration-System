package user.controller.subject;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DTO.ClassInformation;
import system.main.DTO.UserAccountDTO;
import user.DAO.ClassUserDAO;

public class GetListSubjectDetailUser extends HttpServlet {
    private static final String ERROR = "getListSubjectUserController";
    private static final String SUCCESS = "subjectDetailUser.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subjectID = Integer.parseInt(request.getParameter("subID"));
            int sesID = Integer.parseInt(request.getParameter("sesID"));
//            request.setAttribute("SEMESTER_ID", sesID);
//            request.setAttribute("COURSE_ID", courseID);
            session.setAttribute("SUBJECT_ID", subjectID);
            ClassUserDAO clDao = new ClassUserDAO();
            int StudentID = clDao.getIDStudent(loginUser.getEmail());
            session.setAttribute("STUDENT_ID", StudentID);
            ClassInformation clInfo = clDao.getInforClass(courseID, subjectID);
            if(clInfo != null){
                request.setAttribute("INFOR_CLASS", clInfo);
                String lecName = clDao.getLecName(courseID, subjectID);
                request.setAttribute("LECTURE_NAME", lecName);
            }
            url = SUCCESS;
        } catch (NumberFormatException | SQLException e) {
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
