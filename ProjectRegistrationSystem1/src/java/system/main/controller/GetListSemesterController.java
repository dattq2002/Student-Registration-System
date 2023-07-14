package system.main.controller;

import lecturer.DAO.ClassDAO;
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

public class GetListSemesterController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "course.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO)session.getAttribute("LOGIN_USER"); 
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            session.setAttribute("SEMESTER_ID", sesID);
            SemesterDAO sesDao = new SemesterDAO();
            List<Semester> listSes = sesDao.getListSemester(sesID);
            if(listSes != null){
                if(!listSes.isEmpty()){
                    request.setAttribute("SEMESTER", listSes);
                    ClassDAO clDao = new ClassDAO();
                    List<Class> listCl = clDao.getListClass(sesID, loginUser.getEmail());
                    if(listCl != null){
                        if(!listCl.isEmpty()){
                            request.setAttribute("CLASS", listCl);
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (NumberFormatException | SQLException e) {
            log("Error at GetListSemesterController: " + e.toString());
        }finally{
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
