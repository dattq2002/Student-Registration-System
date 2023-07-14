<%@page import="system.main.DTO.ClassInformation"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Registration System</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            ClassInformation infcl = (ClassInformation) request.getAttribute("INFOR_CLASS");
            if (infcl != null) {
        %>
        <h3>Course: <%=infcl.getCourseName() + infcl.getCourseCode()%> - 
            Semester: <%=session.getAttribute("SEMESTER_NAME")%><%=session.getAttribute("SEMESTER_YEAR")%></h3>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%> - 
            Subject: <%=infcl.getSubjectCode() + infcl.getSubjectID()%></h3>
        <%
            }
        %>
        <a href="GetListStudentInClassUser?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
            List Student
        </a>
        <a href="ListTopicUser?sesID=<%=session.getAttribute("SEMESTER_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>&courseID=<%=session.getAttribute("COURSE_ID")%>">List Topic</a>
        <a href="ListGroupUser?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">List Group</a>
    </body>
</html>
