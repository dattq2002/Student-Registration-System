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
        <h2>Create Subject</h2>
        <form action="AdminController" method="POST">
            Subject: <input type="text" name="subject" value="" required=""/>
            Subject Name: <input type="text" name="subName" value="" required=""/>
            Description: <input type="text" name="Des" value="" />
            Credit: <input type="number" name="credit" value="" min="0" required=""/>
            <input type="submit" value="Create New Subject" name="action" />
        </form>

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <a><%=message%></a>
        <%
            }
        %>
    </body>
</html>
