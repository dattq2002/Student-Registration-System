<%-- 
    Document   : assignTopic
    Created on : Jul 7, 2023, 4:55:51 PM
    Author     : Nam An
--%>

<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <h3>Assign Topic</h3>
        
        <form action="MainController">
            Topic ID <input type="number" name="topicID" value="" required=""/> <br>
            
            Subject ID: <input type="text" name="subject" values="" required="" placeholder="FER-201"/> <br>
            
<!--            Semester ID: <input type="text" name="semester" values="" required="" placeholder="Spring-2022"/> <br>-->
            
            <input type="submit" value="Assign" />
            <input type="hidden" value="AssignTopic" name="action" />
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
