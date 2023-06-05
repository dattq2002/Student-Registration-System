<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR Page</title>
    </head>
    <body>
        <h1>ERROR: <%= session.getAttribute("ERROR_FUNC") %></h1>
        <a href="login.jsp">Try again !!!</a>
    </body>
</html>
