package manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managerDAO.PresentationMNGDAO;

public class PostPresentationMNGController extends HttpServlet {
   
    private static final String ERROR = "postPresentation.jsp";
    private static final String SUCCESS = "postPresentation.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            String subject = request.getParameter("subject");
            String course = request.getParameter("course");
            String search = request.getParameter("searchE");
            int room = Integer.parseInt(request.getParameter("room"));
            String presentDate = request.getParameter("presentDate");
            String time = request.getParameter("time");
            String note = request.getParameter("note");
            
            
            //----------------------------------------     
            String regex = "^[A-Za-z]+-\\d+$";
            String regex2 = "^(NJS|SE)-\\d+$";
            boolean check = false;
            //----------------------------------------
            //check
            PresentationMNGDAO dao = new PresentationMNGDAO();
            
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
            
            int courseID = 0;
            if (!course.matches(regex2)) {
                request.setAttribute("MESSAGE", "Semester ID is not match!!");
                return;
            } else {
                String tmp[] = course.split("-");
                String courseName = tmp[0];
                int courseCode = Integer.parseInt(tmp[1]);
                boolean result = dao.checkDuplicateCourseID(courseCode, courseName);
                if (!result) {
                    request.setAttribute("MESSAGE", "Course ID could not be found!!!");
                    return;
                }
                courseID = dao.getCourseID(courseCode, courseName);
            }
            
            int lecturerID = dao.getLecturerID(search);
            
            if (room < 0) {
                request.setAttribute("MESSAGE", "Room must be a positive number");
                return;
            }
            
            if (note.length() < 0) {
                request.setAttribute("MESSAGE", "Note must be longer than 0!!!");
                return;
            }
            
            check = true;
            //create
            if (check) {
                boolean result = dao.postPresentation(subjectID, courseID, lecturerID, room, presentDate, time, note);
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
