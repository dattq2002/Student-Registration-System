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
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <a href="ManagerController?action=LecturerGroup">Group</a>
        <a href="ManagerController?action=LecturerFalseGroup">False Group</a>
        <br>

        <%
            List<Group> listGroup = (List<Group>) request.getAttribute("LIST_GROUP");
            if (listGroup != null) {
                if (!listGroup.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Group Name</th>
                    <th>Course Code</th>
                    <th>Subject Code</th>
                    <th>Student Code</th>
                    <th>Student Name</th>
                    <th>isLeader</th>
                    <th>Approve</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Group dto : listGroup) {
                %>
            <form action="ManagerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getGroupName()%> </td>
                    <td> <%= dto.getCourseName() + "-" + dto.getCourseCode()%> </td>
                    <td> <%= dto.getSubjectCode() + "-" + dto.getSubjectID()%> </td> 
                    <td> <%= dto.getStudentCode() + dto.getStudentID()%> </td> 
                    <td> <%= dto.getStudentName()%> </td>
                    <td> <%= dto.getIsLeader()%> </td>
                    <td>
                        <input type="submit" value="Approve" />
                        <input type="hidden" name="action" value="ApproveGroup"/>
                        <input type="hidden" name="groupID" value="<%= dto.getGroupID()%>"/>
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
