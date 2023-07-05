<%@page import="DTO.ApplicationMNG"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="DTO.Application"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Application Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }

            String searchE = loginUser.getEmail();
            if (search == null) {
                search = "";
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <span> > </span>
        <a href="ManagerController?action=Lecturer's Application&searchE=<%= searchE%>">List of Application</a>

        <%
            List<ApplicationMNG> listApplication = (List<ApplicationMNG>) request.getAttribute("LIST_APPLICATION");
            if (listApplication != null) {
                if (!listApplication.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Application ID</th>
                    <th>Type</th>
                    <th>Subject ID</th>
                    <th>Course ID</th>
                    <th>Group ID</th>
                    <th>Student ID</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ApplicationMNG dto : listApplication) {
                %>
            <form action="ManagerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getID()%> </td>
                    <td> <%= dto.getType()%> </td>
                    <td> <%= dto.getSubjectID()%> </td>
                    <td> <%= dto.getCourseID()%> </td>
                    <td> <%= dto.getGroupID()%> </td>
                    <td> <%= dto.getStudentID()%> </td>
                    <td>
                        <input type="hidden" name="type" value="<%= dto.getType()%>"/>
                        <input type="hidden" name="detail" value="<%= dto.getID()%>"/>
                        <input type="submit" name="action" value="DetailApplication"/>
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
