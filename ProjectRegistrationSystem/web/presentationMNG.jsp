<%@page import="DTO.PresentationMNG"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Presentation Page</title>
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
            List<PresentationMNG> listPresentation = (List<PresentationMNG>) request.getAttribute("LIST_PRESENTATION");
            if (listPresentation != null) {
                if (!listPresentation.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Presentation ID</th>
                    <th>Type</th>
                    <th>Subject ID</th>
                    <th>Course ID</th>
                    <th>Group ID</th>
                    <th>Room</th>
                    <th>Presentation Date</th>
                    <th>Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (PresentationMNG dto : listPresentation) {
                %>
            <form action="ManagerController" method="POST">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getID()%> </td>
                    <td> <%= dto.getType()%> </td>
                    <td> <%= dto.getSubjectID()%> </td>
                    <td> <%= dto.getCourseID()%> </td>
                    <td> <%= dto.getGroupID()%> </td>
                    <td> <%= dto.getRoom()%> </td>
                    <td> <%= dto.getPresentDate()%> </td>
                    <td> <%= dto.getTime()%> </td>
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
    <a href="postPresentation.jsp">Post Presentation</a>
</body>
</html>
