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
        <h3>Switch Group</h3>
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
                    <th>Switch Group</th>
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
                        <a href="GetListGroupDetailUser?courseID=<%=item.getCourseID()%>&subID=<%=item.getSubjectID()%>&grID=<%=item.getGroupID()%>&groupName=<%=item.getGroupName()%>">
                            View_Detail_Group
                        </a>
                    </td>
                    <td>
                        <input type="submit" value="Switch" name="action" />
                        <input type="hidden" name="groupName" value="<%=item.getGroupName()%>" />
                        <input type="hidden" name="groupID" value="<%=item.getGroupID()%>" />
                        <input type="hidden" name="yourID" value="<%=session.getAttribute("STUDENT_ID")%>" />
                        <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>" />
                        <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>" />
                        <input type="hidden" name="yourGrPresent" value="<%=session.getAttribute("GROUP_NAME")%>" />
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
