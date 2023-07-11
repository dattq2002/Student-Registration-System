<%@page import="DTO.UserAccountDTO"%>
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
        <a href="manager.jsp">Home Page</a>
        <a href="ListTopicMNGController">List of Topic</a>
        <h3>Assign Topic</h3>
        
        <form action="ManagerController">
            Topic ID <input type="number" name="topicID" value="" required=""/> <br>
            
            Subject ID: <input type="text" name="subject" values="" required="" placeholder="FER-201"/> <br>
            
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
