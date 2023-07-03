<%@page import="DTO.ClassInformation"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            List<ClassInformation> list = (List<ClassInformation>) request.getAttribute("LIST_EROLLMENT");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (ClassInformation item : list) {
        %>
        <a href="GetListClassStudentEnrollment?CourseID=<%=item.getCourseID()%>">
            <%=item.getSubjectCode() + item.getSubjectID() + "-" 
                + item.getCourseName() + item.getCourseCode()%></a>
        <%
                    }
                }
            }
        %>
    </body>
</html>
