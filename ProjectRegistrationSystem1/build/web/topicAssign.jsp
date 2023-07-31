<%@page import="system.main.DTO.TopicAssign"%>
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
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Context</th>
                    <th>Actor</th>
                    <th>Function</th>
                    <th>Lecturer Name</th>
                    <th>Start Date</th>
                    <th>Modify Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicAssign item : list) {
                %>
                <tr>
                    <td><%=count++%>
                        <input type="hidden" name="topicAss" value="<%=item.getTopicAssignID()%>" />
                    </td>
                    <td><%=item.getTopicCode() + item.getTopicID()%></td>
                    <td><%=item.getTopicName()%></td>
                    <td><%=item.getContext() %></td>
                    <td><%=item.getActor() %></td>
                    <td><%=item.getFunction() %></td>
                    <td><%=item.getLecName()%></td>
                    <td><%=item.getStartDate()%></td>
                    <td><%=item.getModifyDate()%></td>
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
    </body>
</html>
