package mainController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH_STUDENT = "SearchStudentProfile";
    private static final String ADD_STUDENT = "AddStudentProfile";
    private static final String DELETE_STUDENT = "DeleteStudentProfile";
    private static final String UPDATE_STUDENT = "UpdateStudentProfile";
    private static final String SEARCH_LECTURE = "SearchLectureProfile";
    private static final String ADD_LECTURE = "AddLectureProfile";
    private static final String DELETE_LECTURE = "DeleteLectureProfile";
    private static final String UPDATE_LECTURE = "UpdateLectureProfile";
    private static final String RESET_PASSWORD = "ResetPasswordController";
    private static final String LOGOUT = "LogoutController";
    private static final String LIST_ACCOUNT = "ListAccountController";
    private static final String SEARCH_ACCOUNT = "SearchAccountController";
    private static final String CREATE_ACCOUNT = "CreateAccountController";
    private static final String DELETE_ACCOUNT = "DeleteAccountController";
    private static final String UPDATE_ACCOUNT = "UpdateAccountController";
    private static final String SEND_APPLICATION = "SendApplicationController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("SearchStudent".equals(action)) {
                url = SEARCH_STUDENT;
            } else if ("AddProfile".equals(action)) {
                url = ADD_STUDENT;
            } else if ("DeleteStudent".equals(action)) {
                url = DELETE_STUDENT;
            } else if ("UpdateStudent".equals(action)) {
                url = UPDATE_STUDENT;
            } else if ("SearchLecture".equals(action)) {
                url = SEARCH_LECTURE;
            } else if ("CreateLecture".equals(action)) {
                url = ADD_LECTURE;
            } else if ("DeleteLecture".equals(action)) {
                url = DELETE_LECTURE;
            } else if ("UpdateLecture".equals(action)) {
                url = UPDATE_LECTURE;
            } else if ("Reset".equals(action)) {
                url = RESET_PASSWORD;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Account".equals(action)) {
                url = LIST_ACCOUNT;
            } else if ("SearchAccount".equals(action)) {
                url = SEARCH_ACCOUNT;
            } else if ("CreateAccount".equals(action)) {
                url = CREATE_ACCOUNT;
            } else if ("DeleteAccount".equals(action)) {
                url = DELETE_ACCOUNT;
            } else if ("UpdateAccount".equals(action)) {
                url = UPDATE_ACCOUNT;
            } else if ("Send".equals(action)) {
                url = SEND_APPLICATION;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_FUNC", "function is not available");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
