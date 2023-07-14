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
        <h2>Add Lecture</h2>
        <form action="AdminController" method="POST">
            Lecture No Roll:
            <input type="text" name="id" value="" placeholder="ex:LT-0453" required=""/><br>
            Lecture Name: 
            <input type="text" name="name" value="" required=""/><br>
            Email:
            <input type="text" name="email" value="" required=""/><br>
            <input type="submit" value="Add" />
            <input type="hidden" value="AddLecture" name="action" />
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
