<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Topic Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search = loginUser.getEmail();
            if (search == null) {
                search = "";
            }
        %>
        <a href="manager.jsp">Home Page</a>
        <span> > </span>
        <a href="ListTopicMNGController">List of Topic</a>
        <h3>Create Topic</h3>
        <form action="ManagerController">
            Topic ID <input type="number" name="topicID" value="" required=""/> <br>
            
            Topic Code: <input types="text" name="topicCode" values="" requirede=""/> <br>
            
            Topic Name: <input type="text" name="topicName" values="" required=""/> <br>
            
            Short Description: <textarea name="shortDescription" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Full Description: <textarea name="fullDescription" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Subject ID: <input type="text" name="subject" values="" required="" placeholder="FER-201"/> <br>
            
            Semester ID: <input type="text" name="semester" values="" required="" placeholder="Spring-2022"/> <br>
            
            <input type="submit" value="CreateTopic" name="action" />
            <input type="hidden" name="searchE" value="<%=search%>"/>
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
