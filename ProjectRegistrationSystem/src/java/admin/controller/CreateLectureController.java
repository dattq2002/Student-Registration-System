package admin.controller;

import DTO.LectureProfile;
import adminDAO.ProfileDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateLectureController extends HttpServlet {
    private static final String ERROR = "addLecture.jsp";
    private static final String SUCCESS = "addLecture.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            //---------------
            ProfileDAO dao = new ProfileDAO();
            String tmp[] = id.split("-");
            String code = tmp[0];
            int LectureID = Integer.parseInt(tmp[1]);
            //checkID
            boolean check = dao.checkLectureID(LectureID);
            if(check == false){
                request.setAttribute("MESSAGE_UPLOAD", "ID is duplicate !!");
                return;
            }
            //checkEmail
            boolean check1 = dao.checkEmailExistInAccount(email);
            if(check1 == false){
                request.setAttribute("MESSAGE_UPLOAD", "Email is not exsited!!");
                return;
            }
            LectureProfile lec = new LectureProfile(LectureID, code, name, email);
            boolean result = dao.AddLectureProfile(lec);
            if(result){
                request.setAttribute("SUCCESS", "Create Successfully !!");
                url = SUCCESS;
            }
        } catch (NumberFormatException e) {
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateLectureController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CreateLectureController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
