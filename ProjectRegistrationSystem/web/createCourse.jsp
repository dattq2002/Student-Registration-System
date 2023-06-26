<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Course Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Course Management</h1>
        <form action="MainController" method="POST">
            <label for="semesterNumber">Semester ID:</label>
            <input type="number" name="semesterNumber" value="" required="" 
                   min="0" placeholder="must be exsit!!">
            <br>
            <label for="courseRoll">Course Roll Number:</label>
            <input type="text" name="courseRoll" value="" required="" placeholder="NJS-1701">
            <br>
            <label for="startDate">Start Date:</label>
            <input type="date" name="startDate" value="">
            <br>
            <label for="endDate">End Date:</label>
            <input type="date" name="endDate" value="">
            <br>
            <label for="lecName">Lecture Name:</label>
            <input type="text" name="lecName" value="" required="" 
                   placeholder="must be exsit!!">
            <br>
            <label for="subRoll">Subject Roll Number:</label>
            <input type="text" name="subRoll" value="" required="" placeholder="FER-201">
            <br>
            <label for="subName">Subject Name:</label>
            <input type="text" name="subName" value="">
            <br>
            <label for="Credit">Credit:</label>
            <input type="number" name="Credit" value="" min="0" required="">
            <br>
            <input type="submit" value="Add Course" name="action"/>
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
