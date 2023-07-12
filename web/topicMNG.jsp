<%-- 
    Document   : topicMNG
    Created on : Jun 8, 2023, 4:45:18 PM
    Author     : Nam An
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.Topic"%>
<%@page import="DTO.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Topic Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
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
        <a href="MainController?action=LecturerListSubjectTopic">Subject</a>
        <a href="MainController?action=LecturerTopic">Create</a>

        <form action="MainController">
            Search<input type="text" name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="SearchTopic"/>
        </form>       

        <%
            List<Topic> listTopic = (List<Topic>) request.getAttribute("LIST_TOPIC");
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
                    for (Topic dto : listTopic) {
                %>
            <form action="MainController">
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= dto.getTopicID()%> </td>
                    <td> <%= dto.getTopicCode()%> </td>
                    <td> <%= dto.getTopicName()%> </td> 
                    <td>
                        <input type="hidden" name="topicID" value="<%= dto.getTopicID()%>"/>
                        <input type="submit" value="Detail" />
                        <input type="hidden" name="action" value="DetailTopic"/>
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
    %>

    <%
        }
    %> 


    <a href="createTopic.jsp">Create Topic</a>
    <a href="assignTopic.jsp">Assign Topic</a>
</body>
</html>
