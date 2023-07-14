package manager.controller.manage.group;

import admin.DAO.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProjectTopic extends HttpServlet {
   
    private static final String ERROR = "GetListGroupDetailMNG";
    private static final String SUCCESS = "GetListGroupDetailMNG";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            String topic = request.getParameter("topic");
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            //regex
            String regex = "^[A-Za-z]+-\\d+$";
            GroupDAO grDao = new GroupDAO();

            int topicID = 0;
            if (!topic.matches(regex)) {
                request.setAttribute("MESSAGE", "Topic is not Match !!!");
                return;
            } else {
                String tmp[] = topic.split("-");
                String topicCode = tmp[0];
                topicID = Integer.parseInt(tmp[1]);
                boolean result = grDao.checkTopicID(subID, sesID, topicID, topicCode);
                if (!result) {
                    request.setAttribute("MESSAGE", "Topic Code could not be found!!!");
                    return;
                }
            }

            boolean check = grDao.updateProjectTopic(topicID, projectID);
            if (check) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Update Successfully !!");
            }
        } catch (Exception e) {
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
