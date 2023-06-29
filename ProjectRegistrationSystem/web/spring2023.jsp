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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="ListStudentInformation?idSemester=<%=request.getAttribute("11113")%>">
            List Student
        </a>
        <a href="ListLectureInformation?idSemester=<%=request.getAttribute("11113")%>">
            List Lecture
        </a>
        <a href="ClassController?idSemester=<%=request.getAttribute("11113")%>">
            List Class
        </a>
    </body>
</html>
