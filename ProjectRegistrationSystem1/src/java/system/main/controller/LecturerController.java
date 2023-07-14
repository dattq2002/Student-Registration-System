package system.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LecturerController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LIST_SUBJECT = "getListSubjectController";
    private static final String SUBJECT_DETAIL = "GetListSubjectDetail";
    private static final String GROUP_DETAIL = "GetListGroupDetail";
    private static final String UPDATE_PROJECT_TOPIC = "UpdateProjectTopic";
    private static final String UPDATE_GROUP_MEMBER = "UpdateGroupMember";
    private static final String DELETE_GROUP_MEMBER = "DeleteGroupMember";
    private static final String DELETE_GROUP = "DeleteGroup";
    private static final String SOURCE_TOPIC = "SourceTopic";
    private static final String UPDATE_TOPIC = "UpdateTopic";
    private static final String CREATE_TOPIC = "CreateTopic";
    private static final String LECTURER_PROFILE = "Profile";
    private static final String UPDATE_PROFILE = "UpdateProfile";
    private static final String LECTURER_PRESENTATION = "LecturerPresentation";
    private static final String LECTURER_PROCESSING_APPLICATION = "ProcessingApplication";
    private static final String LECTURER_DETAIL_APPLICATION = "DetailApplication";
    private static final String APPROVE_APPLICATION = "ApproveApplication";
    private static final String LECTURER_PROCESSED_APPLICATION = "ProcessedApplication";
    private static final String CREATE_GROUP = "CreateGroup";
    private static final String ADD_GROUP = "AddGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("listSubject".equals(action)) {
                url = LIST_SUBJECT;
            } else if ("ViewDetail".equals(action)) {
                url = SUBJECT_DETAIL;
            } else if ("ViewGroupDetail".equals(action)) {
                url = GROUP_DETAIL;
            } else if ("UpdateProjectTopic".equals(action)) {
                url = UPDATE_PROJECT_TOPIC;
            } else if ("UpdateGroupMember".equals(action)) {
                url = UPDATE_GROUP_MEMBER;
            } else if ("DeleteGroupMember".equals(action)) {
                url = DELETE_GROUP_MEMBER;
            } else if ("DeleteGroup".equals(action)) {
                url = DELETE_GROUP;
            } else if ("SourceTopic".equals(action)) {
                url = SOURCE_TOPIC;
            } else if ("UpdateTopic".equals(action)) {
                url = UPDATE_TOPIC;
            } else if ("CreateTopic".equals(action)) {
                url = CREATE_TOPIC;
            } else if ("LecturerProfile".equals(action)) {
                url = LECTURER_PROFILE;
            } else if ("UpdateProfile".equals(action)) {
                url = UPDATE_PROFILE;
            } else if ("LecturerPresentation".equals(action)) {
                url = LECTURER_PRESENTATION;
            } else if ("ProcessingApplication".equals(action)) {
                url = LECTURER_PROCESSING_APPLICATION;
            } else if ("DetailApplication".equals(action)) {
                url = LECTURER_DETAIL_APPLICATION;
            } else if ("ApproveApplication".equals(action)) {
                url = APPROVE_APPLICATION;
            } else if ("ProcessedApplication".equals(action)) {
                url = LECTURER_PROCESSED_APPLICATION;
            } else if ("CreateGroup".equals(action)) {
                url = CREATE_GROUP;
            } else if ("addGroup".equals(action)) {
                url = ADD_GROUP;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_FUNC", "function is not available");
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
