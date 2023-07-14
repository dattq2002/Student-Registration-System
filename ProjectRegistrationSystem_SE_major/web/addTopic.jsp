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
            if (loginUser == null || !loginUser.getRoleID().equals("ADMIN")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            List<TopicAssign> list = (List<TopicAssign>) request.getAttribute("LIST_TOPIC_ASSIGN");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Lecturer Name</th>
                    <th>Short Description</th>
                    <th>Full Description</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (TopicAssign item : list) {
                %>
            <form action="AdminController">
                <tr>
                    <td><%=item.getTopicCode() + item.getTopicID()%></td>
                    <td><%=item.getTopicName()%></td>
                    <td><%=item.getLecName()%></td>
                    <td><%=item.getShortDescription()%></td>
                    <td><%=item.getFullDescription()%></td>
                    <td>
                        <input type="submit" value="Add" />
                        <input type="hidden" value="AddTopictoSubject" name="action"/>
                        <input type="hidden" name="topicID" value="<%=item.getTopicID()%>" />
                        <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>" />
                        <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>" />
                        <input type="hidden" name="sesID" value="<%=session.getAttribute("SEMESTER_ID")%>" />
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
        String message = (String) request.getAttribute("MESSAGE_ADD_TOPIC");
        if (message != null) {
    %>
    <h4><%=message%></h4>
    <%
        }
    %>
</body>
</html>
