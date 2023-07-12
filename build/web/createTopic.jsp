<%-- 
    Document   : createTopic
    Created on : Jun 12, 2023, 10:04:44 AM
    Author     : Nam An
--%>

<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="manager.jsp">Home Page</a>
        <a href="MainController?action=LecturerListSubjectTopic">Subject</a>
        <a href="MainController?action=LecturerTopic">Create</a>
        <h3>Create Topic</h3>
        <form action="MainController">
            Topic ID <input type="number" name="topicID" value="" required=""/> <br>
            
            Topic Code: <input types="text" name="topicCode" values="" requirede=""/> <br>
            
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
