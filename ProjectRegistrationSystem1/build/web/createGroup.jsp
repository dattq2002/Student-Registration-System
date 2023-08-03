<%-- 
    Document   : createGroup
    Created on : Jul 13, 2023, 7:27:39 PM
    Author     : Nam An
--%>

<%@page import="system.main.DTO.TopicAssign"%>
<%@page import="system.main.DTO.ClassInformation"%>
<%@page import="java.util.List"%>
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

        <a href="ListGroup?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>&sesID=<%=session.getAttribute("SEMESTER_ID")%>">List Group</a>
        
        <h3>Create Topic</h3>
        <form action="LecturerController">      

            <div>
                Group Name: <input type="text" name="grName" value="" required=""/>
            </div>          
     
            <input type="submit" value="Create" />
            <input type="hidden" value="CreateGroup" name="action" />  
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>"/>
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>"/>
            <input type="hidden" name="sesID" value="<%=session.getAttribute("SEMESTER_ID")%>"/>
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
