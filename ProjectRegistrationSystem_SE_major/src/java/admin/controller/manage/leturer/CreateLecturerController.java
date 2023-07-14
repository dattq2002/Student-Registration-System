package admin.controller.manage.leturer;

import admin.DAO.ProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.LectureProfile;

public class CreateLecturerController extends HttpServlet {
   
    private static final String ERROR = "addLecture.jsp";
    private static final String SUCCESS = "addLecture.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            //---------------
            ProfileDAO dao = new ProfileDAO();
            String regex = "LT-\\d+$";
            if (!id.matches(regex)) {
                request.setAttribute("MESSAGE_UPLOAD", "LectureID is wrong form !!!");
            }else if(!email.matches("^\\w+@fe\\.edu\\.vn$")){
                request.setAttribute("MESSAGE_UPLOAD", "Email is wrong form !!!");
            } else {
                String tmp[] = id.split("-");
                String code = tmp[0];
                int LectureID = Integer.parseInt(tmp[1]);
                //checkID
                boolean check = dao.checkLectureID(LectureID);
                if (check == false) {
                    request.setAttribute("MESSAGE_UPLOAD", "ID is duplicate !!");
                    return;
                }
                //checkEmail
                boolean check1 = dao.checkEmailExistInAccount(email);
                if (check1 == false) {
                    request.setAttribute("MESSAGE_UPLOAD", "Email is not exsited!!");
                    return;
                }
                boolean check2 = dao.checkLectureEmail(email);
                if(check2 == false){
                    request.setAttribute("MESSAGE_UPLOAD", "Email is exsited!!");
                    return;
                }
                LectureProfile lec = new LectureProfile(LectureID, code, name, email);
                boolean result = dao.AddLectureProfile(lec);
                if (result) {
                    request.setAttribute("SUCCESS", "Create Successfully !!");
                    url = SUCCESS;
                }
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        } finally {
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
