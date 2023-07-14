package user.controller.group;

import admin.DAO.ClassDAO;
import admin.DAO.GroupDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DTO.ClassInformation;
import system.main.DTO.Group;
import system.main.DTO.GroupProject;
import system.main.DTO.UserAccountDTO;
import user.DAO.GroupUserDAO;

public class ListGroupUser extends HttpServlet {
    
    private static final String ERROR = "error.jsp";   
    private static final String SUCCESS = "groupUser.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
//            request.setAttribute("COURSE_ID", courseID);
//            request.setAttribute("SUBJECT_ID", subID);
            GroupUserDAO dao = new GroupUserDAO();
            List<GroupProject> list = dao.listProjectGroup(loginUser.getEmail(), courseID, subID);
            if (list != null) {
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_PROJECT", list);
                    url = SUCCESS;
                }
            }
            List<Group> list1 = dao.getListMember(loginUser.getEmail(), courseID, subID);
            int count = list1.size();
            if (!list1.isEmpty()) {
                request.setAttribute("LIST_MEMBER", list1);
                request.setAttribute("AMOUNT", count);
                url = SUCCESS;
            }
            ClassDAO clDao = new ClassDAO();
            ClassInformation clInfo = clDao.getInforClass(courseID, subID);
            if(clInfo != null){
                request.setAttribute("INFOR_CLASS", clInfo);
                String lecName = clDao.getLecName(courseID, subID);
                request.setAttribute("LECTURE_NAME", lecName);
                url = SUCCESS;
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
