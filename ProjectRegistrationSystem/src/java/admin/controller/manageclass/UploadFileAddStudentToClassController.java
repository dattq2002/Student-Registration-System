package admin.controller.manageclass;

import admin.DAO.ClassDAO;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@MultipartConfig
public class UploadFileAddStudentToClassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part filePart = request.getPart("file"); // Lấy phần tệp từ yêu cầu
        InputStream fileInputStream = filePart.getInputStream(); // Lấy luồng đầu vào từ tệp
        String course = request.getParameter("course");
        try ( Workbook workbook = new XSSFWorkbook(fileInputStream)) { // Tạo đối tượng Workbook từ tệp Excel
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            // Xử lý dữ liệu từ tệp Excel theo ý muốn của bạn
            ClassDAO dao = new ClassDAO();
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    double StuID = row.getCell(j).getNumericCellValue();
                    double courseID = 0;
                    if (course.equals("null")) {
                        courseID = row.getCell(j + 1).getNumericCellValue();
                    }
                    String StartDate = row.getCell(j + 2).getStringCellValue();
                    double subjectID = row.getCell(j + 3).getNumericCellValue();
                    String Note = row.getCell(j + 4).getStringCellValue();
                    if (cell != null) {
                        if (!course.equals("null")) {
                            boolean check = dao.getDataFromFile(StuID, Double.parseDouble(course), StartDate, subjectID, Note);
                            if (check == false) {
                                return;
                            }
                        } else {
                            boolean result = dao.getDataFromFile(StuID, courseID, StartDate, subjectID, Note);
                            if (result == false) {
                                return;
                            }
                        }
                    }
                    break;
                }
            }
            request.setAttribute("SUCCESS", "Add data successfully !!!");
            workbook.close(); // Đóng Workbook
            fileInputStream.close(); // Đóng luồng đầu vào
        } catch (Exception e) {
            request.setAttribute("FAIL", "Add data fail !!");
        } finally {
            request.getRequestDispatcher("addStudentToClass.jsp").forward(request, response);
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
