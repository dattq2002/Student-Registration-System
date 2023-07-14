package user.controller.group;

import admin.DAO.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.main.DTO.Group;
import system.main.DTO.GroupProject;

public class GetListGroupDetailUser extends HttpServlet {
   
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "groupDetailUser.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int grID = Integer.parseInt(request.getParameter("grID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            String grName = request.getParameter("groupName");
            request.setAttribute("GROUP_NAME", grName);
            GroupDAO grDao = new GroupDAO();
            List<Group> list = grDao.getListGroupDetail(grID, subID);
            if(list != null){
                if(!list.isEmpty()){
                    request.setAttribute("GROUP_DETAIL", list);
                    url = SUCCESS;
                    List<GroupProject> list1 = grDao.getListProject(grID);
                    if(list1 != null){
                        if(!list1.isEmpty()){
                            request.setAttribute("PROJECT_GROUP", list1);
                            url = SUCCESS;
                        }
                    }
                }
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
