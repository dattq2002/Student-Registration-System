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
                if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            %>
            <h4>Amount Member in Group: <%=(request.getAttribute("AMOUNT") == null)
                    ? "0" : request.getAttribute("AMOUNT")%></h4>
            <%
                List<Group> list = (List<Group>) request.getAttribute("LIST_MEMBER");
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
                </tr>
            </thead>
            <tbody>
                <%
                    for (Group item : list) {
                %>
                <tr>
                    <td><%=item.getStudentCode() + "-" + item.getStudentID()%></td>
                    <td><%=item.getStudentName()%></td>
                    <td><%=item.getGroupName()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getMajor()%></td>
                    <td><%=item.getIsLeader()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>        
        <%
            }
        } else {
        %>
        <h1>You don't have a group please Join</h1>
        <button>
            <a href="JoinGroupController?courseID=<%=request.getAttribute("CourseID")%>&subID=<%=request.getAttribute("subID")%>">
                Join Group</a>
        </button>
        <%
            }
        %>

    </body>
</html>
