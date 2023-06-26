package admin.controller;

import adminDAO.ClassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCourseController extends HttpServlet {

    private static final String ERROR = "createCourse.jsp";
    private static final String SUCCESS = "ClassController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            int semesterNumber = Integer.parseInt(request.getParameter("semesterNumber"));
            String courseRoll = request.getParameter("courseRoll");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            String lecName = request.getParameter("lecName");
            String subRoll = request.getParameter("subRoll");
            String subName = request.getParameter("subName");
            int credit = Integer.parseInt(request.getParameter("Credit"));
            //----------------------------------------
            String regex = "^(NJS|SE)-\\d+$";
            String regex2 = "^[A-Za-z]+-\\d+$";
            boolean check = false;
            //----------------------------------------
            //check
            ClassDAO dao = new ClassDAO();
            if (!dao.checkSemester(semesterNumber)) {
                request.setAttribute("MESSAGE", "Semester is not exsit!!");
                return;
            }
            int courseID = 0;
            if (!courseRoll.matches(regex)) {
                request.setAttribute("MESSAGE", "Course not Match !!!");
                return;
            } else {
                String tmp[] = courseRoll.split("-");
                String name = tmp[0];
                courseID = Integer.parseInt(tmp[1]);
                boolean result = dao.checkCourse(courseID, name, semesterNumber,
                        startDate, endDate);
                if (result == false) {
                    dao.CreateNewCourse(courseID, name, semesterNumber,
                            startDate, endDate);
                    check = true;
                }
            }
            int lecID = dao.checkLectureName(lecName);
            if (lecID == 0) {
                request.setAttribute("MESSAGE", "Lecturer is not found!!!");
                return;
            }
            int subID = 0;
            if (!subRoll.matches(regex2)) {
                request.setAttribute("MESSAGE", "Sub Roll is not match!!");
                return;
            } else {
                String tmp[] = subRoll.split("-");
                String subCode = tmp[0];
                subID = Integer.parseInt(tmp[1]);
                boolean result = dao.checkSubject(subID, subCode, subName, credit);
                if (result == false) {
                    dao.createNewSubject(subID, subCode, subName, credit);
                    check = true;
                }
            }
            //create
            if (check) {
                boolean result = dao.CreateCourse(lecID, subID, courseID);
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
