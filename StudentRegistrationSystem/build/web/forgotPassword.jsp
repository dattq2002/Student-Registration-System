<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password !!</title>
    </head>
    <body>
        <h1>Forgot Password !!</h1>
        <form action="MainController">
            Email: <input type="text" name="email" value="" /><br>
            <input type="submit" value="Submit" name="action" />
        </form>
        <%
            String message = (String) session.getAttribute("ERROR_EMAIL");
            if(message != null){
        %>
        <h5 style="color: red"><%= message%></h5>
        <%
            }
        %>
    </body>
</html>
