package manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managerDAO.ProfileMNGDAO;

public class UpdateProfileMNGController extends HttpServlet {
   
    private static final String ERROR = "ProfileMNGController";
    private static final String SUCCESS = "ProfileMNGController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            
            ProfileMNGDAO dao = new ProfileMNGDAO();
            boolean check = dao.updateProfile(name, gender, birthday, address, city, phoneNumber, email);
            if(check) {
                request.setAttribute("ERROR_MESSAGE", "Update Successfully!!");
                url = SUCCESS;
            }else{
                request.setAttribute("ERROR_MESSAGE", "Update Fail!!");
            }
        } catch (SQLException e) {
            log("Error at UpdateController: " + e.toString());
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
