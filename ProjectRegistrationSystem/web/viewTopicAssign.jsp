<%@page import="DTO.TopicAssign"%>
<%@page import="java.util.List"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Topic Assign</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>List Topic Assign</h1>
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPICASS");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Topic</th>
                    <th>Subject</th>
                    <th>Create Date</th>
                    <th>Modify Date</th>
                    <th>Semester</th>
                    <th>Lecture Name</th>
                    <th>Status</th>
                    <th>Detail</th>
                    <th>Approve</th>
                    <th>Decline</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (TopicAssign item : list) {
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td><%=item.getTopicAssignID()%>
                        <input type="hidden" name="topicAssID" value="<%=item.getTopicAssignID()%>" />
                    </td>
                    <td>
                        <%=item.getTopicCode() + "-" + item.getTopicID()%>
                        <input type="hidden" name="topicid" value="<%=item.getTopicID()%>" />
                    </td>
                    <td>
                        <%= item.getSubjectCode() + item.getSubjectID()%>
                        <input type="hidden" name="subid" value="<%=item.getSubjectID()%>" />
                    </td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=(item.getModifyDate() == null) ? "" : item.getModifyDate()%></td>
                    <td><%=item.getSemester()%></td>
                    <td><%=item.getLecName()%></td>
                    <td>
                        <%=item.getStatus()%>
                        <input type="hidden" name="status" value="<%=item.getStatus()%>" />
                    </td>
                    <%
                        if (item.getStatus().equalsIgnoreCase("Processed")) {
                    %>
                    <td>
                        <a href="MainController?action=ViewTopicDetail&topicID=<%=item.getTopicID()%>&subID=<%=item.getSubjectID()%>">
                            View_Detail
                        </a>
                    </td>
                    <%
                    } else {
                    %>
                    <td>
                        <a href="#" style="color: red">Disable</a>
                    </td>
                    <%
                        }
                    %>
                    <td>
                        <input type="submit" value="Approve" />
                        <input type="hidden" name="action" value="ApproveTopic" />
                    </td>
                    <td>                       
                        <button>
                            <a href="MainController?action=DeclineTopic&status=<%=item.getStatus()%>&topicAssID=<%=item.getTopicAssignID()%>&subid=<%=item.getSubjectID()%>">
                                Decline
                            </a>
                        </button>
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
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <h3 style="color: red"><%=message%></h3>
    <%
        }
    %>
</body>
</html>
