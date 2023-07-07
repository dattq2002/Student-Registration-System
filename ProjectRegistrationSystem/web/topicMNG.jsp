<%@page import="DTO.TopicMNG"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Topic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <span> > </span>
        <a href="ListTopicMNGController">List of Topic</a>

        <form action="MainController">
            Search<input type="text" name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="SearchTopic"/>
        </form>       

        <%
            List<TopicMNG> listTopic = (List<TopicMNG>) request.getAttribute("LIST_TOPIC");
            if (listTopic != null) {
                if (!listTopic.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Topic ID</th>
                    <th>Topic Code</th>
                    <th>Topic Name</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (TopicMNG dto : listTopic) {
                %>
            <form action="ManagerController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getTopicID()%> </td>
                    <td> <%= dto.getTopicCode()%> </td>
                    <td> <%= dto.getTopicName()%> </td> 
                    <td>
                        <input type="hidden" name="detail" value="<%= dto.getTopicID()%>"/>
                        <input type="submit" name="action" value="DetailTopic"/>
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
        String error_message = (String) request.getAttribute("ERROR_MESSAGE");
        if (error_message == null) {
            error_message = "";
        }
    %>

    <%
        }
    %> 

    <%
        List<TopicMNG> searchTopic = (List<TopicMNG>) request.getAttribute("SEARCH_TOPIC");
        if (searchTopic != null) {
            if (!searchTopic.isEmpty()) {
    %>
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Topic ID</th>
                <th>Topic Code</th>
                <th>Topic Name</th>
                <th>Detail</th>
            </tr>
        </thead>
        <tbody>
            <%
                int count = 1;
                for (TopicMNG dto : searchTopic) {
            %>
        <form action="ManagerController">
            <tr>
                <td> <%= count++%> </td>
                <td> <%= dto.getTopicID()%> </td>
                <td> <%= dto.getTopicCode()%> </td>
                <td> <%= dto.getTopicName()%> </td> 
                <td>
                    <form action="MainController">
                        <input type="hidden" name="detail" value="<%= dto.getTopicID()%>"/>
                        <input type="submit" name="action" value="DetailTopic"/>
                    </form>
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
    String error_message = (String) request.getAttribute("ERROR_MESSAGE");
    if (error_message == null) {
        error_message = "";
    }
%>

<%
    }
%> 

<a href="createTopic.jsp">Create Topic</a>

</body>
</html>
