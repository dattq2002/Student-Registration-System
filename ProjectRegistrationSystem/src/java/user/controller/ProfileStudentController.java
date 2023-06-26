package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileStudentController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try {
//            HttpSession session = request.getSession();
//            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
//            ProfileStudentDAO dao = new ProfileStudentDAO();
//            StudentProfile student = dao.profileStudent(loginUser.getEmail());
//            if(student != null){
//                int ID = student.getID();
//                String code = student.getCode();
//                String Name = student.getName();
//                String birth = student.getBirthday();
//                String email = student.getEmail();
//                request.setAttribute("ID", ID);
//                request.setAttribute("Name", Name);
//                request.setAttribute("Code", code);
//                request.setAttribute("Birthday", birth);
//                request.setAttribute("Email", email);
//            }
//        } catch (Exception e) {
//            log("Err at ProfileStudentController: " + e.toString());
//        }finally{
//            request.getRequestDispatcher("viewProfileStudent.jsp").forward(request, response);
//        }
        
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
