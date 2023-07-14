<%@page import="system.main.DTO.Application"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <a href="manager.jsp">Home Page</a>
        
        <%
            List<Application> listApplication = (List<Application>) request.getAttribute("LIST_APPLICATION");
            if (listApplication != null) {
                if (!listApplication.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Subject ID</th>
                    <th>Course ID</th>
                    <th>Group Name</th>
                    <th>Room</th>
                    <th>Presentation Date</th>
                    <th>Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Application dto : listApplication) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getSubCode() + "-" + dto.getSubID()%> </td>
                    <td> <%= dto.getCourseName() + "-" + dto.getCourseCode() %> </td>
                    <td> <%= dto.getGrName()%> </td>
                    <td> <%= dto.getRoom() %> </td>
                    <td> <%= dto.getPresentDate()%> </td>
                    <td> <%= dto.getTime() %> </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <%
        }
    %>

    <%
        }
    %> 
    </body>
</html>
