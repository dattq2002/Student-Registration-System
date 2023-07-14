package lecturer.controller.manageclass;

import lecturer.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DTO.StudentProfile;
import system.main.DTO.UserAccountDTO;

public class GetListStudentInClass extends HttpServlet {
    private static final String ERROR = "listStudentInClass.jsp";
    private static final String SUCCESS = "listStudentInClass.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO)session.getAttribute("LOGIN_USER"); 
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            ClassDAO clDao = new ClassDAO();
            String lecName = clDao.getLecName(courseID, subID);
            
            List<StudentProfile> list = clDao.getListStudentInClass(courseID, subID, loginUser.getEmail());
            if(list != null){
                if(!list.isEmpty()){
                    request.setAttribute("LECTURE_NAME", lecName);
                    request.setAttribute("LIST_STUDENT_CLASS", list);
                    url = SUCCESS;
                } else {
                request.setAttribute("MESSAGE", "NO STUDENT IN CLASS!!!");
            }
            } 
        } catch (NumberFormatException | SQLException e) {
            log("Error at GetListStudentInClass: " + e.toString());
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
