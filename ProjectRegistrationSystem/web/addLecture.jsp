<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>add Lecture Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Input your file to add Lecture</h2>
        <form action="UploadFileLectureController" method="POST" enctype="multipart/form-data">
            <input type="file" name="fileLec" accept=".xlsx"><br>
            <br>
            <input type="submit" value="Upload" />
            <input type="hidden" value="UploadLecture" name="action">
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
