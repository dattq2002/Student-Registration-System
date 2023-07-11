package system.maincontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";

    private static final String PROFILE_MNG = "ProfileMNGController";
    private static final String UPDATE_PROFILE_MNG = "UpdateProfileMNGController";

    private static final String LIST_CLASS_MNG = "ListClassMNGController";
    
    private static final String CLASS_GROUP_MNG = "ClassGroupMNGController";
    private static final String LIST_GROUP_MNG = "ListGroupMNGController";
    private static final String LIST_FALSE_GROUP_MNG = "ListFalseGroupMNGController";
    private static final String APPROVE_GROUP_MNG = "ApproveGroupMNGController";
    private static final String DETAIL_GROUP_MNG = "DetailGroupMNGController";
    private static final String UPDATE_MEMBER_MNG = "UpdateMemberMNGController";
    private static final String DELETE_MEMBER_MNG = "DeleteMemberMNGController";
    private static final String UPDATE_TOPIC_GROUP_MNG = "UpdateTopicGroupMNGController";
    private static final String DELETE_GROUP_MNG = "DeleteGroupMNGController";

    private static final String LIST_SUBJECT_TOPIC_MNG = "ListSubjectTopicMNGController";
    private static final String LIST_TOPIC_MNG = "ListTopicMNGController";
    private static final String SEARCH_TOPIC_MNG = "SearchTopicMNGController";
    private static final String DETAIL_TOPIC_MNG = "DetailTopicMNGController";
    private static final String DELETE_TOPIC = "DeleteTopicController";
    private static final String UPDATE_TOPIC = "UpdateTopicController";
    private static final String CREATE_TOPIC = "CreateTopicController";
    private static final String ASSIGN_TOPIC = "AssignTopicController";
    private static final String DELETE_TOPIC_IN_SUBJECT = "DeleteTopicInSubjectController";

    private static final String LIST_APPLICATION_MNG = "ListApplicationMNGController";
    private static final String DETAIL_APPLICATION_MNG = "DetailApplicationMNGController";
    private static final String APPROVE_APPLICATION = "ApproveApplicationController";
    private static final String PROCESSED_APPLICATION_MNG = "ProcessedApplicationMNGController";

    private static final String LIST_PRESENTATION_MNG = "ListPresentationMNGController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("LecturerTopic".equals(action)) {
                url = LIST_TOPIC_MNG;
            } else if ("SearchTopic".equals(action)) {
                url = SEARCH_TOPIC_MNG;
            } else if ("DetailTopic".equals(action)) {
                url = DETAIL_TOPIC_MNG;
            } else if ("DeleteTopic".equals(action)) {
                url = DELETE_TOPIC;
            } else if ("UpdateTopic".equals(action)) {
                url = UPDATE_TOPIC;
            } else if ("CreateTopic".equals(action)) {
                url = CREATE_TOPIC;
            } else if ("AssignTopic".equals(action)) {
                url = ASSIGN_TOPIC;
            } else if ("LecturerApplication".equals(action)) {
                url = LIST_APPLICATION_MNG;
            } else if ("ProcessedApplication".equals(action)) {
                url = PROCESSED_APPLICATION_MNG;
            } else if ("DetailApplication".equals(action)) {
                url = DETAIL_APPLICATION_MNG;
            } else if ("ApproveApplication".equals(action)) {
                url = APPROVE_APPLICATION;
            } else if ("LecturerClass".equals(action)) {
                url = LIST_CLASS_MNG;
            } else if ("LecturerGroup".equals(action)) {
                url = LIST_GROUP_MNG;
            } else if ("LecturerFalseGroup".equals(action)) {
                url = LIST_FALSE_GROUP_MNG;
            } else if ("ApproveGroup".equals(action)) {
                url = APPROVE_GROUP_MNG;
            } else if ("DetailGroup".equals(action)) {
                url = DETAIL_GROUP_MNG;
            } else if ("UpdateMember".equals(action)) {
                url = UPDATE_MEMBER_MNG;
            } else if ("DeleteMember".equals(action)) {
                url = DELETE_MEMBER_MNG;
            } else if ("UpdateTopicProject".equals(action)) {
                url = UPDATE_TOPIC_GROUP_MNG;
            } else if ("LecturerPresentation".equals(action)) {
                url = LIST_PRESENTATION_MNG;
            } else if ("LecturerProfile".equals(action)) {
                url = PROFILE_MNG;
            } else if ("Update Profile".equals(action)) {
                url = UPDATE_PROFILE_MNG;
            }else if("DeleteGroup".equals(action)){
                url = DELETE_GROUP_MNG;
            } else if ("LecturerClassGroup".equals(action)) {
                url = CLASS_GROUP_MNG;  
            } else if ("LecturerListSubjectTopic".equals(action)) {
                url = LIST_SUBJECT_TOPIC_MNG; 
            } else if ("DeleteTopicInSubject".equals(action)) {
                url = DELETE_TOPIC_IN_SUBJECT;    
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
