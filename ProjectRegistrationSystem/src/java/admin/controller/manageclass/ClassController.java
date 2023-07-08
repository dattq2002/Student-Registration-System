package admin.controller.manageclass;

import DTO.ClassInformation;
import admin.DAO.ClassDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String SUCCESS = "viewClass.jsp";
    private static final String CLASS_SP2022 = "viewClassSP2022.jsp";
    private static final String CLASS_FA2022 = "viewClassFA2022.jsp";
    private static final String CLASS_SP2023 = "viewClassSP2023.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ClassDAO dao = new ClassDAO();
            String id = request.getParameter("idSemester");
            if (id != null) {
                if(id.equals("11111")){
                    List<ClassInformation> list = dao.getListClassSP2022();
                    if(list !=null){
                        if(!list.isEmpty()){
                            request.setAttribute("11111", id);
                            request.setAttribute("LIST_CLASS_SPRING2022", list);
                            url = CLASS_SP2022;
                        }
                    }
                }else if(id.equals("11112")){
                    List<ClassInformation> list = dao.getListClassFA2022();
                    if(list !=null){
                        if(!list.isEmpty()){
                            request.setAttribute("11112", id);
                            request.setAttribute("LIST_CLASS_FALL2022", list);
                            url = CLASS_FA2022;
                        }
                    }
                }else if(id.equals("11113")){
                    List<ClassInformation> list = dao.getListClassSP2023();
                    if(list !=null){
                        if(!list.isEmpty()){
                            request.setAttribute("11113", id);
                            request.setAttribute("LIST_CLASS_SPRING2023", list);
                            url = CLASS_SP2023;
                        }
                    }
                }
            } else {
                List<ClassInformation> list = dao.getListClass();
                if (list != null) {
                    if (!list.isEmpty()) {
                        request.setAttribute("LIST_CLASS", list);
                        url = SUCCESS;
                    }
                }
            }
        } catch (SQLException e) {
            log("Err at ClassController: " + e.toString());
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
