<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Page</title>
    </head>
    <body>
        <h1>Reset Password !!</h1>
        <%
            String message = (String) session.getAttribute("EXSITED_EMAIL1");
            if (message != null) {
        %>
        <h4><%=message%></h4>
        <%
            }
            String emailExsit = (String) session.getAttribute("EXSITED_EMAIL");
            if (emailExsit != null) {
        %>
        <form action="ResetPasswordController" method="POST">
            <input type="hidden" name="email" value="<%= session.getAttribute("EXSITED_EMAIL")%>" />
            Reset Password: <input type="password" name="rsPassword" value="" /><br>
            <input type="submit" value="Reset" name="action" />
        </form>
        <%
            }
        %>
        <%
            String message1 = (String) request.getAttribute("MESSAGE");
            if (message1 != null) {
        %>
        <a style="color: red"><%=message1%></a>
        <%
            }
        %>
    </body>
</html>
