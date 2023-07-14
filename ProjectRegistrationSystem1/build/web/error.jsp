<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR Page</title>
    </head>
    <body>
        <h1>ERROR function: <%= session.getAttribute("ERROR_FUNC") %></h1>
        <h1>ERROR Code: <%= request.getAttribute("ERROR_CODE") %></h1>
        <a href="login.jsp">Try again !!!</a>
    </body>
</html>
