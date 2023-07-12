<%-- 
    Document   : classMNG
    Created on : Jun 28, 2023, 11:01:15 AM
    Author     : Nam An
--%>

<%@page import="DTO.ClassInformation"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class Page</title>
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
        <a href="MainController?action=LecturerClass">Class</a>
        <br>

        <%
            List<ClassInformation> listClass = (List<ClassInformation>) request.getAttribute("LIST_CLASS");
            if (listClass != null) {
                if (!listClass.isEmpty()) {
                    for (ClassInformation dto : listClass) {
        %>
        <a href="ListStudentMNGController?courseID=<%=dto.getCourseID()%>&subjectID=<%=dto.getSubjectID()%>&courseName=<%=dto.getCourseName()%>&courseCode=<%=dto.getCourseCode()%>"><%=dto.getSubjectCode() + dto.getSubjectID() + "-" + dto.getCourseName() + dto.getCourseCode() %></a>
        <%
                }
            }
        %>

        <%
            }
        %> 
    </body>
</html>
