package system.main.controller;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DAO.SemesterDAO;
import system.main.DTO.Semester;
import system.main.DTO.Class;
import system.main.DTO.UserAccountDTO;
import user.DAO.ClassUserDAO;

public class GetListSemesterController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "course.jsp";
    private static final String SUCCESS1 = "courseUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int SesID = Integer.parseInt(request.getParameter("sesID"));
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            session.setAttribute("SEMESTER_ID", SesID);
            SemesterDAO sesDao = new SemesterDAO();
            if (loginUser.getRoleID().equals("ADMIN")) {
                List<Semester> listSes = sesDao.getListSemester(SesID);
                if (listSes != null) {
                    if (!listSes.isEmpty()) {
                        request.setAttribute("SEMESTER", listSes);
                        ClassDAO clDao = new ClassDAO();
                        List<Class> listCl = clDao.getListClass(SesID);
                        if (listCl != null) {
                            if (!listCl.isEmpty()) {
                                request.setAttribute("CLASS", listCl);
                                url = SUCCESS;
                            }
                        }
                    }
                }
            }else if(loginUser.getRoleID().equals("USER")){
                List<Semester> listSes = sesDao.getListSemester(SesID);
                if (listSes != null) {
                    if (!listSes.isEmpty()) {
                        request.setAttribute("SEMESTER", listSes);
                        ClassUserDAO clDao1 = new ClassUserDAO();
                        List<Class> listCl = clDao1.getListClass(SesID, loginUser.getEmail());
                        if (listCl != null) {
                            if (!listCl.isEmpty()) {
                                request.setAttribute("CLASS", listCl);
                                url = SUCCESS1;
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            log("Error at GetListSemesterController: " + e.toString());
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
