<%@page import="DTO.ClassInformation"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Group Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>Create Group</h3>
        <form action="UserController" method="POST">
            Subject: <select name="sub">
                <%
                    List<ClassInformation> list = (List<ClassInformation>) request.getAttribute("LIST_GROUP");
                    if (list != null) {
                        if (!list.isEmpty()) {
                            for (ClassInformation item : list) {
                %>
                <option value="<%=item.getSubjectID()%>">
                    <%=item.getSubjectCode() + "-" + item.getSubjectID()%>
                </option>
                <%
                            }
                        }
                    }
                %>
            </select><br>
            Class: <input type="text" name="course" value="" placeholder="NJS-1701"/><br>
            Group Name: <input type="text" name="group" value="" placeholder="Team 1"/><br>
            Topic: <input type="text" name="topic" value="" placeholder="HRM -501"/><br>
            <input type="submit" value="Create" />
            <input type="hidden" name="action" value="CreateGroup" />
        </form>
        <%
            String message = (String) request.getAttribute("WRONG_FORM");
            if (message != null) {
        %>
        <p><%=message%></p>
        <%
            }
        %>
        <%
            String message1 = (String) request.getAttribute("SUCCESS");
            if (message1 != null) {
        %>
        <p><%=message1%></p>
        <%
            }
        %>
    </body>
</html>
