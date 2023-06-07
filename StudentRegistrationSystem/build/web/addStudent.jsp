<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="admin.jsp">Home</a> > <a href="viewStudent.jsp"> View Student</a> > <a href="addStudent.jsp">Create Student</a>
        <h3>Add Student Profile</h3>
        <form action="MainController">
            Student ID: <input type="number" name="studentid" value="" required="" min ="0"placeholder="more than 0"/><br>
            Student Code: <input type="text" name="studentcode" value="" required="" minlength="2" placeholder="ex:SE01"/><br>
            Student Name: <input type="text" name="studentname" value="" required=""placeholder="ex: abc"/><br>
            BirthDay: <input type="text" name="birthday" value=""placeholder="ex: 1999-12-07"/><br>
            Email: <input type="text" name="email" value="" required="" placeholder="ex: abc@fpt.edu.vn"/><br>
            <input type="submit" value="AddProfile" name="action" />
            <input type="reset" value="Reset" />
        </form>
        <%
            String message = (String) session.getAttribute("MESSAGE1");
            if (message != null) {
        %>
        <h4 style="color: red"><%= message%></h4>
        <%
            }
        %>
    </body>
</html>
