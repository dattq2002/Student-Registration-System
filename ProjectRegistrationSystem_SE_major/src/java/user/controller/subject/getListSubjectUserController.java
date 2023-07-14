package user.controller.subject;

import admin.DAO.ClassDAO;
import admin.DAO.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DTO.Semester;
import system.main.DTO.Subject;
import system.main.DTO.UserAccountDTO;
import user.DAO.ClassUserDAO;
import user.DAO.SubjectUserDAO;

public class getListSubjectUserController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "subjectUser.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            session.setAttribute("COURSE_ID", courseID);
//            int sesID = Integer.parseInt(request.getParameter("sesID"));
            SubjectUserDAO subDao = new SubjectUserDAO();
            List<Subject> listSub = subDao.getListSubject(courseID,loginUser.getEmail());
            if(listSub != null){
                if(!listSub.isEmpty()){
                    request.setAttribute("SUBJECT", listSub);
                    ClassUserDAO clDao = new ClassUserDAO();
                    List<system.main.DTO.Class> list1 = clDao.getInformationClass(courseID);
                    if(list1 != null){
                        if(!list1.isEmpty()){
                            request.setAttribute("COURSE_INFO", list1);
                            Semester ses = clDao.getNameSemester(sesID);
                            session.setAttribute("SEMESTER_NAME", ses.getSemesterName());
                            session.setAttribute("SEMESTER_YEAR", ses.getSemesterYear());
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log("Error at getListSubjectController: " + e.toString());
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
