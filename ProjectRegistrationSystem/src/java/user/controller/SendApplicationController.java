package user.controller;

import DTO.Application;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import userDAO.ApplicationDAO;

public class SendApplicationController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String select = request.getParameter("selectOption");
            String id = request.getParameter("id").trim();
            String lecName = request.getParameter("lecname");
            String course = request.getParameter("course");
            String reason = request.getParameter("reason");
            if(!id.matches("^SE\\d{2}-\\d+$")){
                request.setAttribute("MESSAGE", "Wrong form !! try again");
            }else if(!course.matches("^NJS-\\d{4}$")){
                request.setAttribute("MESSAGE", "Wrong form !! try again");
            }else{
                ApplicationDAO dao = new ApplicationDAO();
                int lecID = dao.getLectureID(lecName);
                String[] tmp = id.split("-");
                int stID = Integer.parseInt(tmp[1]);
                String[] tmp2 = course.split("-");
                int courseID = Integer.parseInt(tmp2[1]);
                Application form = new Application(select, lecID, stID,reason, courseID);
                boolean check = dao.processApplication(form);
                if(check){
                    request.setAttribute("MESSAGE", "Sent successfully!!");
                }else{
                    request.setAttribute("MESSAGE", "Fail!!");
                }
             }
        } catch (Exception e) {
            log("Err at SendApplicationController: " + e.toString());
        }finally{
            request.getRequestDispatcher("sendApplication.jsp").forward(request, response);
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
