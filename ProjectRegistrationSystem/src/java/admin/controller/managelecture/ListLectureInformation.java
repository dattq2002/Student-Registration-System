package admin.controller.managelecture;

import DTO.LectureProfile;
import admin.DAO.ProfileDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListLectureInformation extends HttpServlet {
    private static final String LECTURE_SP2022 = "viewLectureSP2022.jsp";
    private static final String LECTURE_FA2022 = "viewLectureFA2022.jsp";
    private static final String LECTURE_SP2023 = "viewLectureSP2023.jsp";
    private static final String SUCCESS = "viewLecture.jsp";
    private static final String ERROR = "error.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProfileDAO dao = new ProfileDAO();
            String id = request.getParameter("idSemester");
            if (id != null) {
                if (id.equals("11111")) {
                    List<LectureProfile> list = dao.getListLectureSP2022();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11111", id);
                            request.setAttribute("LIST_LT_SPRING2022", list);
                            url = LECTURE_SP2022;
                        }
                    }
                }else if(id.equals("11112")){
                    List<LectureProfile> list = dao.getListLectureFA2022();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11112", id);
                            request.setAttribute("LIST_LT_FALL2022", list);
                            url = LECTURE_FA2022;
                        }
                    }
                }else if(id.equals("11113")){
                    List<LectureProfile> list = dao.getListLectureSP2023();
                    if (list != null) {
                        if (!list.isEmpty()) {
                            request.setAttribute("11113", id);
                            request.setAttribute("LIST_LT_SPRING2023", list);
                            url = LECTURE_SP2023;
                        }
                    }
                }
            } else {
                List<LectureProfile> list = dao.getListLecture();
                if (!list.isEmpty()) {
                    request.setAttribute("SHOWLIST_LECTURE", list);
                    url = SUCCESS;
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
