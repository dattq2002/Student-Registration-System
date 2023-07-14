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
        <h1>Create Class</h1>
        <form action="AdminController" method="POST">
            <label>Semester:</label>
            <input type="text" name="semester" value="" required="" 
                   placeholder="must be exsit!!">
            <br>
            <label for="courseRoll">Course Roll Number:</label>
            <input type="number" name="courseRoll" value="" required="" placeholder="id">
            <br>
            <label for="courseName">Course Name:</label>
            <input type="text" name="courseName" value="" required="" placeholder="NJS-1701">
            <br>
            <label for="startDate">Start Date:</label>
            <input type="date" name="startDate" value="" required="">
            <br>
            <label for="endDate">End Date:</label>
            <input type="date" name="endDate" value="" required="">
            <input type="submit" value="Create Course" name="action"/>
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if(message != null){
        %>
        <a><%=message %></a>
        <%
            }
        %>
    </body>
</html>
