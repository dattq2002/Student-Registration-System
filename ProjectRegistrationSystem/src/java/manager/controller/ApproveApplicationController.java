/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package manager.controller;

import DTO.ApplicationMNG;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managerDAO.ApplicationMNGDAO;

public class ApproveApplicationController extends HttpServlet {
   
    private static final String ERROR = "ListApplicationMNGController";
    private static final String SUCCESS = "ListApplicationMNGController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int formID = Integer.parseInt(request.getParameter("formID"));
            String note = request.getParameter("note");

            String type = request.getParameter("type");
            if (type.equals("Report")) {
                ApplicationMNGDAO dao = new ApplicationMNGDAO();
                ApplicationMNG application = new ApplicationMNG(formID, note);
                boolean check = dao.approveReportApplication(application);
                if (check) {
                    request.setAttribute("ERROR_MESSAGE", "Approve Successfully !!");
                    url = SUCCESS;
                }
            } else {
                int room = Integer.parseInt(request.getParameter("room"));
                String presentDate = request.getParameter("presentDate");
                String time = request.getParameter("time");
                ApplicationMNGDAO dao = new ApplicationMNGDAO();
                ApplicationMNG application = new ApplicationMNG(formID, note, room, presentDate, time);
                boolean check = dao.approvePresentApplication(application);
                if (check) {
                    request.setAttribute("ERROR_MESSAGE", "Approve Successfully !!");
                    url = SUCCESS;
                }
            }

        } catch (NumberFormatException | SQLException e) {
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
