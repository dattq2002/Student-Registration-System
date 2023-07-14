package admin.controller.manage.subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.Subject;
import user.DAO.SubjectUserDAO;

public class CreateSubjectController extends HttpServlet {
    private static final String ERROR = "createSubject.jsp";
    private static final String SUCCESS = "createSubject.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String subject = request.getParameter("subject");
            String Name = request.getParameter("subName");
            String des = request.getParameter("Des");
            int credit = Integer.parseInt(request.getParameter("credit"));
            String regex = "^[A-Za-z]+-\\d+$";
            if(!subject.matches(regex)){
                request.setAttribute("MESSAGE", "Subject is wrong form !!!");
                return;
            }
            String tmp[] = subject.split("-");
            String subCode = tmp[0];
            int subID = Integer.parseInt(tmp[1]);
            SubjectUserDAO dao = new SubjectUserDAO();
            Subject s = new Subject(subID, subCode, Name, des, credit);
            boolean check = dao.InsertSubject(s);
            if(check){
                request.setAttribute("MESSAGE", "Subject is creating successfully !!!");
                url = SUCCESS;
            }else{
                request.setAttribute("MESSAGE", "Subject is creating Fail !!!");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
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
