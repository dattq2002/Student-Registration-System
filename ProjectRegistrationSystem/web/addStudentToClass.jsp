<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student To Class Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Input your file to add Student to Class</h2>
        <form action="UploadFileController" method="POST" enctype="multipart/form-data">
            Course: <select name="course">
                <option value="null">Choose Course</option>
                <option value="1701">NJS1701</option>
                <option value="1702">NJS1702</option>
                <option value="1703">NJS1703</option>
                <option value="1704">NJS1704</option>
                <option value="1705">NJS1705</option>
                <option value="1706">NJS1706</option>
                <option value="1732">SE1732</option>
                <option value="1733">SE1733</option>
            </select><br><br>
            <input type="file" name="file" accept=".xlsx"><br>
            <br>
            <input type="submit" value="Upload" name="action">
        </form>
        <%
            String message = (String) request.getAttribute("SUCCESS");
            if (message != null) {
        %>
        <a style="color: green"><%=message%></a>
        <%
            }
            String message1 = (String) request.getAttribute("FAIL");
            if (message1 != null) {
        %>
        <a style="color: red"><%=message1%></a>
        <%
            }
        %>
    </body>
</html>
