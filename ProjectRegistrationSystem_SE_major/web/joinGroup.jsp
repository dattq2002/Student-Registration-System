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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            List<GroupProject> list = (List<GroupProject>) request.getAttribute("LIST_JOIN");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Student No Roll</th>
                    <th>Student Name</th>
                    <th>Group Name</th>
                    <th>Start Date</th>
                    <th>Major</th>
                    <th>isLeader</th>
                    <th>Topic Code</th>
                    <th>View Detail</th>
                    <th>Join Group</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (GroupProject item : list) {
                %>
            <form action="UserController" method="POST">
                <tr>
                    <td><%=item.getStudentCode() + "-" + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td><%=item.getGroupName()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getMajor()%></td>
                    <td><%=item.getIsLeader()%></td>
                    <td>
                        <input type="text" name="CODE" value="" />
                    </td>
                    <td>
                        <a href="ViewDetailGroupToJoin?courseID=<%=item.getCourseID()%>&subID=<%=item.getSubjectID()%>&grID=<%=item.getGroupID()%>">
                            View_Detail_Group
                        </a>
                    </td>
                    <td>
                        <input type="submit" value="JoinGroup" name="action" />
                        <input type="hidden" name="courseID" value="<%=item.getCourseID()%>" />
                        <input type="hidden" name="subID" value="<%=item.getSubjectID()%>" />
                        <input type="hidden" name="groupName" value="<%=item.getGroupName()%>" />
                        <input type="hidden" name="stuID" value="<%=item.getStudentID()%>" />
                        <input type="hidden" name="stuCode" value="<%=item.getStudentCode()%>" />
                        <input type="hidden" name="stuName" value="<%=item.getStudentName()%>" />
                        <input type="hidden" name="startDate" value="<%=item.getStartDate()%>" />
                        <input type="hidden" name="major" value="<%=item.getMajor()%>" />
                        <input type="hidden" name="isLeader" value="<%=item.getIsLeader()%>" />
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
        String message = (String) request.getAttribute("ERROR_MESSAGE");
        if (message != null) {
    %>
    <a><%=message%></a>
    <%
        }
    %>
</body>
</html>
