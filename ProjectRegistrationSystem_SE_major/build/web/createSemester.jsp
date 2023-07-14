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
        <form action="MainController">
            Semester ID: <input type="number" name="id" value="" required="" min="0"/>
            Year: <input type="number" name="year" value="" min="0" required=""/>
            Name: <input type="text" name="name" value="" required=""/>
            Status: <select name="Status">
                <option>Available</option>
                <option>UnAvailable</option>
            </select>
            Start Date: <input type="date" name="StartDate" value="" required=""/>
            End Date: <input type="date" name="EndDate" value="" required=""/>
            <input type="submit" value="Create Semester" name="action" />
        </form>

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <a><%=message %></a>
        <%
            }
        %>
    </body>
</html>
