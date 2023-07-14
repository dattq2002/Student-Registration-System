<%-- 
    Document   : createTopic
    Created on : Jul 13, 2023, 4:49:36 PM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <h3>Create Topic</h3>
        <form action="LecturerController">
            Topic Code: <input type="text" name="topic" value="" required="" placeholder="HRM-501"/> <br>
            
            Topic Name: <input type="text" name="topicName" values="" required=""/> <br>
            
            Short Description: <textarea name="shortDescription" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Full Description: <textarea name="fullDescription" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            <input type="submit" value="Create" />
            <input type="hidden" value="CreateTopic" name="action" />
            <input type="reset" value="Reset" />
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
