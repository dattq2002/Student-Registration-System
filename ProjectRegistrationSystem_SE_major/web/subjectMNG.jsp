<%@page import="system.main.DTO.Subject"%>
<%@page import="java.util.List"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="system.main.DTO.Class"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <%
            List<Class> list1 = (List<Class>) request.getAttribute("COURSE_INFO");
            if (list1 != null) {
                if (!list1.isEmpty()) {
                    for (Class item : list1) {
        %>
        <h3>Course: <%=item.getCourseName() + item.getCourseCode() %></h3>
        <a>Start Date: <%=item.getStartDate() %></a>
        <a>End Date: <%=item.getEndDate()%></a><br>
        <%
                    }
                }
            }
        %>

        <%
            List<Subject> list = (List<Subject>) request.getAttribute("SUBJECT");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (Subject item : list) {
        %>
        <a href="LecturerController?action=ViewDetail&courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=item.getSubjectID()%>&sesID=<%=session.getAttribute("SEMESTER_ID")%>">
            <%=item.getSubjectCode() + item.getSubjectID()%>
        </a>
        <%
                    }
                }
            }
        %>
    </body>
</html>