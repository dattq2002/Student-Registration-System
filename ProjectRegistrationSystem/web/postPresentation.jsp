<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Presentation Page</title>
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
        <h3>Post Presentation</h3>
        <form action="ManagerController" method="POST">
            Subject ID: <input type="text" name="subject" values="" required="" placeholder="FER-201"/> <br>
            
            Course ID: <input type="text" name="course" values="" required="" placeholder="NJS-1701"/> <br>
            
            Room <input type="number" name="room" value="" required=""/> <br>
            
            Presentation Date: <input type="date" name="presentDate" values="" requirede=""/> <br>
            
            Time: <input type="time" name="time" values="" required=""/> <br>
            
            Note: <textarea name="note" style="width: 300px; height: 100px;" required=""></textarea> <br>          
                 
            <input type="submit" value="Post Presentation" name="action" />
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
