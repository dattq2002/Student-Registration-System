<%@page import="system.main.DTO.Application"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Presentation Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }           
        %>
        <h3>Schedule Presentation Capstone</h3>
        <%
            List<Application> list = (List<Application>) request.getAttribute("LIST_FORM");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Course Code</th>
                    <th>Group Code</th>
                    <th>Present Date</th>
                    <th>Room</th>
                    <th>Subject Code</th>
                    <th>Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Application item : list) {
                %>
                <tr>
                    <td><%=item.getType()%></td>
                    <td><%=item.getCourseName() + item.getCourseID()%></td>
                    <td><%=item.getGrName() + item.getGrID()%></td>
                    <td>
                        <%=item.getPresentDate()%>
                    </td>
                    <td>
                        <%=item.getRoom()%>
                    </td>
                    <td><%=item.getSubCode() + item.getSubID()%></td>
                    <td>
                        <%=item.getTime()%>
                    </td>
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
