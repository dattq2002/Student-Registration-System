<%@page import="system.main.DTO.ClassInformation"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            ClassInformation infcl = (ClassInformation) request.getAttribute("INFOR_CLASS");
            if (infcl != null) {
        %>
        <h3>List Group - Subject: <%=infcl.getSubjectCode() + infcl.getSubjectID()%> </h3>
        <h3>Lecture Name: <%=request.getAttribute("LECTURE_NAME")%></h3>
        <%
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
                    <td><%=((item.getTopicCode() == null) ? "" : item.getTopicCode()) + ((item.getTopicID() == 0) ? "" : item.getTopicID())%></td>
                    <td>
                        <a href="AdminController?action=ViewGroupDetail&grID=<%=item.getGroupID()%>&grName=<%=item.getGroupName()%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
                            View Detail
                        </a>
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

        <%
            String message = (String) request.getAttribute("MESSAGE_LIST_GROUP");
            if (message != null) {
        %>
        <h4><%=message%></h4>
        <%
            }
        %>
    </body>
</html>
