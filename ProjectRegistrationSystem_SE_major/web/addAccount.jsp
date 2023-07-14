<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Registration System</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Input your file to add Account</h2>
        <a>Note in file .xlsx: +column: Email, password, Full Name, Role, Code, Status</a><br>
        <a>ex   :abc123@fpt.edu.vn, String, String,ADMIN, AD, Active</a><br>
        <a>ex   :abc123@fe.edu.vn , String , String, MNG, MN, Deactive</a><br>
        <a>ex   :abc123@fe.edu.vn , String , String, USER, US, Active </a><br><br>
        <form action="UploadFileAccountController" method="POST" enctype="multipart/form-data">
            <input type="file" name="fileAcc" accept=".xlsx"><br>
            <br>
            <input type="submit" value="Upload" />
            <input type="hidden" value="UploadAccount" name="action">
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE_UPLOAD");
            if (message != null) {
        %>
        <a style="color: red"><%=message%></a>
        <%
            }
            String message1 = (String) request.getAttribute("SUCCESS");
            if (message1 != null) {
        %>
        <a style="color: green"><%=message1%></a>
        <%
            }
        %>
    </body>
</html>
