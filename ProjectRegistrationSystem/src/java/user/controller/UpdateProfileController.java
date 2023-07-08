package user.controller;

import DTO.StudentProfile;
import DTO.UserAccountDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userDAO.ProfileStudentDAO;

public class UpdateProfileController extends HttpServlet {

    private static final String ERROR = "viewProfileStudent.jsp";
    private static final String SUCCESS = "ProfileStudentController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String addre = request.getParameter("address");
            String city = request.getParameter("City");
            String regex = "^\\w+@fpt\\.edu\\.vn$";
            String regexPhone = "^\\d{10,11}$";
            if (!email.matches(regex)) {
                request.setAttribute("MESS_UPDATE", "Email is wrong form !!!");
            }else if(!phone.matches(regexPhone)){
                request.setAttribute("MESS_UPDATE", "Phone is wrong form !!!");
            } else {
                StudentProfile st = new StudentProfile(name, birthday, phone,
                        gender, addre, city, email);
                UserAccountDTO acc = new UserAccountDTO(email, name);
                ProfileStudentDAO dao = new ProfileStudentDAO();
                boolean check = dao.updateProfileStudent(st, acc);
                if (check) {
                    request.setAttribute("MESS_UPDATE", "Updating successfully!!");
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESS_UPDATE", "Fail updating!!!");
                }
            }
        } catch (SQLException e) {
            log("Err at UpdateProfileController: " + e.toString());
        } finally {
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
