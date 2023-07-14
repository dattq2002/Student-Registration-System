package system.main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LIST_SUBJECT = "getListSubjectController";
    private static final String SUBJECT_DETAIL = "GetListSubjectDetail";
    private static final String GROUP_DETAIL = "GetListGroupDetail";
    private static final String ADD_STUDENT = "AddStudentClassLink";
    private static final String DELETE_STUDENT_CLASS = "DeleteStudentInClass";
    private static final String ADD_TOPIC = "AddTopicLink";
    private static final String ADD_TOPIC_TO_SUBJECT = "AddTopicToClass";
    private static final String DELETE_TOPIC_IN_SUBJECT = "DeleteTopicInClass";
    private static final String CREATE_COUSRSE = "CreateClassController";
    private static final String ADD_SUBJECT_TO_CLASS = "AddSubjectClassController";
    private static final String ADD_SUBJECT_CLASS = "AssignSubjectClassController";
    private static final String SHOW_LIST_STUDENT = "ListStudentInformation";
    private static final String ADD_STUDENT_INFO = "CreateStudentController";
    private static final String ADD_LECTUERE_INFO = "CreateLecturerController";
    private static final String SHOW_LIST_LECTUERE = "ListLectureInformation";
    private static final String UPDATE_PROFILE = "UpdateInformationProfile";
    private static final String SEARCH_LECTURE = "SearchLectureInformation";
    private static final String SEARCH_STUDENT = "SearchStudentInformation";

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
            } else if ("AddStudentToClass".equals(action)) {
                url = ADD_STUDENT;
            } else if ("DeleteStudentClass".equals(action)) {
                url = DELETE_STUDENT_CLASS;
            } else if ("AddTopic".equals(action)) {
                url = ADD_TOPIC;
            } else if ("AddTopictoSubject".equals(action)) {
                url = ADD_TOPIC_TO_SUBJECT;
            } else if ("DeleteTopic".equals(action)) {
                url = DELETE_TOPIC_IN_SUBJECT;
            } else if ("Create Course".equals(action)) {
                url = CREATE_COUSRSE;
            } else if ("AddSubjectClass".equals(action)) {
                url = ADD_SUBJECT_TO_CLASS;
            } else if ("AssignSubjectClass".equals(action)) {
                url = ADD_SUBJECT_CLASS;
            } else if ("viewStudent".equals(action)) {
                url = SHOW_LIST_STUDENT;
            } else if ("AddStudent".equals(action)) {
                url = ADD_STUDENT_INFO;
            } else if ("AddLecture".equals(action)) {
                url = ADD_LECTUERE_INFO;
            } else if ("viewLecture".equals(action)) {
                url = SHOW_LIST_LECTUERE;
            } else if ("UpdateLecture".equals(action)) {
                url = UPDATE_PROFILE;
            }else if("UpdateStudent".equals(action)){
                url = UPDATE_PROFILE;
            }else if("SearchLecture".equals(action)){
                url = SEARCH_LECTURE; 
            }else if("SearchStudent".equals(action)){
                url = SEARCH_STUDENT;     
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
