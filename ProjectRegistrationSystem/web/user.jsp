<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>      
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }           
        %>
        
        <a href="ListTopic">Topic</a></br>
        <a href="sendApplication.jsp">Send Application</a><br>
        <a href="ListForm">View Application</a><br>
        <a href="ProfileStudentController">View Profile</a><br>
        <a href="ListPresentationCapstone">View Presentation</a><br>
        <a href="GetListClassStudentController">View Class</a><br>
        <a href="GetListGroupController">Create Group</a>
        <a href="#">View Group</a>
    </body>
</html>
