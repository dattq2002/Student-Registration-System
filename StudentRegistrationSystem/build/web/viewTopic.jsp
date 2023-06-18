<%@page import="DTO.Topic"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>View Topic</h2>
        <%
            List<Topic> list = (List<Topic>) request.getAttribute("SHOWLIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>ShortDescription</th>
                    <th>FullDesCription</th>
                    <th>LectureName</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Topic item : list) {
                %>
                <tr>
                    <td><%=count++ %></td>
                    <td><%=item.getID() %></td>
                    <td><%=item.getCode() %></td>
                    <td><%=item.getName() %></td>
                    <td><%=item.getShortDescription() %></td>
                    <td><%=item.getFullDescription() %></td>
                    <td><%=item.getLecName()%></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>

        <%
                }
            }
        %>
    </body>
</html>
