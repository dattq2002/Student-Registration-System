<%@page import="system.main.DTO.Subject"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <%
            List<Subject> list = (List<Subject>) request.getAttribute("LIST_SUBJECT");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Subject Code</th>
                    <th>Subject Name</th>
                    <th>Description</th>
                    <th>Credit</th>
                    <th>Lecturer Name</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Subject item : list) {
                %>
            <form action="AdminController">
                <tr>
                    <td><%=count++%></td>
                    <td><%=item.getSubjectCode() + item.getSubjectID()%></td>
                    <td><%=item.getSubjectName()%></td>
                    <td><%=(item.getDescription() == null) ? "" : item.getDescription()%></td>
                    <td><%=item.getCredit()%></td>
                    <td>
                        <input type="text" name="lecName" value="" required=""/>
                    </td>
                    <td>
                        <input type="submit" value="Add" />
                        <input type="hidden" name="action" value="AssignSubjectClass" />
                        <input type="hidden" name="subID" value="<%=item.getSubjectID()%>" />
                        <input type="hidden" name="couseID" value="<%=session.getAttribute("COURSE_ID")%>" />
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
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <a><%=message%></a>
    <%
        }
    %>
</body>
</html>
