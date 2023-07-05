<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.TopicMNG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Topic Page</title>
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
        <span> > </span>
        <a href="ListTopicMNGController">List of Topic</a>

        <%
            List<TopicMNG> searchTopic = (List<TopicMNG>) request.getAttribute("DETAIL_TOPIC");
            if (searchTopic != null) {
                if (!searchTopic.isEmpty()) {
        %>

        <%
            for (TopicMNG dto : searchTopic) {
        %>
        <form action="ManagerController" method="POST">

            <br>Topic ID: <%= dto.getTopicID()%> <br>

            <br>Topic Code: <textarea name="topicCode" ><%= dto.getTopicCode()%></textarea> <br>

            <br>Topic Name: <textarea name="topicName" style="width: 250px; height: 50px;"><%= dto.getTopicName()%></textarea> <br>

            <br>Short Description: <textarea name="shortDescription" style="width: 250px; height: 150px;"><%= dto.getShortDescription()%></textarea> <br>

            <br>Full Description: <textarea name="fullDescription" style="width: 250px; height: 150px;"><%= dto.getFullDescription()%></textarea> <br>

            <input type="submit" name="action" value="UpdateTopic"/>
            <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>"/>

            <input type="submit" name="action1" value="DeleteTopic"/>
            <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>"/>    

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
