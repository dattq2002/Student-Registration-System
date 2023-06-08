package adminController;

import DAO.StudentDAO;
import DTO.StudentProfile;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddStudentProfile extends HttpServlet {

    private static final String SUCCESS = "SearchStudentProfile";
    private static final String ERROR = "addStudent.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int id = Integer.parseInt(request.getParameter("studentid"));
            String code = request.getParameter("studentcode");
            String name = request.getParameter("studentname");
            String birthday = request.getParameter("birthday");
            String email = request.getParameter("email");

            String regex = "^(?:19|20)\\d\\d-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-30|(?:0[13578]|1[02])-31)$";
            StudentDAO dao = new StudentDAO();
            StudentProfile student = new StudentProfile(id, code, name, birthday, email);
            HttpSession session = request.getSession();
            if (code.length() < 2 || code.contains(" ")) {
                session.setAttribute("MESSAGE1", "Your code must be more than 2 or cannot contain space!!!");
            } else if (email.contains(" ") || !email.contains("@fpt.edu.vn")) {
                session.setAttribute("MESSAGE1", "Your email must be contain '@fe.edu.vn' or cannot contain space!!!");
            } else if (!birthday.matches(regex)) {
                session.setAttribute("MESSAGE1", "Wrong format!! Try again");
            } else {
                boolean check = dao.AddStudent(student);
                if (check) {
                    url = SUCCESS;
                    Thread.sleep(2000);
                } else {
                    session.setAttribute("MESSAGE1", "Cannot create because duplicate ID");
                }
            }
        } catch (InterruptedException | NumberFormatException | SQLException e) {
            log("Error at AddStudentProfile: " + e.toString());
        } finally {
            response.sendRedirect(url);
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
