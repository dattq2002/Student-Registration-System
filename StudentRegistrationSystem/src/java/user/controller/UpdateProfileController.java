package user.controller;

import DTO.StudentProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userDAO.ProfileStudentDAO;

public class UpdateProfileController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        try {
//            String name = request.getParameter("name");
//            String birthday = request.getParameter("birthday");
//            String email = request.getParameter("email");
//            StudentProfile st = new StudentProfile(name, birthday, email);
//            ProfileStudentDAO dao = new ProfileStudentDAO();
//            boolean check = dao.updateProfileStudent(st);
//            if(check){
//                request.setAttribute("MESS_UPDATE", "Updating successfully!!");
//            }else{
//                request.setAttribute("MESS_UPDATE", "Fail updating!!!");
//            }
//        } catch (Exception e) {
//            log("Err at UpdateProfileController: " +e.toString());
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
