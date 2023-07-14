package user.controller.group;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.DAO.GroupUserDAO;

public class SwitchGroupController extends HttpServlet {

    private static final String ERROR = "SwitchGroupLink";
    private static final String SUCCESS = "ListGroupUser";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int yourID = Integer.parseInt(request.getParameter("yourID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int groupID = Integer.parseInt(request.getParameter("groupID"));
            String yourGrPresent = request.getParameter("yourGrPresent");
            String Code = request.getParameter("CODE");
            
            String gCode = Code.substring(0, 2);
            int sCode = Integer.parseInt(Code.substring(2));
            GroupUserDAO dao = new GroupUserDAO();
            boolean result = dao.checkGrCode(gCode, sCode);
            if (result == false) {
                request.setAttribute("MESSAGE", "The Code is wrong !!");
                url = ERROR;
                return;
            }
            int grPresentID = dao.getGrID(yourGrPresent, courseID);
            if(grPresentID == 0){
                request.setAttribute("MESSAGE", "Can't find your group !!");
                return;
            }else if(grPresentID == groupID){
                request.setAttribute("MESSAGE", "You are in this Group already, can't switch !!");
                return;
            }
            boolean check = dao.GroupEnrollment(subID, groupID, yourID, grPresentID);
            if (check) {
                request.setAttribute("MESSAGE", "Switch Group successfully!!");
                url = SUCCESS;
            }
        } catch (NumberFormatException | SQLException e) {
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
