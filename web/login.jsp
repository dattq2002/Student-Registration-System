<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login System!!</h1>
        <form action="MainController" method="POST">
            Email: <input type="text" name="email" value=""/><br>
            Password: <input type="password" name="password" value="" /><br>
            <input type="submit" value="Login" name="action" />
        </form>
        <div>
            <%
                String message = (String) session.getAttribute("ERROR_LOGIN");
                if (message != null) {
            %>
            <h4 style="color: red"><%= message%></h4>
            <%
                }
            %>
        </div>
    </body>
</html>
