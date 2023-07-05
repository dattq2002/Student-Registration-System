package manager.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managerDAO.TopicMNGDAO;

public class CreateTopicController extends HttpServlet {
   
    private static final String ERROR = "createTopic.jsp";
    private static final String SUCCESS = "ListTopicMNGController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            String topicCode = request.getParameter("topicCode");
            String topicName = request.getParameter("topicName");
            String search = request.getParameter("searchE");
            String shortDescription = request.getParameter("shortDescription");
            String fullDescription = request.getParameter("fullDescription");
            String subject = request.getParameter("subject");
            String semester = request.getParameter("semester");
            //----------------------------------------
            String regex = "^[A-Za-z]+-\\d+$";
            String regex2 = "^(Spring|Fall|Summer)-\\d+$";
            boolean check = false;
            //----------------------------------------
            //check
            TopicMNGDAO dao = new TopicMNGDAO();
            if (dao.checkDuplicateTopicID(topicID) || topicID < 0) {
                request.setAttribute("MESSAGE", "Topic ID must be a positive number and cannot be duplicated!!!");
                return;
            }
            if (dao.checkDuplicateTopicCode(topicCode) || topicCode.length() < 2 || topicCode.length() > 10) {
                request.setAttribute("MESSAGE", "Topic Code must have a length in [2,10] and cannot be duplicated!!!");
                return;
            }
            if (topicName.length() < 0 || topicName.length() > 100) {
                request.setAttribute("MESSAGE", "Topic Name must have a length in [0,100]");
                return;
            }
            
            int lecturerID = dao.getLecturerID(search);
            
            if (shortDescription.length() < 0) {
                request.setAttribute("MESSAGE", "Short Description must be longer than 0!!!");
                return;
            }
            if (fullDescription.length() < 0) {
                request.setAttribute("MESSAGE", "Full Description must be longer than 0!!!");
                return;
            }
            int subjectID = 0;
            if (!subject.matches(regex)) {
                request.setAttribute("MESSAGE", "Subject ID not Match !!!");
                return;
            } else {
                String tmp[] = subject.split("-");
                String subjectCode = tmp[0];
                subjectID = Integer.parseInt(tmp[1]);
                boolean result = dao.checkDuplicateSubjectID(subjectID,subjectCode);
                if (!result) {
                    request.setAttribute("MESSAGE", "Subject ID could not be found!!!");
                    return;
                }
            }
            int semesterID = 0;
            if (!semester.matches(regex2)) {
                request.setAttribute("MESSAGE", "Semester ID is not match!!");
                return;
            } else {
                String tmp[] = semester.split("-");
                String semesterName = tmp[0];
                int semesterYear = Integer.parseInt(tmp[1]);
                boolean result = dao.checkDuplicateSemesterID(semesterYear, semesterName);
                if (!result) {
                    request.setAttribute("MESSAGE", "Semester ID could not be found!!!");
                    return;
                }
                semesterID = dao.getSemesterID(semesterYear, semesterName);
            }
            check = true;
            //create
            if (check) {
                boolean result = dao.createTopic(topicID, topicCode, topicName, lecturerID, shortDescription, fullDescription, subjectID, semesterID);
                if (result) {
                    request.setAttribute("MESSAGE", "Create Successfully !!");
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("MESSAGE", "Create Fail !!");
                url = ERROR;
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
