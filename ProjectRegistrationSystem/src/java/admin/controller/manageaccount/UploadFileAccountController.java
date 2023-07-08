package admin.controller.manageaccount;

import DTO.UserAccountDTO;
import admin.DAO.UserAccountDAO;
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
public class UploadFileAccountController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part filePart = request.getPart("fileAcc"); // Lấy phần tệp từ yêu cầu
        InputStream fileInputStream = filePart.getInputStream(); // Lấy luồng đầu vào từ tệp
        try ( Workbook workbook = new XSSFWorkbook(fileInputStream)) { // Tạo đối tượng Workbook từ tệp Excel
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            // Xử lý dữ liệu từ tệp Excel theo ý muốn của bạn
            UserAccountDAO dao = new UserAccountDAO();
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = 6;
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    //xử lý file
                    String Email = row.getCell(j).getStringCellValue();
                    String password = row.getCell(j + 1).getStringCellValue();
                    String FullName = row.getCell(j + 2).getStringCellValue();
                    String Role = row.getCell(j + 3).getStringCellValue();
                    String Code = row.getCell(j + 4).getStringCellValue();
                    String Status = row.getCell(j + 5).getStringCellValue();
                    UserAccountDTO acc = new UserAccountDTO(Code, Email, 
                            password, Role, FullName, Status);
                    if(cell != null){
                        boolean result = dao.addAccountFromFile(acc);
                        if(result == false){
                            request.setAttribute("MESSAGE_UPLOAD", "Upload fail !!!");
                            return;
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
            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
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
