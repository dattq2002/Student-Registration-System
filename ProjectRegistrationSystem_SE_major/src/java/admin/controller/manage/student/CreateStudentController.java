package admin.controller.manage.student;

import admin.DAO.ProfileDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.StudentProfile;

public class CreateStudentController extends HttpServlet {

    private static final String ERROR = "addStudent.jsp";
    private static final String SUCCESS = "addStudent.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String major = request.getParameter("major");
            //---------------
            ProfileDAO dao = new ProfileDAO();
            String regex = "SE-\\d+$";
            if (!id.matches(regex)) {
                request.setAttribute("MESSAGE_UPLOAD", "ID is wrong form !!!");
            }else if(!email.matches("^\\w+@fpt\\.edu\\.vn$")){
                request.setAttribute("MESSAGE_UPLOAD", "Email is wrong form !!!");
            } else {
                String tmp[] = id.split("-");
                String code = tmp[0];
                int StudentID = Integer.parseInt(tmp[1]);
                //checkID
                boolean check = dao.checkStudentID(StudentID);
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
                boolean check2 = dao.checkStudentEmail(email);
                if(check2 == false){
                    request.setAttribute("MESSAGE_UPLOAD", "Email is exsited!!");
                    return;
                }
                StudentProfile stu = new StudentProfile(major, StudentID, code, name, email);
                boolean result = dao.AddStudentProfile(stu);
                if (result) {
                    request.setAttribute("SUCCESS", "Create Successfully !!");
                    url = SUCCESS;
                }
            }
        } catch (NumberFormatException | SQLException e) {
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
