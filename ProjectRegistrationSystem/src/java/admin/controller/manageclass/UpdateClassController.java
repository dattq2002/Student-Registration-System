package admin.controller.manageclass;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateClassController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //------getParameter---------
            int id = Integer.parseInt(request.getParameter("id"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String subint = request.getParameter("subint");
            String lecName = request.getParameter("lecName");
            String Courseid = request.getParameter("Courseid");
            String stdate = request.getParameter("stdate");
            String enddate = request.getParameter("enddate");
            String regex = "^(NJS|SE)-\\d+$";
            String regex2 = "^[A-Za-z]+-\\d+$";
            //-------------------
            if(!subint.matches(regex2)){
                request.setAttribute("MESSAGE", "Wrong form Subject!!");
                return;
            }else if(!Courseid.matches(regex)){
                request.setAttribute("MESSAGE", "Wrong form Course!!");
                return;
            }else{
                ClassDAO dao = new ClassDAO();
                int lecID = dao.checkLectureName(lecName);
                if(lecID == 0){
                    request.setAttribute("MESSAGE", "Not Found Lecturer!!");
                    return;
                }
                String tmp[] = subint.split("-");
                int subID = Integer.parseInt(tmp[1]);
                String tmp1[] = Courseid.split("-");
                int CourseID = Integer.parseInt(tmp1[1]);
                boolean check = dao.UpdateSubjectInClass(id, subID, CourseID, lecID, status);
                if(check){
                    boolean result = dao.UpdateCourse(CourseID, stdate, enddate);
                    if(result){
                        request.setAttribute("MESSAGE", "Update successfully !!!");
                    }
                }else{
                    request.setAttribute("MESSAGE", "Update Table fail !!");
                    return;
                }
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher("ClassController").forward(request, response);
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
