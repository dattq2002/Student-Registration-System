<%@page import="DTO.Application"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Form Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("searchform");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Process Form</h1>
        <form action="MainController" method="POST">
            Search: <input type="date" name="searchform" value="<%=search%>" />
            <input type="submit" value="Search Form" name="action" />
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <h3><%=message%></h3>
        <%
            }

            List<Application> list = (List<Application>) request.getAttribute("LIST_FORM");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Lecture Name</th>
                    <th>Type</th>
                    <th>Course Code</th>
                    <th>Group Code</th>
                    <th>Present Date</th>
                    <th>Room</th>
                    <th>Create Date</th>
                    <th>Status</th>
                    <th>Subject Code</th>
                    <th>Time</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Application item : list) {
                %>
            <form action="UpdateFormPresentController" method="POST">
                <tr>
                    <td><%=item.getID()%></td>
                    <td><%=item.getLecName()%></td>
                    <td><%=item.getType()%></td>
                    <td><%=item.getCourseName() + item.getCourseID()%></td>
                    <td><%=item.getGrName() + item.getGrID()%></td>
                    <td>
                        <input type="date" name="preDate" value="<%=item.getPresentDate()%>" />
                    </td>
                    <td>
                        <input type="text" name="room" value="<%=item.getRoom()%>" />
                    </td>
                    <td><%=item.getCreateDate()%></td>
                    <td><%=item.getStatus()%></td>
                    <td><%=item.getSubCode() + item.getSubID()%></td>
                    <td>
                        <input type="text" name="time" value="<%=item.getTime()%>" />
                    </td>
                    <td>
                        <input type="submit" value="Update Form" />
                        <input type="hidden" name="time" value="<%=item.getTime()%>" />
                        <input type="hidden" name="room" value="<%=item.getRoom()%>" />
                        <input type="hidden" name="preDate" value="<%=item.getPresentDate()%>" />
                        <input type="hidden" name="creDate" value="<%=item.getCreateDate()%>" />
                    </td>
                </tr>
            </form>
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
