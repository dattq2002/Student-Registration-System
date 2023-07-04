package admin.controller;

import adminDAO.ProfileDAO;
import DTO.StudentProfile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListStudentInformation extends HttpServlet {

    private static final String STUDENT_SP2022 = "viewStudentSP2022.jsp";
    private static final String STUDENT_FA2022 = "viewStudentFA2022.jsp";
    private static final String STUDENT_SP2023 = "viewStudentSP2023.jsp";
    private static final String SUCCESS = "viewStudent.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //Proccess
            ProfileDAO dao = new ProfileDAO();
            String id = request.getParameter("idSemester");
            if (id != null) {
                if (id.equals("11111")) {
                    List<StudentProfile> list = dao.getListStudentSpring2022();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11111", id);
                            request.setAttribute("LIST_ST_SPRING2022", list);
                            url = STUDENT_SP2022;
                        } else {
                            request.setAttribute("ERROR_CODE", "No student in Class");
                        }
                    }
                } else if (id.equals("11112")) {
                    List<StudentProfile> list = dao.getListStudentFall2022();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11112", id);
                            request.setAttribute("LIST_ST_FALL022", list);
                            url = STUDENT_FA2022;
                        } else {
                            request.setAttribute("ERROR_CODE", "No student in Class");
                        }
                    }
                } else if (id.equals("11113")) {
                    List<StudentProfile> list = dao.getListStudentSpring2023();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11113", id);
                            request.setAttribute("LIST_ST_SPRING2023", list);
                            url = STUDENT_SP2023;
                        } else {
                            request.setAttribute("ERROR_CODE", "No student in Class");
                        }
                    }
                }
            } else {
                List<StudentProfile> list = dao.getListStudent();
                if (!list.isEmpty()) {
                    request.setAttribute("SHOWLIST_STUDENT", list);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR_CODE", "No student in Class");
                }
            }
        } catch (SQLException e) {
            log("Error at SearchController: " + e.toString());
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
