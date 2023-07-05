<%@page import="DTO.Group"%>
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
            List<Group> list = (List<Group>) request.getAttribute("LIST_EROLLED");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Student No Roll</th>
                    <th>Student Name</th>
                    <th>Group Name</th>
                    <th>Course ID</th>
                    <th>Start Date</th>
                    <th>Major</th>
                    <th>Subject</th>
                    <th>isLeader</th>
                    <th>Out Group</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Group item : list) {
                %>
            <form action="UserController" method="POST">
                <tr>
                    <td><%=item.getStudentCode() + "-" + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td><%=item.getGroupName()%></td>
                    <td><%=item.getCourseID()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getMajor()%></td>
                    <td><%=item.getSubjectName() + "-" + item.getSubjectID()%></td>
                    <td><%=item.getIsLeader()%></td>
                    <td>
                        <input type="submit" value="OutGroup" name="action" />
                        <input type="hidden" name="subID" value="<%=item.getSubjectID()%>" />
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
    <%
        String message = (String) request.getAttribute("ERROR_OUT");
        if (message != null) {
    %>
    <p style="color: red"><%=message%></p>
    <%
        }
    %>
    <%
        String message1 = (String) request.getAttribute("SUCCESS");
        if (message1 != null) {
    %>
    <p style="color: green"><%=message1%></p>
    <%
        }
    %>
</body>
</html>
