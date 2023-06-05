<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>Add Profile Student</h3>
        <form action="MainController">
            Student ID: <input type="number" name="studentid" value="" required=""/><br>
            Student Code: <input type="text" name="studentcode" value="" required=""/><br>
            Student Name: <input type="text" name="studentname" value="" required=""/><br>
            BirthDay: <input type="text" name="birthday" value="" required=""/><br>
            Email: <input type="text" name="email" value="" required=""/><br>
            <input type="submit" value="AddProfile" name="action" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
