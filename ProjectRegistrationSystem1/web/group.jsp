<%@page import="system.main.DTO.GroupProject"%>
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

        <%
            List<GroupProject> list = (List<GroupProject>) request.getAttribute("LIST_GROUP");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Group Code</th>
                    <th>Group Name</th>
                    <th>Topic Code</th>
                    <th>View Detail</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (GroupProject item : list) {
                %>
                <tr>
                    <td><%=count++%></td>
                    <td><%=item.getGroupCode() + item.getGroupID()%></td>
                    <td><%=item.getGroupName()%></td>
                    <td><%=item.getTopicCode() + item.getTopicID()%></td>
                    <td>
                        <a href="LecturerController?action=ViewGroupDetail&grID=<%=item.getGroupID()%>&grName=<%=item.getGroupName()%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
                            View Detail
                        </a>
                    </td>
                    <td>
                        <a href="LecturerController?action=DeleteGroup&grID=<%= item.getGroupID()%>&courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">Delete</a>
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

        <a href="LecturerController?action=addGroup&courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>&sesID=<%=session.getAttribute("SEMESTER_ID")%>">Create Group</a>
        
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
