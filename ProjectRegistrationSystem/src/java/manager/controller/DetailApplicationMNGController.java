package manager.controller;

import DTO.ApplicationMNG;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managerDAO.ApplicationMNGDAO;

public class DetailApplicationMNGController extends HttpServlet {
   private static final String ERROR = "ListApplicationMNGController";
    private static final String SUCCESS_R = "detailReportApplicationMNG.jsp";
    private static final String SUCCESS_P = "detailPresentationApplicationMNG.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String detail = request.getParameter("detail");
            String type = request.getParameter("type");

            ApplicationMNGDAO dao = new ApplicationMNGDAO();
            if (type.equals("Report")) {
                List<ApplicationMNG> list = dao.searchReportApplicationByID(detail);
                if (!detail.isEmpty()) {
                    request.setAttribute("DETAIL_R_APPLICATION", list);
                    url = SUCCESS_R;
                }
            } else {
                List<ApplicationMNG> list = dao.searchPresenApplicationByID(detail);
                if (!detail.isEmpty()) {
                    request.setAttribute("DETAIL_P_APPLICATION", list);
                    url = SUCCESS_P;
                }
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
