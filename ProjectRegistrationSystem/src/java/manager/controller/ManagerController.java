package manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String LIST_TOPIC_MNG = "ListTopicMNGController";
    private static final String SEARCH_TOPIC_MNG = "SearchTopicMNGController";
    private static final String DETAIL_TOPIC_MNG = "DetailTopicMNGController";
    private static final String DELETE_TOPIC = "DeleteTopicController";
    private static final String UPDATE_TOPIC = "UpdateTopicController";
    private static final String CREATE_TOPIC = "CreateTopicController";

    private static final String LIST_APPLICATION_MNG = "ListApplicationMNGController";
    private static final String DETAIL_APPLICATION_MNG = "DetailApplicationMNGController";
    private static final String APPROVE_APPLICATION = "ApproveApplicationController";
    private static final String REFUSE_APPLICATION = "RefuseApplicationController";

    private static final String LIST_CLASS_MNG = "ListClassMNGController";
    private static final String LIST_STUDENT_MNG = "ListStudentMNGController";

    private static final String LIST_PRESENTATION_MNG = "ListPresentationMNGController";
    private static final String POST_PRESENTATION_MNG = "PostPresentationMNGController";

    private static final String PROFILE_MNG = "ProfileMNGController";
    private static final String UPDATE_PROFILE_MNG = "UpdateProfileMNGController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            String action1 = request.getParameter("action1");
            if ("Lecturer's topic".equals(action)) {
                url = LIST_TOPIC_MNG;
            } else if ("SearchTopic".equals(action)) {
                url = SEARCH_TOPIC_MNG;
            } else if ("DetailTopic".equals(action)) {
                url = DETAIL_TOPIC_MNG;
            } else if ("DeleteTopic".equals(action1)) {
                url = DELETE_TOPIC;
            } else if ("UpdateTopic".equals(action)) {
                url = UPDATE_TOPIC;
            } else if ("CreateTopic".equals(action)) {
                url = CREATE_TOPIC;
            } else if ("Lecturer's Application".equals(action)) {
                url = LIST_APPLICATION_MNG;
            } else if ("DetailApplication".equals(action)) {
                url = DETAIL_APPLICATION_MNG;
            } else if ("ApproveApplication".equals(action)) {
                url = APPROVE_APPLICATION;
            } else if ("RefuseApplication".equals(action1)) {
                url = REFUSE_APPLICATION;
            } else if ("Lecturer's Class".equals(action)) {
                url = LIST_CLASS_MNG;
            } else if ("Join Class".equals(action)) {
                url = LIST_STUDENT_MNG;
            } else if ("Lecturer's Presentation".equals(action)) {
                url = LIST_PRESENTATION_MNG;
            } else if ("Post Presentation".equals(action)) {
                url = POST_PRESENTATION_MNG;
            } else if ("Lecturer's Profile".equals(action)) {
                url = PROFILE_MNG;
            } else if ("Update Profile".equals(action)) {
                url = UPDATE_PROFILE_MNG;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
