<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.Semester"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Semester Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <%
            List<Semester> list = (List<Semester>) request.getAttribute("LIST_SEMESTER");
            if (list != null) {
                if (!list.isEmpty()) {
                    for (Semester item : list) {
        %>
        <a href="LinkSemester?id=<%=item.getID()%>"><%= item.getName() + item.getYear()%></a>
        <%
                    }
                }
            }
        %>
    </body>
</html>
