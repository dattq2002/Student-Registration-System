<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.ClassMNG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Class Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>

        <%
            List<ClassMNG> listClass = (List<ClassMNG>) request.getAttribute("LIST_CLASS");
            if (listClass != null) {
                if (!listClass.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Class</th>
                    <th>Semester</th>
                    <th>Subject</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Join Class</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ClassMNG dto : listClass) {
                %>
            <form action="ManagerController" method="POST">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getClassID()%> </td>
                    <td> <%= dto.getSemester()%> </td>
                    <td> <%= dto.getSubject()%> </td>
                    <td> <%= dto.getStartDate()%> </td>
                    <td> <%= dto.getEndDate()%> </td>
                    <td>
                        <form action="MainController">
                            <input type="hidden" name="join" value="<%= dto.getID()%>"/>
                            <input type="submit" name="action" value="Join Class"/>
                        </form>
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
        String error_message = (String) request.getAttribute("ERROR_MESSAGE");
        if (error_message != null) {
    %>
    <p><%=error_message%></p>
    <%
        }
    %>
</body>
</html>
