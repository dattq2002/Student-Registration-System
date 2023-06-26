<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>Profile, <%=loginUser.getFullName()%></h3>
        <form action="MainController">
            Student Code: <%= (String) request.getAttribute("Code") + (int) request.getAttribute("ID")%><br>
            Name: <input type="text" name="name" value="<%=request.getAttribute("Name")%>"/><br>
            Birthday: <input type="text" name="birthday" value="<%=request.getAttribute("Birthday")%>"/><br>
            Email: <%=request.getAttribute("Email")%><input type="hidden" name="email" value="<%=request.getAttribute("Email")%>" /> <br> 
            <input type="submit" value="Update Profile" name="action" />
        </form>
        <%
            String message = (String) request.getAttribute("MESS_UPDATE");
            if (message != null) {
        %>
        <h3><%=message%></h3>
        <%
            }
        %>
    </body>
</html>
